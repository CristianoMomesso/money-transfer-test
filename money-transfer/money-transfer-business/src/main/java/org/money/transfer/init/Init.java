package org.money.transfer.init;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.money.transfer.business.AccountBO;
import org.money.transfer.dto.AccountDTO;

@Startup
@Singleton
public class Init {

	@Inject
	private AccountBO accBo;

	@PostConstruct
	public void init_cenario() {
		AccountDTO accDto = new AccountDTO();
		accDto.setAccountNumber(1l);
		accDto.setFirstName("Test1");
		accDto.setLastName("Test1");
		accDto.setOwnerDocument("1");
		accDto.setBalance(100d);
		accBo.creatAccount(accDto);

		AccountDTO accDto2 = new AccountDTO();
		accDto2.setAccountNumber(2l);
		accDto2.setFirstName("Test2");
		accDto2.setLastName("Test2");
		accDto2.setOwnerDocument("2");
		accDto2.setBalance(10d);
		accBo.creatAccount(accDto2);

		AccountDTO accDto3 = new AccountDTO();
		accDto3.setAccountNumber(3l);
		accDto3.setFirstName("Test3");
		accDto3.setLastName("Test3");
		accDto3.setOwnerDocument("3");
		accDto3.setBalance(0d);
		accBo.creatAccount(accDto3);
	}
}
