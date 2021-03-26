/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import lapr.project.model.Invoice;
import lapr.project.model.Receipt;
import lapr.project.model.RideBilling;
import lapr.project.model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author hugov
 */
public class UpdateInvoiceCostControllerTest {

    public UpdateInvoiceCostControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    

    /**
     * Test of checkUserPoints method, of class CheckPointsController.
     */
    @Test
    public void testUpdateInvoiceCost() {
        System.out.println("checkUserPoints");
        Calendar now = Calendar.getInstance();

        User u = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30,2.0,"Pedro");
        Invoice i = new Invoice(1, "23/04/2018", new SimpleDateFormat("MMM").format(now.getTime()));
        RideBilling rb = new RideBilling(20, 1, new SimpleDateFormat("MMM").format(now.getTime()));
        u.addInvoice(i);

        u.getInvoiceByMonth(new SimpleDateFormat("MMM").format(now.getTime())).addBill(rb);

        UpdateInvoiceCostController instance = new UpdateInvoiceCostController();
        int expResult = 0;
        int result = instance.updateInvoiceCost(u);
        assertEquals(expResult, result);

        User u1 = new User(2, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 50,2.0,"Pedro");
        Invoice i1 = new Invoice(2, "23/04/2018", new SimpleDateFormat("MMM").format(now.getTime()));
        RideBilling rb1 = new RideBilling(3, 1, new SimpleDateFormat("MMM").format(now.getTime()));
        u1.addInvoice(i1);
        u1.getInvoiceByMonth("jan").addBill(rb1);
        int expResult1 = 20;
        float result1 = instance.updateInvoiceCost(u1);
        assertEquals(expResult1, result1);
    }

    /**
     * Test of invoicePayment method, of class UpdateInvoiceCostController.
     */
    @Test
    public void testInvoicePayment() {
        System.out.println("invoicePayment");
        
        Calendar now = Calendar.getInstance();
        String m = new SimpleDateFormat("MMM").format(now.getTime());
        User u = new User(1, "amo_lapr3", 1.7f, 70, 123456789, "hugovinhal98@gmail.com", 30,2.0,"Pedro");
        Invoice i = new Invoice(1, "23/04/2018", new SimpleDateFormat("MMM").format(now.getTime()));
        RideBilling rb = new RideBilling(20, 1, new SimpleDateFormat("MMM").format(now.getTime()));
        u.addInvoice(i);

        u.getInvoiceByMonth(new SimpleDateFormat("MMM").format(now.getTime())).addBill(rb);      
        UpdateInvoiceCostController instance = new UpdateInvoiceCostController();
        Receipt result = instance.invoicePayment(u);
        Receipt expResult = new Receipt(1, 1, "Last day of " + m, 20);
        assertEquals(expResult,result);
    }

}
