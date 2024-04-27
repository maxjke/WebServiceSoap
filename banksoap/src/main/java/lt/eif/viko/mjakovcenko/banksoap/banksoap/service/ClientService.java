package lt.eif.viko.mjakovcenko.banksoap.banksoap.service;

import lt.eif.viko.mjakovcenko.banksoap.banksoap.convert.Converter;
import lt.eif.viko.mjakovcenko.banksoap.banksoap.model.Account;
import lt.eif.viko.mjakovcenko.banksoap.banksoap.model.BankAccount;
import lt.eif.viko.mjakovcenko.banksoap.banksoap.model.Client;
import lt.eif.viko.mjakovcenko.banksoap.banksoap.repository.ClientJpaRepository;
import lt.viko.eif.mj.springsoap.gen.BankAccountListType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Service class for managing client-related operations.
 * This class provides services to query and transform client and account data from the repository layer
 * into DTO for web service communication.
 */
@Service
public class ClientService {

    @Autowired
    private ClientJpaRepository clientJpaRepository;
    public Converter con;
    @Autowired
    private ModelMapper modelMapper;
    /**
     * Finds a client by their first name.
     *
     * @param name the name of the client to find
     * @return an Optional containing the client if found, otherwise an empty Optional
     */
    public Optional<lt.viko.eif.mj.springsoap.gen.Client> findClient(String name) {
        return clientJpaRepository.findAll().stream()
                .filter(client -> client.getFirstName().equals(name))
                .findFirst()
                .map(client -> convertToDto(client, lt.viko.eif.mj.springsoap.gen.Client.class));
    }

    public Optional<lt.viko.eif.mj.springsoap.gen.Client> findClient2(String name) {
        Converter converter = new Converter();
        return clientJpaRepository.findAll().stream()
                .filter(client -> client.getFirstName().equals(name))
                .findFirst()
                .map(client -> {
                    lt.viko.eif.mj.springsoap.gen.Client clientType = new lt.viko.eif.mj.springsoap.gen.Client();
                    clientType.setFirstName(client.getFirstName());
                    clientType.setLastName(client.getLastName());
                    if (client.getAccount() != null) {
                        clientType.setAccount(converter.mapAccount(client.getAccount()));
                    }
                    if (client.getBankAccountList() != null) {
                        clientType.setBankAccountList(converter.mapBankAccountList(new ArrayList<>(client.getBankAccountList())));
                    }
                    if (client.getLoanList() != null) {
                        clientType.setLoanList(converter.mapLoanList(new ArrayList<>(client.getLoanList())));
                    }
                    if (client.getCreditCardList() != null) {
                        clientType.setCreditCardList(converter.mapCreditCardList(new ArrayList<>(client.getCreditCardList())));
                    }
                    return clientType;
                });
    }
    /**
     * Finds an account by username.
     *
     * @param username the username associated with the account to find
     * @return an Optional containing the AccountType DTO if found, otherwise an empty Optional
     */
    public Optional<lt.viko.eif.mj.springsoap.gen.AccountType> findAccountByUserName(String username) {
        return clientJpaRepository.findAll().stream()
                .map(Client::getAccount)
                .filter(account -> account != null && username.equals(account.getUsername()))
                .map(account -> convertToDto(account,lt.viko.eif.mj.springsoap.gen.AccountType.class)) // Convert domain model to JAXB type
                .findFirst();
    }
    /**
     * Finds a bank account by currency.
     *
     * @param currency the currency of the bank account to find
     * @return an Optional containing the BankAccountType DTO if found, otherwise an empty Optional
     */
    public Optional<lt.viko.eif.mj.springsoap.gen.BankAccountType> findBankAccountByCurrency(String currency) {
        return clientJpaRepository.findAll().stream()
                .flatMap(client -> client.getBankAccountList().stream())  // Assuming getBankAccountList returns List<BankAccount>
                .filter(bankAccount -> bankAccount != null && currency.equals(bankAccount.getCurrency()))
                .map(bankAccount -> convertToDto(bankAccount, lt.viko.eif.mj.springsoap.gen.BankAccountType.class))
                .findFirst();
    }
    /**
     * Finds a credit card by its CVC code.
     *
     * @param cvc the CVC code of the credit card to find
     * @return an Optional containing the CreditCardType DTO if found, otherwise an empty Optional
     */
    public Optional<lt.viko.eif.mj.springsoap.gen.CreditCardType> findCreditCardByCvc(String cvc){
        return clientJpaRepository.findAll().stream()
                .flatMap(client -> client.getCreditCardList().stream())
                .filter(creditCard -> creditCard != null&& cvc.equals(creditCard.getCVC()))
                .map(creditCard -> convertToDto(creditCard, lt.viko.eif.mj.springsoap.gen.CreditCardType.class)).findFirst();
    }
    /**
     * Finds a loan by its end date.
     *
     * @param loanEndDate the end date of the loan to find
     * @return an Optional containing the LoanType DTO if found, otherwise an empty Optional
     */
    public Optional<lt.viko.eif.mj.springsoap.gen.LoanType> findLoanByLoanEndDate(String loanEndDate){
        return clientJpaRepository.findAll().stream()
                .flatMap(client -> client.getLoanList().stream())
                .filter(loan -> loan !=null&& loanEndDate.equals(loan.getLoanEndDate()))
                .map(loan -> convertToDto(loan, lt.viko.eif.mj.springsoap.gen.LoanType.class)).findFirst();
    }
    /**
     * Converts a source object to a target class DTO.
     *
     * @param <S> the source object type
     * @param <T> the target DTO class type
     * @param source the source object to convert
     * @param targetClass the target DTO class
     * @return the converted DTO of the target class
     */
    private <S, T> T convertToDto(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }
}


