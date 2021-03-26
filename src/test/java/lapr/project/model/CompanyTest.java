/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;



/**
 *
 * @author USER
 */
public class CompanyTest {
    
    public CompanyTest() {
    }
    
   


    /**
     * Test of getInvoiceList method, of class Company.
     */
    @Test
    public void testGetInvoiceList() {
        System.out.println("getInvoiceList");
        List<Invoice> expResult = new ArrayList<>();
        List<Invoice> result = Company.getInvoiceList();
        assertEquals(expResult, result);
       
    }

   
    
}
