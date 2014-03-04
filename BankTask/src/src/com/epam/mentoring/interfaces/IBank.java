package src.com.epam.mentoring.interfaces;

import java.util.List;

import src.com.epam.mentoring.util.MoneyException;

public interface IBank {

	void createAccount(int id, IBank.IAccount.AccountType accountType, int money);

	IBank.IAccount getAccount(int id);

	List<IBank.IAccount> getAllAccounts();

	String getName();

	interface IAccount {

		int getId();

		AccountType getAccountType();

		void deposit(int amount) throws MoneyException;

		int withdraw(int amount) throws MoneyException;

		int check();

		enum AccountType {
			CREDIT, DEPOSIT, CHECKING
		}
	}
}
