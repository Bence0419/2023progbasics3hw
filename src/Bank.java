import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Bank {
	
	// ----------------------------------------
	//ATTRIBUTUMOK
	// ----------------------------------------
	
	public static ArrayList<Account> accounts = new ArrayList<Account>(); //A fiokok tarolasara hasznalt kontener
	public static ArrayList<Admin> admins = new ArrayList<Admin>();
	
	// ----------------------------------------
	//METODUSOK
	// ----------------------------------------
	
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
		for(int i = 0; i<accounts.size(); i++)
		{
			if(accounts.get(i).getUsername().equals(username)&&accounts.get(i).getPassword().equals(password))
			{
				System.out.println("Sikeres bejelentkezes");
				return true;
			}
		}
		System.out.println("Helytelen felhasznalonev vagy jelszo");
		return false;
	}

	public static boolean isAdmin(String username, String password)
	{
		for(int i = 0; i<accounts.size();i++)
		{
			if(admins.get(i).getUsername().equals(username)&&admins.get(i).getPassword().equals(password))
			{
				return true;
			}
		}
		return false;
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

}
