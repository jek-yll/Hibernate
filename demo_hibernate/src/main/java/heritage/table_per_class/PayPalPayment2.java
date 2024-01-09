package heritage.table_per_class;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "paypal_payment2")
public class PayPalPayment2 extends Payment2 {

    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "PayPalPayment{" +
                "accountNumber='" + accountNumber + '\'' +
                "} " + super.toString();
    }
}
