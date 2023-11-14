import java.util.Random;

public class Admin extends User {
    private int adminID;

    public Admin(String name,String user, String pw)
    {
        super(name,user,pw);
    }
    public int getAdminId()
    {
        return adminID;
    }
    public void setAdminID(int val)
    {
        adminID = val;
    }
    public void setAdminID()
    {
        String idstring = "";
		for(int i = 0; i<10; i++)
		{
			Random rand = new Random();
			int val = rand.nextInt();
			String h = Integer.toString(val);
            idstring+=h;
		}
        this.adminID = Integer.parseInt(idstring);
    }
}
