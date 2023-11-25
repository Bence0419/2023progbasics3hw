import java.io.Serializable;
import java.util.Random;
import java.util.ArrayList;



public class Account extends User{
	
	// ----------------------------------------
	//ATTRIBUTUMOK
	// ----------------------------------------
	
	private String IBAN; //international bank account number HU (országkód) 42(ellenőrző szám) 123(bank kód) 111111111 0000000 (16 számjegyú bankszámlaszám) formátumban
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


}