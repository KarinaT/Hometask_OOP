package src.com.epam.mentoring.menu;

import java.util.ArrayList;
import java.util.List;

import src.com.epam.mentoring.domain.Bank;
import src.com.epam.mentoring.interfaces.IBank;

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
		for (IBank bank : this.banks) {
			if (bank.getName().equalsIgnoreCase(bankName)) {
				return bank;
			}
		}
		return null;
	}

	public String openAccount(IBank bank, int accountId,
			IBank.IAccount.AccountType type, int money) {
		bank.createAccount(accountId, type, money);
		return "Success!!";		
	}

	public List<IBank.IAccount> getAllBankAccounts() {
		List<IBank.IAccount> result = new ArrayList<>();
		for (IBank bank : this.banks) {
			result.addAll(bank.getAllAccounts());
		}
		return result;
	}

	public List<IBank> getBanks() {
		return banks;
	}
}
