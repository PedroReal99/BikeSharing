/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author USER
 */
public class InvoiceTest {

    public InvoiceTest() {
    }

    /**
     * Test of getPaymentLimitDate method, of class Invoice.
     */
    @Test
    public void testGetPaymentLimitDate() {
        System.out.println("getPaymentLimitDate");
        Invoice instance = new Invoice(1, "23/04/2018", "April");
        String expResult = "23/04/2018";
        String result = instance.getPaymentLimitDate();

        assertEquals(expResult, result);

        Invoice i = new Invoice(0, 0, result, result);
        i.setMonth(result);
    }

    /**
     * Test of setPaymentLimitDate method, of class Invoice.
     */
    @Test
    public void testSetPaymentLimitDate() {
        System.out.println("setPaymentLimitDate");
        String paymentLimitDate = "27/09/21";
        Invoice instance = new Invoice(1, "23/04/2018", "April");
        instance.setPaymentLimitDate(paymentLimitDate);
        assertEquals(paymentLimitDate, instance.getPaymentLimitDate());
    }

    /**
     * Test of getTotalCost method, of class Invoice.
     */
    @Test
    public void testGetTotalCost() {
        System.out.println("getTotalCost");
        Invoice instance = new Invoice(1, "23/04/2018", "April");
        float expResult = 0F;
        float result = instance.getTotalCost();
        assertEquals(expResult, result);

    }

    /**
     * Test of setTotalCost method, of class Invoice.
     */
    @Test
    public void testSetTotalCost() {
        System.out.println("setTotalCost");
        float totalCost = 37F;
        Invoice instance = new Invoice(1, "23/04/2018", "April");
        instance.setTotalCost(totalCost);
        assertEquals(totalCost, instance.getTotalCost());

    }

    /**
     * Test of getInvoiceId method, of class Invoice.
     */
    @Test
    public void testGetInvoiceId() {
        System.out.println("getInvoiceId");
        Invoice instance = new Invoice(1, "23/04/2018", "April");
        int expResult = 1;
        int result = instance.getInvoiceId();
        assertEquals(expResult, result);

    }

    /**
     * Test of setInvoiceId method, of class Invoice.
     */
    @Test
    public void testSetInvoiceId() {
        System.out.println("setInvoiceId");
        int invoiceId = 2;
        Invoice instance = new Invoice();
        instance.setInvoiceId(invoiceId);
        assertEquals(invoiceId, instance.getInvoiceId());
    }

    /**
     * Test of getMonth method, of class Invoice.
     */
    @Test
    public void testGetMonth() {
        System.out.println("getMonth");
        Invoice instance = new Invoice(1, "23/04/2018", "April");
        String expResult = "April";
        String result = instance.getMonth();
        assertEquals(expResult, result);

    }

    /**
     * Test of setMonth method, of class Invoice.
     */
    @Test
    public void testSetMonth() {
        System.out.println("setMonth");
        String month = "June";
        Invoice instance = new Invoice(1, "23/04/2018", "April");
        instance.setMonth(month);
        assertEquals(month, instance.getMonth());
    }

    /**
     * Test of getRideBillingList method, of class Invoice.
     */
    @Test
    public void testGetRideBillingList() {
        System.out.println("getRideBillingList");
        Invoice instance = new Invoice(1, "23/04/2018", "April");
        List<RideBilling> expResult = new ArrayList<>();
        List<RideBilling> result = instance.getRideBillingList();
        assertEquals(expResult, result);

    }

    /**
     * Test of setRideBillingList method, of class Invoice.
     */
    @Test
    public void testSetRideBillingList() {
        System.out.println("setRideBillingList");
        List<RideBilling> rideBillingList = new ArrayList<>();
        RideBilling rb = new RideBilling();
        rideBillingList.add(rb);
        Invoice instance = new Invoice(1, "23/04/2018", "April");
        instance.setRideBillingList(rideBillingList);
        assertEquals(rideBillingList, instance.getRideBillingList());

    }

    /**
     * Test of hashCode method, of class Invoice.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Invoice instance = new Invoice();
        int expResult = 1803480726;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Invoice.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Invoice instance = new Invoice(1, "23/04/2018", "April");
        Invoice instance2 = new Invoice(1, "23/04/2018", "April");
        Invoice instance3 = new Invoice(2, "23/04/2018", "April");

        boolean result = instance.equals(instance2);
        assertTrue(result);

        boolean result2 = instance.equals(instance3);
        assertTrue(!result2);

        Ride r = new Ride("12/12/2018", "10:20", "10:53", "1", "2", 1, 345, "1");
        boolean result3 = instance.equals(r);   // false class
        assertTrue(!result3);

        boolean result4 = instance.equals(null);
        assertTrue(!result4);

        Invoice instance4 = new Invoice(2, "24/04/2018", "April");
        boolean result5 = instance.equals(instance4);
        assertTrue(!result5);

        Invoice instance5 = new Invoice(1, "23/04/2018", "Aril");
        boolean result6 = instance.equals(instance5);
        assertTrue(!result6);

        Invoice instance6 = new Invoice(0, 0, "22", "la");
        Invoice instance7 = new Invoice(1, 0, "22", "la");
        boolean result7 = instance6.equals(instance7);
        assertTrue(!result7);

        Invoice instance8 = new Invoice(0, 0, "2", "la");
        boolean result8 = instance6.equals(instance8);
        assertTrue(!result8);

        Invoice instance9 = new Invoice(0, 0, "22", "la");
        instance9.addBill(new RideBilling());
        boolean result9 = instance6.equals(instance9);
        assertTrue(!result9);

    }

}
