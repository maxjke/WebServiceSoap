package lt.eif.viko.mjakovcenko.banksoap.banksoap;

import lt.eif.viko.mjakovcenko.banksoap.banksoap.model.BankAccount;
import lt.eif.viko.mjakovcenko.banksoap.banksoap.model.Client;
import lt.eif.viko.mjakovcenko.banksoap.banksoap.model.CreditCard;
import lt.eif.viko.mjakovcenko.banksoap.banksoap.model.Loan;
import lt.viko.eif.mj.springsoap.gen.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class BanksoapApplication {
	public static void main(String[] args) {
		SpringApplication.run(BanksoapApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}


