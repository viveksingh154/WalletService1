package WalletService.ServiceLayer;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WalletService.AccountDto.AccountDto;
import WalletService.DtoReposistory.AccountReposistory;
import WalletService.EntityClass.AccountDetails;
import WalletService.Exception.AccountException;
import WalletService.Exception.AccountNotFound;
@Service
public class AccountService 
{
	@Autowired
	private AccountReposistory accountReposistory;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//creating account
	public AccountDetails createAcount(AccountDto accountDto) 
	{
		AccountDetails map = modelMapper.map(accountDto, AccountDetails.class);
		AccountDetails account=accountReposistory.save(map);
		return account;
		
	}
	public AccountDetails creaditAccount(long AccountNumber,int amount)
	{
		Optional<AccountDetails> user = accountReposistory.findById(AccountNumber);
		AccountDetails accountDetails = user.get();
		float Balance=accountDetails.getBalance();
		amount+=Balance;
		accountDetails.setBalance(Balance);
		AccountDetails save = accountReposistory.save(accountDetails);
		return save;
		
	}
	public float getOldBalance(long AccountNumber)
	{
		Optional<AccountDetails> search = accountReposistory.findById(AccountNumber);
		AccountDetails accountDetails = search.get();
		float balance = accountDetails.getBalance();
		return balance;
		
	}
	public Optional<AccountDetails> getAccountDetail(long AccountNumber)
	{
		Optional<AccountDetails> find = accountReposistory.findById(AccountNumber);
		return find;
		
	}
	public ArrayList<AccountDetails> transferAmount(long debitfromAccount,long creditintoAccount,float amount)
	{
		 Optional<AccountDetails> account1=accountReposistory.findById(debitfromAccount);
		 Optional<AccountDetails> account2= accountReposistory.findById(creditintoAccount);
		 float debitfromThisBalance = account1.get().getBalance();
			float creditIntoThisBalance = account2.get().getBalance();
			if(debitfromThisBalance<amount)
			{
				throw new AccountException("insuficent balance");
			}
			else
			{
				ArrayList<AccountDetails> list=new ArrayList<>();
				debitfromThisBalance -=amount;
				account1.get().setBalance(debitfromThisBalance);
				AccountDetails updatedAccount1=accountReposistory.save(account1.get());
				creditintoAccount+=amount;
				account2.get().setBalance(creditIntoThisBalance);
				AccountDetails updatedAccount2 = accountReposistory.save(account1.get());
				list.add(updatedAccount2);
				list.add(updatedAccount1);
				return list;
				
				
			}
		
	}
	//finding single account number 
	//Finding all accountNumber .......
	public List<AccountDetails> getAllAccountDetails()
	{
		List<AccountDetails> data=(List<AccountDetails>) accountReposistory.findAll();
		return data;
		
		
	}
	public AccountDetails findByUserName(String name)
	{
		AccountDetails findByUserName=accountReposistory.findByUserName(name);
		return findByUserName;
		
	}
	public Optional<AccountDetails> getSingleAccountNumber(Long AccountNumber)
	{
		Optional<AccountDetails> ad=null;

		try
		{
			ad=accountReposistory.findById(AccountNumber);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ad;
	
		
	}
	//deleting single account number..
	public void  deleteSingleAccountNumber(Long AccountNumber)
	{
		
		Optional<AccountDetails> delete = accountReposistory.findById(AccountNumber);
		if(delete.isPresent())
		{
			accountReposistory.deleteById(AccountNumber);
		}
		else
		{
			throw new AccountNotFound("account not found:"+"Account not present here");
		}
		
	}
	//savinng account number....
	public AccountDetails savingAccount(AccountDetails accountDetails)
	{
		AccountDetails save1 = accountReposistory.save(accountDetails);
		return save1;
		
	}
}
