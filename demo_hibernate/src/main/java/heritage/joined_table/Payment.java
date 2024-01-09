package heritage.joined_table;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="payment")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;

    private Double amount;

    private Date paymentDate = new Date();

    public Payment() {
    }

    public Long getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Long idPayment) {
        this.idPayment = idPayment;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "idPayment=" + idPayment +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
