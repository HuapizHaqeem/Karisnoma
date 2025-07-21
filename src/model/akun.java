package model;

public class akun {
    private String username;
    private String password;
    private String peran;
    private String email;

    public akun() {}

    public akun(String username, String password,String peran,String email) {
        this.username =  username;
        this.password =  password;
        this.peran = peran;
        this.email = email;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getPeran() { return peran; }
    public String getEmail() { return email; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setPeran(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
}
