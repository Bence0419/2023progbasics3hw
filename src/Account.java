import java.io.Serializable;
import java.util.Random;



public class Account extends User{
	
	// ----------------------------------------
	//ATTRIBUTUMOK
	// ----------------------------------------
	
	private String IBAN; //international bank account number 16jegyu bankszamla szam
	private double money; //jelenlegi osszeg a szamlan
	
	// ----------------------------------------
	//KONSTRUKTOROK
	// ----------------------------------------
	
	public Account()
	{
		super("","","");
		this.money = 0;
	}
	
	public Account(String name,String user, String pw)
	{
		super(name,user,pw);
		this.money = 0;
	}
	
	// ----------------------------------------
	//GETTEREK
	// ----------------------------------------
	
	public String getName()
	{
		return super.getName();
	}
	
	public String getUsername()
	{
		return super.getUsername();
	}
	
	public String getPassword()
	{
		return super.getPassword();
	}
	
	public double getMoney()
	{
		return this.money;
	}
	
	public String getIBAN()
	{
		return IBAN;
	}
	
	// ----------------------------------------
	//SETTEREK
	// ----------------------------------------
	
	public void setMoney(double d)
	{
		this.money = d;
	}
	
	
	public void setIBAN(String d)
	{
		this.IBAN = d;
	}
	
	// ----------------------------------------
	//KIIRATAS A TOSTRING SEGITSEGEVEL
	// ----------------------------------------
	
	// public String toString()
	// {
	// 	String hiddenIBAN = this.IBAN
	// 	for(int i = 3; i<hiddenIBAN.length()-4;i++)
	// 	{
	// 		hiddenIBAN.replace(hiddenIBAN.charAt(i),'X');
	// 	}
	// 	String text = super.getName() + " " + hiddenIBAN;
	// 	return text;
	// }

}