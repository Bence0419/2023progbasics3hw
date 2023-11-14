public class User {
    private String name; //anyakonyvezett nev
	private String username; //bejelentkezesi felhasznalonev	
	private String password; //bejelentkezesi jelszo

    public User(String name, String username, String password)
    {
        this.name = name;
        this.username = username;
        this.password = password;
    }

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

    public void setName(String s)
	{
		name = s;
	}
	
	public void setUsername(String s)
	{
		username = s;
	}
	
	public void setPassword(String s)
	{
		password = s;
	}

}
