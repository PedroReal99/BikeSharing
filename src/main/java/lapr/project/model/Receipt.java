package lapr.project.model;

import java.util.Objects;

public class Receipt {

	private int invoiceId;
	private int userId;
	private String paymentDate;
	private float totalValue;

    public Receipt(int invoiceId, int userId, String paymentDate, float totalValue) {
        this.invoiceId = invoiceId;
        this.userId = userId;
        this.paymentDate = paymentDate;
        this.totalValue = totalValue;
    }

    public Receipt() {
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public String toString() {
        return "Receipt{" + "invoiceId=" + invoiceId + ", userId=" + userId + ", paymentDate=" + paymentDate + ", totalValue=" + totalValue + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.invoiceId;
        hash = 73 * hash + this.userId;
        hash = 73 * hash + Objects.hashCode(this.paymentDate);
        hash = 73 * hash + Float.floatToIntBits(this.totalValue);
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
        final Receipt other = (Receipt) obj;
        if (this.invoiceId != other.invoiceId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (Float.floatToIntBits(this.totalValue) != Float.floatToIntBits(other.totalValue)) {
            return false;
        }
        if (!Objects.equals(this.paymentDate, other.paymentDate)) {
            return false;
        }
        return true;
    }
    
    
    
        
        

}