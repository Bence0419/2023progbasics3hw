import java.util.Random;



public class Account extends User{
	
	// ----------------------------------------
	//ATTRIBUTUMOK
	// ----------------------------------------
	
	private int IBAN; //international bank account number 117es szammal kezdve
	private int money; //jelenlegi osszeg a szamlan
	
	// ----------------------------------------
	//KONSTRUKTOROK
	// ----------------------------------------
	
	public Account()
	{
		super("","","");
		this.IBAN = 117;
		this.money = 0;
	}
	
	public Account(String name,String user, String pw)
	{
		super(name,user,pw);
		this.money = 0;
		this.IBAN = 117;
		setIBAN();
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
	
	public int getMoney()
	{
		return this.money;
	}
	
	public int getIBAN()
	{
		return this.IBAN;
	}
	
	// ----------------------------------------
	//SETTEREK
	// ----------------------------------------
	
	public void setName(String s)
	{
		super.setName(s);
	}
	
	public void setUsername(String s)
	{
		super.setUsername(s);
	}
	
	public void setPassword(String s)
	{
		super.setPassword(s);
	}
	
	public void setMoney(int d)
	{
		this.money = d;
	}
	
	
	public void setIBAN(int d)
	{
		this.IBAN = d;
	}
	
	
	public void setIBAN()
	{
		String ibanstring = Integer.toString(this.IBAN);
		for(int i = 0; i<16; i++)
		{
			Random rand = new Random();
			int val = rand.nextInt();
			Integer.toString(val);
			ibanstring+=val;
		}
		this.IBAN = Integer.parseInt(ibanstring);
	}
	
	// ----------------------------------------
	//KIIRATAS A TOSTRING SEGITSEGEVEL
	// ----------------------------------------
	
	public String toString()
	{
		String hiddenIBAN = Integer.toString(this.IBAN);
		for(int i = 3; i<hiddenIBAN.length()-4;i++)
		{
			hiddenIBAN.replace(hiddenIBAN.charAt(i),'X');
		}
		String text = this.name + " " + hiddenIBAN;
		return text;
	}
}
