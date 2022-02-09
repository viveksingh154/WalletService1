package WalletService.Controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import WalletService.AccountDto.AccountDto;
import WalletService.EntityClass.AccountDetails;
import WalletService.Exception.AccountNotFound;
import WalletService.ServiceLayer.AccountService;

@RestController
public class AccountCotroller
{
	@Autowired
	private AccountService accountService;
	@Autowired
	private AccountDto accountDto;
	
	@PostMapping("/create")
	public ResponseEntity<Object> createAccount(AccountDto accountDto)
	{
		AccountDetails create = accountService.createAcount(accountDto);
		if(create==null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.of(Optional.of(create));
	}
	@PutMapping("/creditacc")
	public ResponseEntity<Object> creditAccount(long AccountNumber,int amount)
	{
		float oldBalance = accountService.getOldBalance(AccountNumber);
		AccountDetails result=accountService.creaditAccount(AccountNumber, amount);
		Float updatedBalance=oldBalance+amount;
		if(updatedBalance!=result.getBalance())
		return  ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
		return ResponseEntity.of(Optional.of(result));
		
	}
	@PutMapping("/transferamount")
	public ResponseEntity<Object> transferAmount(long debitFromAccount, long creditIntoAccount, float amount)
	{
		float oldBalance1=accountService.getOldBalance(debitFromAccount);
		float oldBalance2=accountService.getOldBalance(creditIntoAccount);
		ArrayList<AccountDetails> result= accountService.transferAmount(debitFromAccount, creditIntoAccount, amount);
		AccountDetails user1= result.get(0);
		AccountDetails user2 = result.get(1);
		float updatedBalance1 = user1.getBalance();
		float updatedBalance2= user2.getBalance();
		
		if(updatedBalance1+amount!=oldBalance1&&updatedBalance2-amount!=oldBalance2)
		
		{
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
		
	}
		return ResponseEntity.of(Optional.of(result));
}
	@GetMapping("/accountdetails/{AccountNumber}")
	public ResponseEntity<Optional<AccountDetails>> getAccountDetails(@PathVariable long AccountNumber  )
	{
		Optional<AccountDetails> accountDetail = accountService.getAccountDetail(AccountNumber);
		if(accountDetail.isPresent())
		{
		return ResponseEntity.of(Optional.of(accountDetail));
	     }
		else
			
		{
			throw new AccountNotFound("please enter correct account number"+AccountNumber);
		}
	}
	@GetMapping("/deleteacc/{AccountNumber}")
	public void deleteAccountNumber(@PathVariable  long AccountNumber)
	{
		accountService.deleteSingleAccountNumber(AccountNumber);
		
	}
	@GetMapping("/")
	public RedirectView redirectToSwagger() {

		RedirectView redirectView =new RedirectView();
		//redirectView.setUrl("http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config");
		redirectView.setUrl("http://localhost:8080/swagger-ui.html");
		
		return redirectView;
}
	@GetMapping("/findByUserName/{name}")
	 public AccountDetails findByUserName( @PathVariable String name)
	 {
		AccountDetails findByUserName = accountService.findByUserName(name);
		return findByUserName;
		
	 }
	
}
	
	
	