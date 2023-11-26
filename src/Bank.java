import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Bank {
	
	// ----------------------------------------
	//ATTRIBUTUMOK
	// ----------------------------------------
	
	public  ArrayList<Account> accounts = new ArrayList<Account>(); //A fiokok tarolasara hasznalt kontener
	public  ArrayList<Admin> admins = new ArrayList<Admin>();
	
	// ----------------------------------------
	//METODUSOK
	// ----------------------------------------
	
	
	    public void serializeAdmins(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(admins);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Admins deszerializáció
    @SuppressWarnings("unchecked")
    public void deserializeAdmins(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            admins = (ArrayList<Admin>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Accounts szerializáció
    public void serializeAccounts(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
			oos.writeObject(accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Accounts deszerializáció
	@SuppressWarnings("unchecked")
	public void deserializeAccounts(String filename) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			accounts.clear();
			accounts.addAll((ArrayList<Account>) ois.readObject());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	




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

	public  boolean login(String username, String password)
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

	public boolean isInAccounts(String iban)
	{
		for(Account account : accounts)
		{
			if(account.getIBAN().equals(iban))
			{
				return true;
			}
		}
		return false;
	}
	
	public  boolean isAdmin(String username, String password)
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

	public  void transferMoney(Account from, Account to, double money)
	{
		from.setMoney(from.getMoney()-money);
		to.setMoney(to.getMoney()+money);
	}

	public  Account findByIBAN(String IBAN)
	{
		for (Account account : accounts)
		{
			if(account.getIBAN().equals(IBAN))
			{
				return account;
			}
		}
		return null;
	}



	public  void changePassword(String older, String newer)
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

	public boolean isUsedIban(String iban)
	{
		for(Account account : accounts)
		{
			if(account.getIBAN().equals(iban))
			{
				return true;
			}
		}
		return false;
	}

	public String generateIban()
	{
		Random random = new Random();
		while(true)
		{
			String iban = "";
			if(!accounts.isEmpty())
			{
				iban = accounts.get(0).getIBAN();
			}
			if(!isUsedIban(iban))
			{
				return iban;
			}
			else
			{
				StringBuilder randomNumberBuilder = new StringBuilder();
				for (int i = 0; i < 9; i++) {
					int digit = random.nextInt(10); // Véletlenszerű szám 0 és 9 között
					randomNumberBuilder.append(digit);
				}
				String ibanstring = "HU42123" + randomNumberBuilder.toString() + "0000000";
				return ibanstring;
			}
		}
	}

	public static String printBankNotes(double amount) {
        // Hungarian banknote denominations
        int[] bankNotes = {20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5};
        
        StringBuilder result = new StringBuilder(amount + " Ft kiadása bankjegyekbe:\n");

        for (int bankNote : bankNotes) {
            int count = (int) (amount / bankNote);
            if (count > 0) {
                result.append(count).append(" db ").append(bankNote).append(" Ft-os\n");
                amount %= bankNote;
            }
        }

        return result.toString();
    }

	public void addAdmin(Admin admin) {
		admin.setAdminID();
		admins.add(admin);
	}

    public void deleteAccount(String accountNumber) {
		accounts.removeIf(account -> account.getIBAN().equals(accountNumber));
	}

	public boolean isUsedUsername(String username) {
		for(Account account: accounts)
		{
			if(account.getUsername().equals(username))
			{
				return true;
			}
		}
		return false;
	}

	public Admin findAdmin(String username, String password) {
		for(Admin admin : admins)
		{
			if(admin.getUsername().equals(username)&&admin.getPassword().equals(password))
			{
				return admin;
			}
		}
		return null;
	}

    public boolean isUsedAdmin(String username) {
        for(Admin admin : admins)
		{
			if(admin.getUsername().equals(username))
			{
				return true;
			}
		}
		return false;
    }
	
}
