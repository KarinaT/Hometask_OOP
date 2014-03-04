package src.com.epam.mentoring.main;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.com.epam.mentoring.interfaces.IBank;
import src.com.epam.mentoring.interfaces.IBank.IAccount;
import src.com.epam.mentoring.menu.BussinesLogic;
import src.com.epam.mentoring.util.MoneyException;

public class KeyBoardLogic {
	
	public static void main(String[] args){
		start(System.in, new BussinesLogic());
	}
	public static void start(InputStream is, BussinesLogic bussinesLogic) {
		Scanner scan = new Scanner(is);
		String input = null;
		while (true) {				
			printOperations();
			input = scan.nextLine();
			switch (input) {
			case "1": openBankAccount(scan, bussinesLogic);break;
			case "2": getAllAccounts(scan, bussinesLogic);break;
			case "Q": System.out.println("Good bye!"); return;
			default:  System.out.println("Invalid input!"); break;
			}
		}
	}
	
	private static IBank getBank(Scanner scan, BussinesLogic bussinesLogic){
		String input = null; 
		printAllBanks(bussinesLogic);
		IBank bank = null;
		while (bank==null) {
			input = scan.nextLine();
			try{
				bank = bussinesLogic.getBanks().get(Integer.parseInt(input)-1);
			}catch(NumberFormatException exception){
				System.out.println("Incorrect input!");				
			}
			if(bank!=null){
				System.out.println("You've chosen " + bank.getName());
			}else{
				System.out.println("Such name doesn't exist!");
			}
		}
		return bank;
	}
	
	private static void printAllBanks(BussinesLogic bussinesLogic) {
		StringBuilder builder = new StringBuilder();
		builder.append("==== Please, choose a bank.");
		int count = 1;
		for (IBank bank : bussinesLogic.getBanks()) {
			builder.append("Press ").append(count++).append(" for ")
					.append(bank.getName()).append("; ");
		}
		builder.append(" ====");
		System.out.println(builder);
	}
	
	private static void printOperations() {
		System.out.println("Please choose operation: ");
		System.out.println("Press 1 to open a new account");
		System.out.println("Press 2 to get all accounts");
		System.out.println("Press Q to quite");

	} 
	
	private static void openBankAccount(Scanner scan, BussinesLogic bussinesLogic) {
		IBank bank = null;
		String input = null;
		if(bank == null){			
			bank = getBank(scan, bussinesLogic);			
		}
		printAllAccounts(bussinesLogic);
		input = scan.nextLine();
		if(input.equalsIgnoreCase("CHECKING")){
			System.out.println("You've chosen " + IBank.IAccount.AccountType.CHECKING);
			System.out.println("Please, put some money: ");
			int money = Integer.parseInt(scan.nextLine());
			int accountId = bank.getAllAccounts().size();
			try{
			bussinesLogic.openAccount(bank, accountId, IBank.IAccount.AccountType.CHECKING, money);		
			}catch(MoneyException ex){
				System.out.println("Money can't be negative!");
			}
		} else if(input.equalsIgnoreCase("CREDIT")){
			System.out.println("You've chosen " + IBank.IAccount.AccountType.CREDIT);
			System.out.println("Please, put some money: ");
			int money = Integer.parseInt(scan.nextLine());
			int accountId = bank.getAllAccounts().size();
			try{
			bussinesLogic.openAccount(bank, accountId, IBank.IAccount.AccountType.CREDIT, money);
			}catch(MoneyException ex){
				System.out.println("Money can't be negative!");
			}
		} else if(input.equalsIgnoreCase("DEPOSIT")){
			System.out.println("You've chosen " + IBank.IAccount.AccountType.DEPOSIT);
			System.out.println("Please, put some money: ");
			int money = Integer.parseInt(scan.nextLine());
			int accountId = bank.getAllAccounts().size();
			try{
			bussinesLogic.openAccount(bank, accountId, IBank.IAccount.AccountType.DEPOSIT, money);			
			}catch(MoneyException ex){
				System.out.println("Money can't be negative!");
			}
		} else{
			System.out.println("Wrong input! Try again, please. ");
		}
	}
	
	private static void printAllAccounts(BussinesLogic bussinesLogic) {
		System.out.println("Please, enter "+IBank.IAccount.AccountType.CREDIT+" to choose " + IBank.IAccount.AccountType.CREDIT + " account type, "+IBank.IAccount.AccountType.CHECKING+" for " + IBank.IAccount.AccountType.CHECKING + " account type or "+IBank.IAccount.AccountType.DEPOSIT+" for " + IBank.IAccount.AccountType.DEPOSIT +  " account type");	 
	}
	
	private static void getAllAccounts(Scanner scan, BussinesLogic bussinesLogic) {
		List<IAccount> accounts = new ArrayList<>();
		accounts = bussinesLogic.getAllBankAccounts();
		if(accounts.size()>0){
		System.out.println(" === There are your bank accounts: === \n"+ accounts);	
		}else{
			System.out.println(" === Create at least one account first ====");
		}
	}
}
