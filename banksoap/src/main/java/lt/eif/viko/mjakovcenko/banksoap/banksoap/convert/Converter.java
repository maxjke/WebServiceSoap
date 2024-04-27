package lt.eif.viko.mjakovcenko.banksoap.banksoap.convert;

import lt.eif.viko.mjakovcenko.banksoap.banksoap.model.Account;
import lt.eif.viko.mjakovcenko.banksoap.banksoap.model.BankAccount;
import lt.eif.viko.mjakovcenko.banksoap.banksoap.model.CreditCard;
import lt.eif.viko.mjakovcenko.banksoap.banksoap.model.Loan;
import lt.viko.eif.mj.springsoap.gen.*;

import java.util.List;

public class Converter {


        public AccountType mapAccount(Account account) {
            AccountType accountType = new AccountType();
            accountType.setPassword(account.getPassword());
            accountType.setUsername(account.getUsername());
            return accountType;
        }

        public BankAccountListType mapBankAccountList(List<BankAccount> bankAccounts) {
            BankAccountListType bankAccountListType = new BankAccountListType();
            bankAccounts.forEach(bankAccount -> bankAccountListType.getBankAccount().add(mapBankAccount(bankAccount)));
            return bankAccountListType;
        }

    public BankAccountType mapBankAccount(BankAccount bankAccount) {
            BankAccountType bankAccountType = new BankAccountType();
            bankAccountType.setBalance(bankAccount.getBalance());
            bankAccountType.setCurrency(bankAccount.getCurrency());
            return bankAccountType;
        }

    public CreditCardListType mapCreditCardList(List<CreditCard> creditCards) {
            CreditCardListType creditCardListType = new CreditCardListType();
            creditCards.forEach(creditCard -> creditCardListType.getCreditCard().add(mapCreditCard(creditCard)));
            return creditCardListType;
        }

    public CreditCardType mapCreditCard(CreditCard creditCard) {
            CreditCardType creditCardType = new CreditCardType();
            creditCardType.setCVC(creditCard.getCVC());
            creditCardType.setCardLimit(creditCard.getCardLimit());
            creditCardType.setCardNumber(creditCard.getCardNumber());
            creditCardType.setExpireDate(creditCard.getExpireDate());
            return creditCardType;
        }

    public LoanListType mapLoanList(List<Loan> loans) {
            LoanListType loanListType = new LoanListType();
            loans.forEach(loan -> loanListType.getLoan().add(mapLoan(loan)));
            return loanListType;
        }

    public LoanType mapLoan(Loan loan) {
            LoanType loanType = new LoanType();
            loanType.setLoanAmount(loan.getLoanAmount());
            loanType.setLoanEndDate(loan.getLoanEndDate());
            loanType.setLoanPercent(loan.getLoanPercent());
            loanType.setLoanStartDate(loan.getLoanStartDate());
            loanType.setMonthlyPayment(loan.getMonthlyPayment());
            return loanType;
        }
    }


