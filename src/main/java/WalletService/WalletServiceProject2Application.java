package WalletService;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WalletServiceProject2Application extends SpringBootServletInitializer
{

	public static void main(String[] args)
	{
		SpringApplication.run(WalletServiceProject2Application.class, args);
	}
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
		
		
	}

}
