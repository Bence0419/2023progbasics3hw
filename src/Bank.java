import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Bank {
	
	// ----------------------------------------
	//ATTRIBUTUMOK
	// ----------------------------------------
	
	public static ArrayList<Account> accounts = new ArrayList<Account>(); //A fiokok tarolasara hasznalt kontener
	public static ArrayList<Admin> admins = new ArrayList<Admin>();
	
	// ----------------------------------------
	//METODUSOK
	// ----------------------------------------
	
	
	public void addAccount(Account account)
	{
		account.setIBAN(generateIban());
		accounts.add(account);
	}
	
	public void list() //Az arraylist elemeinek listazasa, kizarolag a teszteles celjabol
	{
		for(int i=0; i<accounts.size();i++) 
		{
			System.out.println(accounts.get(i).toString());
		}
	}
	
	public static void loadAccountData(String filename) {  //Az arraylist feltoltese a fajlba mar meglevo elemekkel
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 5) {
                    String name = parts[0];
                    int iban = Integer.parseInt(parts[1]);
                    String username = parts[2];
                    String password = parts[3];
                    int money = Integer.parseInt(parts[4]);

                    Account account = new Account();
                    account.setName(name);
                    account.setIBAN(iban);
                    account.setUsername(username);
                    account.setPassword(password);
                    account.setMoney(money);
                    accounts.add(account);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static boolean login(String username, String password)
	{
		for(Account account : accounts)
		{
			if(account.getUsername().equals(username)&&account.getPassword().equals(password))
			{
				return true;
			}
		}
		return false;
	}

	public static boolean isAdmin(String username, String password)
	{
		for(Admin admin : admins)
		{
			if(admin.getUsername().equals(username)&&admin.getPassword().equals(password))
			{
				return true;
			}
		}
		return false;
	}

	public Account findAccount(String username, String password)
	{
		for(Account account : accounts)
		{
			if(account.getUsername().equals(username)&&account.getPassword().equals(password))
			{
				return account;
			}
		}
		return null;
	}

	public static void transferMoney(Account from, Account to, double money)
	{
		from.setMoney(from.getMoney()-money);
		to.setMoney(to.getMoney()+money);
	}

	public Account findByIBAN(String IBAN)
	{
		long iban = Long.parseLong(IBAN);
		for (Account account : accounts)
		{
			if(account.getIBAN() == iban)
			{
				return account;
			}
		}
		return null;
	}



	public static void changePassword(String older, String newer)
	{
		for(int i = 0; i<accounts.size(); i++)
		{
			if(accounts.get(i).getPassword().equals(older))
			{
				accounts.get(i).setPassword(newer);
				System.out.println("A jelszo megvaltozott!");
				return;
			}
		}
		System.out.println("Helytelen regi jelszo");
	}

	public static boolean isUsedIban(long iban)
	{
		for(Account account : accounts)
		{
			if(account.getIBAN() == iban)
			{
				return true;
			}
		}
		return false;
	}

	public static long generateIban()
	{
		Random random = new Random();
		while(true)
		{
			long iban = accounts.get(0).getIBAN();
			if(!isUsedIban(iban))
			{
				return iban;
			}
			else
			{
				StringBuilder randomNumberBuilder = new StringBuilder();
				for (int i = 0; i < 16; i++) {
					int digit = random.nextInt(10); // Véletlenszerű szám 0 és 9 között
					randomNumberBuilder.append(digit);
				}
				String ibanstring = randomNumberBuilder.toString();
				iban = Long.parseLong(ibanstring);
			}
		}
	}

}
