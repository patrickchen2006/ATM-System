import java.util.Scanner;

public class ATM extends Bank{

	private int maxWithdraw;
	private String name;
	
	public ATM(String bank_id, int maxWithdraw, String name){
		super(bank_id);
		this.maxWithdraw = maxWithdraw;
		this.name = name;
	}
	
	public int getMaxWithdraw()	{
		return this.maxWithdraw;
	}
	
	public String getAtmName() {
		return this.name;
	}
}
