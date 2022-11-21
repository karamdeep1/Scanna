package com.example.demo;


public class userClass
{
    private boolean admin;
    private String username;
    private String password;

    public userClass()
    {
        admin = false;
        username = "";
        password = "";
    }

    public userClass(boolean admin, String username, String password)
    {
        this.admin = admin;
        this.username = username;
        this.password = password;
    }

    public boolean getAdmin()
    {
        return admin;
    }
    public void setAdmin(boolean adn)
    {
        admin = adn;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String usn)
    {
        username = usn;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String psd)
    {
        password = psd;
    }
}
