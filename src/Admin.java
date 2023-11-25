import java.io.Serializable;
import java.util.Random;

public class Admin extends User{
    private long adminID;

    public Admin(String name,String user, String pw)
    {
        super(name,user,pw);
    }
    public long getAdminId()
    {
        return adminID;
    }
    public void setAdminID(String val)
    {
        adminID = Long.parseLong(val);
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
        this.adminID = Long.parseLong(idstring);
    }
}
