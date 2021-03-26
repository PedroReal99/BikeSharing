package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Invoice {

    private float totalCost;
    private int invoiceId;
    private String paymentLimitDate;
    private String month;
    private List<RideBilling> rideBillingList=new ArrayList<>();

    public Invoice(int invoiceId, String paymentLimitDate, String month) {
        this.totalCost = 0;
        this.invoiceId = invoiceId;
        this.paymentLimitDate = paymentLimitDate;
        this.month = month;
        this.rideBillingList = new ArrayList<>();
    }
    
     public Invoice(float totalCost, int invoiceId, String paymentLimitDate, String month) {
        this.totalCost = totalCost;
        this.invoiceId = invoiceId;
        this.paymentLimitDate = paymentLimitDate;
        this.month = month;
        this.rideBillingList = new ArrayList<>();
    }

    public Invoice() {
    }

    public String getPaymentLimitDate() {
        return paymentLimitDate;
    }

    public void setPaymentLimitDate(String paymentLimitDate) {
        this.paymentLimitDate = paymentLimitDate;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<RideBilling> getRideBillingList() {
        return rideBillingList;
    }

    public void setRideBillingList(List<RideBilling> rideBillingList) {
        this.rideBillingList = rideBillingList;
    }

    public void addBill(RideBilling rb) {
        getRideBillingList().add(rb);
        totalCost = totalCost + rb.getCost();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Float.floatToIntBits(this.totalCost);
        hash = 83 * hash + this.invoiceId;
        hash = 83 * hash + Objects.hashCode(this.paymentLimitDate);
        hash = 83 * hash + Objects.hashCode(this.month);
        hash = 83 * hash + Objects.hashCode(this.rideBillingList);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Invoice other = (Invoice) obj;
        if (Float.floatToIntBits(this.totalCost) != Float.floatToIntBits(other.totalCost)) {
            return false;
        }
        if (this.invoiceId != other.invoiceId) {
            return false;
        }
        if (!Objects.equals(this.paymentLimitDate, other.paymentLimitDate)) {
            return false;
        }
        if (!Objects.equals(this.month, other.month)) {
            return false;
        }
        if (!Objects.equals(this.rideBillingList, other.rideBillingList)) {
            return false;
        }
        return true;
    }

}
