import java.text.*;
import java.util.*;

public class BankAccount extends Bank {
	private int accountNumber;
	private int accountTotal = 40;
	private Date expirationDate;
	private String password;

	public BankAccount(String bank_id, int accountNumber, String expirationDate, String password) {
		super(bank_id);
		this.accountNumber = accountNumber;
		this.password = password;
		DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		try {
			Date date = format.parse(expirationDate);
			this.expirationDate = date;
		} catch (ParseException e) {
			System.out.println("Unparseable using " + format);
		}
	}

	public int getAccountNumber() {
		return this.accountNumber;
	}

	public int getAccountTotal() {
		return this.accountTotal;
	}

	public Date getExpirationDate() {
		return this.expirationDate;
	}

	public String getPassword() {
		return this.password;
	}

	public String getName() {
		return this.getBankID() + Integer.toString(this.accountNumber);
	}
	
	public void withdraw(int amt) {
		this.accountTotal -= amt;
	}
}
