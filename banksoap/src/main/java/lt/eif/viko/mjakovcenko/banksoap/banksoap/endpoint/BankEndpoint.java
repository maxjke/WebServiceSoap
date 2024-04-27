package lt.eif.viko.mjakovcenko.banksoap.banksoap.endpoint;

import lt.eif.viko.mjakovcenko.banksoap.banksoap.service.ClientService;
import lt.viko.eif.mj.springsoap.gen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.util.Base64;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;


/**
 * Endpoint class for client-related SOAP web service operations.
 */
@Endpoint
public class BankEndpoint {

    private static final String NAMESPACE_URI = "http://eif.viko.lt/mj/springsoap/gen";

    @Autowired
    private ClientService clientService;

    /**
     * Retrieves client information based on client's name.
     *
     * @param request The SOAP request containing the client's name.
     * @return A SOAP response containing the client information.
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getClientRequest")
    @ResponsePayload
    public GetClientResponse getClient(@RequestPayload GetClientRequest request) {
        GetClientResponse response = new GetClientResponse();
        Optional<lt.viko.eif.mj.springsoap.gen.Client> clientOptional = clientService.findClient2(request.getName());
        clientOptional.ifPresent(response::setClient);
        return response;
    }
    /**
     * Retrieves the account details of a client based on their username.
     *
     * @param request The SOAP request containing the username.
     * @return A SOAP response containing the account details.
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getClientAccountRequest")
    @ResponsePayload
    public GetClientAccountResponse getAccount(@RequestPayload GetClientAccountRequest request) {
        GetClientAccountResponse response = new GetClientAccountResponse();
        Optional<lt.viko.eif.mj.springsoap.gen.AccountType> accountOptional = clientService.findAccountByUserName(request.getUsername());

        if (accountOptional.isPresent()) {
            response.setAccount(accountOptional.get());
        } else {

        }
        return response;
    }
    /**
     * Retrieves the bank account details of a client based on currency type.
     *
     * @param request The SOAP request containing the currency.
     * @return A SOAP response containing the bank account details.
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getClientBankAccountRequest")
    @ResponsePayload
    public GetClientBankAccountResponse getClientBankAccountResponse(@RequestPayload GetClientBankAccountRequest request) {
        GetClientBankAccountResponse response = new GetClientBankAccountResponse();
        Optional<lt.viko.eif.mj.springsoap.gen.BankAccountType> bankAccountTypeOptional = clientService.findBankAccountByCurrency(request.getCurrency());

        if (bankAccountTypeOptional.isPresent()) {
            response.setBankAccount(bankAccountTypeOptional.get());
        } else {

        }
        return response;
    }
    /**
     * Retrieves the credit card details of a client based on CVC.
     *
     * @param request The SOAP request containing the CVC.
     * @return A SOAP response containing the credit card details.
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getClientCreditCardRequest")
    @ResponsePayload
    public GetClientCreditCardResponse getClientCreditCardResponse(@RequestPayload GetClientCreditCardRequest request){
        GetClientCreditCardResponse response = new GetClientCreditCardResponse();
        Optional<lt.viko.eif.mj.springsoap.gen.CreditCardType> creditCardTypeOptional = clientService.findCreditCardByCvc(request.getCVC());

        if(creditCardTypeOptional.isPresent()){
            response.setCreditCard(creditCardTypeOptional.get());
        }else{

        }

        return response;
    }
    /**
     * Retrieves loan details of a client based on the loan end date.
     *
     * @param request The SOAP request containing the loan end date.
     * @return A SOAP response containing the loan details.
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getClientLoan")
    @ResponsePayload
    public GetClientLoanResponse getClientLoanResponse(@RequestPayload GetClientLoanRequest request) {
        GetClientLoanResponse response = new GetClientLoanResponse();
        Optional<lt.viko.eif.mj.springsoap.gen.LoanType> loanTypeOptional = clientService.findLoanByLoanEndDate(request.getLoanEndDate());

        if (loanTypeOptional.isPresent()) {
            response.setLoan(loanTypeOptional.get());
        } else {

        }
        return response;
    }

}





