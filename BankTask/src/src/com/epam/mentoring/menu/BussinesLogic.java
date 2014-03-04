package src.com.epam.mentoring.menu;

import java.util.ArrayList;
import java.util.List;

import src.com.epam.mentoring.domain.Bank;
import src.com.epam.mentoring.interfaces.IBank;
import src.com.epam.mentoring.util.MoneyException;

public class BussinesLogic {

	private static final int ACCOUNTS_LIMIT = 3;
	private List<IBank> banks;

	public BussinesLogic() {
		this.banks = new ArrayList<>();
		this.banks.add(new Bank("Bank1", ACCOUNTS_LIMIT));
		this.banks.add(new Bank("Bank2", ACCOUNTS_LIMIT));
		this.banks.add(new Bank("Bank3", ACCOUNTS_LIMIT));
	}

	public IBank getBank(String bankName) {
		for (IBank bank : banks) {
			if (bank.getName().equalsIgnoreCase(bankName)) {
				return bank;
			}
		}
		return null;
	}

	public String openAccount(IBank bank, int accountId,
			IBank.IAccount.AccountType type, int money) throws MoneyException {
		if(money>0){
		System.out.println("You've put " + money);
		bank.createAccount(accountId, type, money);		
		return "Success!!";		
		} else{
			throw new MoneyException("Money must be positive!");
		}
	}

	public List<IBank.IAccount> getAllBankAccounts() {
		List<IBank.IAccount> result = new ArrayList<>();
		for (IBank bank : banks) {
			result.addAll(bank.getAllAccounts());
		}
		return result;
	}

	public List<IBank> getBanks() {
		return banks;
	}
}
