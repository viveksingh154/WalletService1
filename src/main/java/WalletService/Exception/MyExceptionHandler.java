package WalletService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wallet.exception.AccountException;
import com.wallet.exception.AccountNotFound;

@ControllerAdvice
@RestController
public class MyExceptionHandler extends ResponseEntityExceptionHandler

{

	@ExceptionHandler(value = { AccountException.class })
	public ResponseEntity<?> handleAccountException(AccountException ex, WebRequest request) {
		String message = ex.getLocalizedMessage();
		

		return new ResponseEntity<String>(message, HttpStatus.OK);

	}
	@ExceptionHandler(value = { AccountNotFound.class })
	public ResponseEntity<?> handleAccountException(AccountNotFound ex, WebRequest request) {
		String message = ex.getLocalizedMessage();
		

		return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);

	}
	
	
	
}
