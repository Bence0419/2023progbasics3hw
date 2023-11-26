import java.io.Serializable;
import java.util.Random;

public class Admin extends User{
    private String adminID;

    public Admin(String name,String user, String pw)
    {
        super(name,user,pw);
    }
    public String getAdminId()
    {
        return adminID;
    }
    public void setAdminID(String val)
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
        this.adminID = idstring;
    }
}
