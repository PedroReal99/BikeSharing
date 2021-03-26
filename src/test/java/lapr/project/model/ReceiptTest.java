/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;



/**
 *
 * @author USER
 */
public class ReceiptTest {
    
    public ReceiptTest() {
    }

    /**
     * Test of getInvoiceId method, of class Receipt.
     */
    @Test
    public void testGetInvoiceId() {
        System.out.println("getInvoiceId");
        Receipt instance = new Receipt(1, 1161007, "10/11/1233", 10f);
        int expResult = 1;
        int result = instance.getInvoiceId();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setInvoiceId method, of class Receipt.
     */
    @Test
    public void testSetInvoiceId() {
        System.out.println("setInvoiceId");
        int invoiceId = 5;
        Receipt instance = new Receipt(1, 1161007, "10/11/1233", 10f);
        instance.setInvoiceId(invoiceId);
        assertEquals(invoiceId,instance.getInvoiceId());
    }

    /**
     * Test of getUserId method, of class Receipt.
     */
    @Test
    public void testGetUserId() {
        System.out.println("getUserId");
        Receipt instance = new Receipt(1, 1161007, "10/11/1233", 10f);
        int expResult = 1161007;
        int result = instance.getUserId();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setUserId method, of class Receipt.
     */
    @Test
    public void testSetUserId() {
        System.out.println("setUserId");
        int userId = 1161231;
        Receipt instance = new Receipt(1, 1161007, "10/11/1233", 10f);
        instance.setUserId(userId);
        assertEquals(userId,instance.getUserId());
    }

    /**
     * Test of getPaymentDate method, of class Receipt.
     */
    @Test
    public void testGetPaymentDate() {
        System.out.println("getPaymentDate");
        Receipt instance = new Receipt(1, 1161007, "10/11/1233", 10f);
        String expResult = "10/11/1233";
        String result = instance.getPaymentDate();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setPaymentDate method, of class Receipt.
     */
    @Test
    public void testSetPaymentDate() {
        System.out.println("setPaymentDate");
        String paymentDate = "123/23/12";
        Receipt instance = new Receipt();
        instance.setPaymentDate(paymentDate);
        assertEquals(paymentDate,instance.getPaymentDate());
    }

    /**
     * Test of getTotalValue method, of class Receipt.
     */
    @Test
    public void testGetTotalValue() {
        System.out.println("getTotalValue");
        Receipt instance = new Receipt(1, 1161007, "10/11/1233", 10f);
        float expResult = 10F;
        float result = instance.getTotalValue();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setTotalValue method, of class Receipt.
     */
    @Test
    public void testSetTotalValue() {
        System.out.println("setTotalValue");
        float totalValue = 112F;
        Receipt instance = new Receipt(1, 1161007, "10/11/1233", 10f);
        instance.setTotalValue(totalValue);
        assertEquals(totalValue,instance.getTotalValue());
    }

    /**
     * Test of toString method, of class Receipt.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Receipt instance = new Receipt(1, 1, "Last day of month", 20);
        String expResult = "Receipt{invoiceId=1, userId=1, paymentDate=Last day of month, totalValue=20.0}";
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of hashCode method, of class Receipt.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Receipt instance = new Receipt();
        int expResult = 141991205;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of equals method, of class Receipt.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Receipt instance = new Receipt(1, 1, "Last day of month", 20);
        boolean result = instance.equals(obj);
        assertTrue(!result);
        
        User u =  new User();
        boolean result1 = instance.equals(u);
        assertTrue(!result1);
        
        Receipt instance2 = new Receipt(1, 1, "Last day of month", 20);
        boolean result2 = instance.equals(instance2);
        assertTrue(result2);
        
        Receipt instance3 = new Receipt(0, 1, "Last day of month", 20);
        boolean result3 = instance.equals(instance3);
        assertTrue(!result3);
        
        Receipt instance4 = new Receipt(1, 0, "Last day of month", 20);
        boolean result4 = instance.equals(instance4);
        assertTrue(!result4);
        
        Receipt instance5 = new Receipt(1, 1, "Last of month", 20);
        boolean result5 = instance.equals(instance5);
        assertTrue(!result5);
        
        Receipt instance6 = new Receipt(1, 1, "Last day of month", 1);
        boolean result6 = instance.equals(instance6);
        assertTrue(!result6);
        
        
    }
    
}
