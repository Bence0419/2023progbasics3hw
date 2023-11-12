import java.util.Random;



public class Account {
	
	// ----------------------------------------
	//ATTRIBUTUMOK
	// ----------------------------------------
	
	private String name; //anyakonyvezett nev
	private int IBAN; //international bank account number 117es szammal kezdve
	private String username; //bejelentkezesi felhasznalonev	
	private String password; //bejelentkezesi jelszo
	private int money; //jelenlegi osszeg a szamlan
	
	// ----------------------------------------
	//KONSTRUKTOROK
	// ----------------------------------------
	
	public Account()
	{
		this.name = "";
		this.IBAN = 117;
		this.username = "";
		this.password = "";
		this.money = 0;
	}
	
	public Account(String name,String user, String pw)
	{
		this.money = 0;
		this.name = name;
		this.username = user;
		this.password = pw;
		this.IBAN = 117;
		setIBAN();
	}
	
	// ----------------------------------------
	//GETTEREK
	// ----------------------------------------
	
	public String getName()
	{
		return this.name;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public String getPassword()
	{
		return this.password;
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
		this.name = s;
	}
	
	public void setUsername(String s)
	{
		this.username = s;
	}
	
	public void setPassword(String s)
	{
		this.password = s;
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
