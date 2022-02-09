package WalletService.EntityClass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AllUser_Details")
public class AccountDetails 
{
@Id
private Long AccountNumber;
private String Username;
private String Gender;
private float Balance;

public Long getAccountNumber() {
	return AccountNumber;
}
public void setAccountNumber(Long accountNumber) {
	AccountNumber = accountNumber;
}
public String getUsername() {
	return Username;
}
public void setUsername(String username) {
	Username = username;
}
public String getGender() {
	return Gender;
}
public void setGender(String gender) {
	Gender = gender;
}
public float getBalance() {
	return Balance;
}
public void setBalance(float balance) {
	Balance = balance;
}
public AccountDetails(Long accountNumber, String username, String gender, float balance) {
	super();
	AccountNumber = accountNumber;
	Username = username;
	Gender = gender;
	Balance = balance;
}
public AccountDetails() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "AccountDetails [AccountNumber=" + AccountNumber + ", Username=" + Username + ", Gender=" + Gender
			+ ", Balance=" + Balance + "]";
}


}
