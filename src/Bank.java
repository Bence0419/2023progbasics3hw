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
	
	public void loadAccountData(String filename) {  //Az arraylist feltoltese a fajlba mar meglevo elemekkel
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 5) {
                    String name = parts[0];
                    String iban = parts[1];
                    String username = parts[2];
                    String password = parts[3];
                    double money = Integer.parseInt(parts[4]);

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

	public  void saveAccountData(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Account account : accounts) {
                // Formázott szövegként kiírjuk az adatokat a fájlba
                String line = String.format("%s %s %s %s %d",
                        account.getName(), account.getIBAN(), account.getUsername(),
                        account.getPassword(), account.getMoney());
                bw.write(line);
                bw.newLine(); // Új sor karakter hozzáadása a következő sorhoz
            }
        } catch (IOException e) {
            e.printStackTrace();
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
        int[] bankNotes = {20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1};
        
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
		admins.add(admin);
	}

}
