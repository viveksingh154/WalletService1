package WalletService.AccountDto;

import org.springframework.stereotype.Component;

@Component
public class AccountDto 
{
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
	public AccountDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountDto(Long accountNumber, String username, String gender, float balance) {
		super();
		AccountNumber = accountNumber;
		Username = username;
		Gender = gender;
		Balance = balance;
	}
	@Override
	public String toString() {
		return "AccountDto [AccountNumber=" + AccountNumber + ", Username=" + Username + ", Gender=" + Gender
				+ ", Balance=" + Balance + "]";
	}
	
}
