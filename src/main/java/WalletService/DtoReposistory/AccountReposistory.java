package WalletService.DtoReposistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import WalletService.EntityClass.AccountDetails;

public interface AccountReposistory extends JpaRepository<AccountDetails,Long>
{

      @Query(value="SELECT * FROM account_details where user_name=:name",nativeQuery = true)
	   public AccountDetails findByUserName(@Param("name") String name);

}
