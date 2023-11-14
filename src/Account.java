import java.util.Random;



public class Account extends User{
	
	// ----------------------------------------
	//ATTRIBUTUMOK
	// ----------------------------------------
	
	private long IBAN; //international bank account number 117es szammal kezdve
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
	
	public long getIBAN()
	{
		return IBAN;
	}
	
	// ----------------------------------------
	//SETTEREK
	// ----------------------------------------
	
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
		IBAN = generateIbanAsLong(117, 13);
	}
	
	// ----------------------------------------
	//KIIRATAS A TOSTRING SEGITSEGEVEL
	// ----------------------------------------
	
	public String toString()
	{
		String hiddenIBAN = Long.toString(this.IBAN);
		for(int i = 3; i<hiddenIBAN.length()-4;i++)
		{
			hiddenIBAN.replace(hiddenIBAN.charAt(i),'X');
		}
		String text = super.getName() + " " + hiddenIBAN;
		return text;
	}

	public static long generateIbanAsLong(int prefix, int length) {
        // Ellenőrizzük, hogy a prefix szám-e
        if (prefix < 0) {
            throw new IllegalArgumentException("A prefixnek pozitív számnak kell lennie.");
        }

        // Konvertáljuk a prefixet stringgé és számjegyek számát számoljuk meg
        String prefixStr = Integer.toString(prefix);
        int prefixDigits = prefixStr.length();

        // Számoljuk meg, hány véletlenszerű számot kell még hozzáadni
        int remainingDigits = length - prefixDigits;

        // Generáljuk a véletlenszerű számokat és fűzzük össze az IBAN-t
        StringBuilder ibanBuilder = new StringBuilder(prefixStr);
        Random random = new Random();

        // Ellenőrizzük, hogy az első szám ne legyen nulla
        int firstDigit = random.nextInt(9) + 1;
        ibanBuilder.append(firstDigit);

        for (int i = 1; i < remainingDigits; i++) {
            int randomDigit = random.nextInt(10);
            ibanBuilder.append(randomDigit);
        }

        // Konvertáljuk az IBAN-t long típusba
        return Long.parseLong(ibanBuilder.toString());
    }
}