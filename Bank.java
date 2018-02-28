import java.util.ArrayList;

public class Bank /*extends ATMSystem*/{
	private String bank_id;
	/*private int atmACounter;
	private int atmBCounter;*/

	
	public Bank(String bank_id) {
		this.bank_id = bank_id;
	}
	
	public String getBankID() {
		return this.bank_id;
	}
}
