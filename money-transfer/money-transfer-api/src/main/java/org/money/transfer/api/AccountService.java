package org.money.transfer.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.money.transfer.business.AccountBO;
import org.money.transfer.dto.AccountDTO;
import org.money.transfer.dto.AccountTransferDTO;
import org.money.transfer.exception.AccountNotFoundException;
import org.money.transfer.exception.InsufficientMoneyException;
import org.money.transfer.exception.InvalidValuesException;

@Path("account")
public class AccountService {

	@Inject
	private AccountBO businessOb;

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AccountDTO create(AccountDTO accDto) {
		return businessOb.creatAccount(accDto);
	}

	@GET
	@Path("/{accountNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public AccountDTO create(@PathParam("accountNumber") Long accountNumber) throws AccountNotFoundException {
		return businessOb.findAccountByAcNumber(accountNumber);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/transfer")
	public Response transfer(AccountTransferDTO accTransfer)
			throws AccountNotFoundException, InsufficientMoneyException, InvalidValuesException {
		businessOb.transfer(accTransfer);
		return Response.status(Status.OK).entity("sucess").build();
	}
}
