/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lapr.project.model.Bicycle;
import lapr.project.model.Company;
import lapr.project.model.Invoice;
import lapr.project.model.Location;
import lapr.project.model.Park;
import lapr.project.model.Ride;
import lapr.project.model.RideBilling;
import lapr.project.model.User;

/**
 *
 * @author hugov
 */
public class LockBicycleController {

    public Ride lockBicycle(int userId, String parkFinalId) {

        User u = Company.getUserRegistry().getUserById(userId);

        int lastRide = u.getRideMap().size();
        Ride r = u.getRideById(lastRide);

        Park pInicial = Company.getParkRegistry().getParkByDescription(r.getStartParkDesc());
        Park pFinal = Company.getParkRegistry().getParkByDescription(parkFinalId);

        Bicycle b = Company.getBicycleRegistry().getBicycleByDescription(r.getBicycleDesc());
        pInicial.deleteBicycle(r.getBicycleDesc());

        b.setIsAvailable(true);
        pFinal.addBicycle(b);

        Calendar now = Calendar.getInstance();
        r.setEndTime(now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));
        r.setEndParkDesc(parkFinalId);

        sendFromGMail(u.getEmail(), u.getPassword(), "pintainhohugo@gmail.com", "Bicycle parked with success!", "The bicycle was used from " + r.getStartTime() + " to " + r.getEndTime());
        String m = new SimpleDateFormat("MMM").format(now.getTime());

        if (u.getInvoiceByMonth(m) == null) {
            u.addInvoice(new Invoice(u.getInvoiceList().size() + 1, "Last day of " + m, m));
        } else {
            u.getInvoiceByMonth(m).addBill(new RideBilling(chargeUser(userId, lastRide), lastRide, m));  // adds a bill to the users respective invoice.
        }
        return r;
    }

    public long lockBicycle(String location, String bikedesc) {
        AwardPointsController inst = new AwardPointsController();
        Park p = (Park) Company.getParkRegistry().getLocByLocation(location);
        Bicycle b = Company.getBicycleRegistry().getBicycleByDescription(bikedesc);

        for (User u : Company.getUserRegistry().getUserMap().values()) {
            boolean f = false;
            int lastRide = u.getRideMap().size() - 1;
            Ride r = u.getRideById(lastRide);
            if (r.getBicycleDesc().equals(bikedesc)) {
                for (Location pa : Company.getParkRegistry().getParkMap().values()) {
                    Park p1 = (Park) pa;
                    for (String bike : p1.getMountainRoadList()) {
                        if (bike.equals(bikedesc)) {
                            r.setEndParkDesc(p.getDescription());
                            r.setStartParkDesc(p1.getDescription());
                            Bicycle b1 = Company.getBicycleRegistry().getBicycleByDescription(bikedesc);
                            p1.deleteBicycle(b1.getBicycleDesc());
                            u.setPoints(inst.getAwardPointsBetweenParks(p1.getDescription(), p.getDescription()));
                            f = true;
                            break;
                        }
                    }
                    if(f){
                        break;
                    }
                    for (String bike : p1.getElectricList()) {
                        if (bike.equals(bikedesc)) {
                            r.setStartParkDesc(p1.getDescription());
                            Bicycle b1 = Company.getBicycleRegistry().getBicycleByDescription(bikedesc);
                            p1.deleteBicycle(b1.getBicycleDesc());
                            u.setPoints(inst.getAwardPointsBetweenParks(p1.getDescription(), p.getDescription()));
                            f = true;
                            break;
                        }
                    }
                    if(f){
                        break;
                    }
                }
                b.setIsAvailable(true);
                p.addBicycle(b);
                Calendar now = Calendar.getInstance();
                r.setEndTime(now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));
                

                //sendFromGMail(u.getEmail(), u.getPassword(), "pintainhohugo@gmail.com", "Bicycle parked with success!", "The bicycle was used from " + r.getStartTime() + " to " + r.getEndTime());
                String m = new SimpleDateFormat("MMM").format(now.getTime());

                if (u.getInvoiceByMonth(m) == null) {
                    u.addInvoice(new Invoice(u.getInvoiceList().size() + 1, "Last day of " + m, m));
                    u.getInvoiceByMonth(m).addBill(new RideBilling(chargeUser(u.getUserId(), lastRide), lastRide, m));  // adds a bill to the users respective invoice.
                } else {
                    u.getInvoiceByMonth(m).addBill(new RideBilling(chargeUser(u.getUserId(), lastRide), lastRide, m));  // adds a bill to the users respective invoice.
                }
                return now.get(Calendar.HOUR_OF_DAY);
            }
        }
        return -1;
    }

    /**
     *
     * based on:
     *
     * https://stackoverflow.com/questions/46663/
     * how-can-i-send-an-email-by-java-application-using-gmail-yahoo-or-
     * hotmail?fbclid=IwAR3BwHueYuEQ913Qo9v7Y_
     * _YdEiKLdx-YGYpEjmHpmULkB2b3uKDKXAiFN4
     *
     * @param from
     * @param pass
     * @param to
     * @param subject
     * @param body
     * @return
     */
    public boolean sendFromGMail(String from, String pass, String to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        boolean b1 = true;

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress toAddress = new InternetAddress();

            toAddress = new InternetAddress(to);

            message.addRecipient(Message.RecipientType.TO, toAddress);

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
            b1 = false;
        } catch (MessagingException me) {
            me.printStackTrace();
            b1 = false;
        }
        return b1;

    }

    /**
     * Returns the price the user as to pay for a ride.
     *
     * @param userId
     * @param rideId
     * @return
     */
    public int chargeUser(int userId, int rideId) {
        User u = Company.getUserRegistry().getUserById(userId);
        Ride r = u.getRideMap().get(rideId);

        int horas = r.getHoursUsed();
        if (horas == 0) {
            return horas;
        } else {
            return (horas - 1) * 3;
        }

    }
}
