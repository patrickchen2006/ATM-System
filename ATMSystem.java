import java.util.*;

public class ATMSystem {
	private ArrayList<Bank> bankList = new ArrayList<Bank>();
	private ArrayList<BankAccount> bankAccountList = new ArrayList<BankAccount>();
	private ArrayList<ATM> atmList = new ArrayList<ATM>();

	public ATMSystem() {
		Bank bankA = new Bank("A");
		Bank bankB = new Bank("B");
		ATM ATM1_A = new ATM("A", 50, "ATM1_A");
		ATM ATM2_A = new ATM("A", 50, "ATM2_A");
		ATM ATM1_B = new ATM("B", 50, "ATM1_B");
		ATM ATM2_B = new ATM("B", 50, "ATM2_B");
		this.atmList.add(ATM1_A);
		this.atmList.add(ATM2_A);
		this.atmList.add(ATM1_B);
		this.atmList.add(ATM2_B);
		this.bankList.add(bankA);
		this.bankList.add(bankB);
		BankAccount cust1 = new BankAccount("A", 11, "January 1, 2018", "abc");
		BankAccount cust2 = new BankAccount("A", 12, "March 1, 2018", "abc");
		BankAccount cust11 = new BankAccount("B", 111, "April 1, 2018", "abc");
		BankAccount cust22 = new BankAccount("B", 122, "December 1, 2018", "abc");
		this.bankAccountList.add(cust1);
		this.bankAccountList.add(cust2);
		this.bankAccountList.add(cust11);
		this.bankAccountList.add(cust22);
	}

	// public int openMenu() {
	// Scanner input = new Scanner(System.in);
	// System.out.println(
	// "Would you like to:\n1. Open a new account in Bank A\n2.Open a new account in
	// Bank B\n3. Make a withdrawal");
	// int selection1 = input.nextInt();
	// return selection1;
	// }

	public void printStatus1(Bank bank) {
		System.out.println("Bank of " + bank.getBankID() + " (" + bankAccountList.size() + " customers)");
		for (BankAccount a : bankAccountList) {
			System.out.print(
					"Customer with Cash Card (bankid: " + a.getBankID() + ", account number (" + a.getAccountNumber());
			System.out.println("), expires on " + a.getExpirationDate() + ", password" + a.getPassword());
		}
	}

	public void printStatus2() {
		for (ATM a : atmList) {
			System.out.println(a.getAtmName() + ": " + a.getAtmName() + " from Bank of " + a.getBankID());
			System.out.println("\tThe maximum amount of cash a card can withdraw per day: $" + a.getMaxWithdraw());
		}
	}

	public BankAccount getBankAccount(String acc) {
		// System.out.println(acc);
		// System.out.println(acc == "A11");
		// System.out.println((new BankAccount("A", 11, "January 1, 2018",
		// "abc")).getName());
		// System.out.println(acc.equals((new BankAccount("A", 11, "January 1, 2018",
		// "abc")).getName()));
		for (BankAccount a : bankAccountList) {
			if (acc.equals(a.getName())) {
				return a;
			}
			// System.out.println(a.getName());

		}
		return null;
	}

	public ATM getATM(String atm) {
		for (ATM a : atmList) {
			if (atm.equals(a.getAtmName())) {
				return a;
			}
		}
		return null;
	}

	public boolean isExpired(BankAccount a) {
		Date today = new Date();
		if (a.getExpirationDate().before(today)) {
			return true;
		}

		return false;
	}

	public BankAccount checkCard(Scanner input, ATM atm) {
		while (true) {
			System.out.println("Enter your card");
			String atmCard = input.nextLine();
			// bankAccountList.get(1).getName();

			BankAccount bankacc = this.getBankAccount(atmCard);
			// System.out.println(bankacc.getName());
			if (this.isExpired(bankacc)) {
				System.out.println("This card is expired and returned to you.");
				continue;
			} else if (!bankacc.getBankID().equals(atm.getBankID())) {
				System.out.println("This card is not supported by this ATM.");
				continue;
			}
			return bankacc;
		}
	}

	public void checkPassword(Scanner input, BankAccount bankacc) {
		while (!bankacc.getPassword().equals(input.nextLine())) {
			System.out.println("This is a wrong password. Enter your password.");
		}
	}

	public void checkWithdrawal(Scanner input, BankAccount bankacc, ATM atm) {
        System.out.println("Authorization is accepted. Start your transaction by entering the amount to withdraw.");
        this.promptTransaction(input, bankacc, atm);
        while(true) {
            System.out.println("Would you like to quit? Please enter yes or no.");
            String yn = input.nextLine();
            if(yn.equals("yes")) {
                this.promptTransaction(input,bankacc,atm);
                continue;
            } else {
                break;
            }
        }
    }

    public void promptTransaction(Scanner input, BankAccount bankacc, ATM atm) {
        System.out.print("Please enter the amount to withdraw: ");
        int withdrawAmt = input.nextInt();
        if (withdrawAmt > atm.getMaxWithdraw()) {
            System.out.println( "This amount exceeds the maximum amount you can withdraw per transaction. Please enter the amount or quit.");
        } else if (withdrawAmt > bankacc.getAccountTotal()) {
            System.out.println("The amount exceeds the current balance. Enter another amount or quit.");
        } else {
            bankacc.withdraw(withdrawAmt);
            System.out.println("$" + withdrawAmt + " is withdrawn from  your account. The remaining balance of this account is $"
                                    + bankacc.getAccountTotal() + ".");
        }
    }
	public void checkCustomer() {
		Scanner input = new Scanner(System.in);

		System.out.println("Enter your choice of ATM");
		String atmChoice = input.nextLine();
		ATM atm = this.getATM(atmChoice);

		BankAccount bankacc = this.checkCard(input, atm);
		System.out.println("The card is accepted. Please enter your password.");
		this.checkPassword(input, bankacc);
		this.checkWithdrawal(input, bankacc, atm);
		
//		int amount = this.checkWithdrawal(input, bankacc, atm);
//		bankacc.withdraw(amount);
//		System.out.println(
//				"$" + amount + " is withdrawn from  your account. The remaining balance of this account is $"
//						+ bankacc.getAccountTotal()); // + ". If you have more transactions, enter the amount or
//														// quit.");
//		System.out.println("If you wish to quit type \"quit\", else how much will you like to withdraw?");
//		String response = input.nextLine();
//		if (response.equals("quit")) {
//			return;
//		}
	}

	public void output() {
		System.out.println("States of two Banks\n\nAssume all accounts have $40 preloaded.");
		for (Bank a : bankList) {
			printStatus1(a);
		}

		System.out.println("States of four ATMs (2 for each Bank)");
		printStatus2();

		System.out.println("---------------------------------------------------------------------");
		System.out.println("Now, your program is in an interactive mode. % means the prompt on your cmd (shell)");
	}

	public static void main(String args[]) {
		ATMSystem sys = new ATMSystem();
		// sys.getBankAccount("A11");
		// sys.isExpired(new BankAccount("B", 111, "April 1, 2018", "abc"));
		sys.output();
		sys.checkCustomer();
	}

}
