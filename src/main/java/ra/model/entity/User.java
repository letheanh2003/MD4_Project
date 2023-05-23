package ra.model.entity;

public class User {
    private int id;
    private String username;
    private String email;
    private String phone_number;
    private String address;
    private String password;
    private String rePassword;
    private int role;
    private int status;

    public User() {
    }

    public User(String username, String email, String phone_number, String address, String password, String rePassword, int role,int status) {
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
        this.password = password;
        this.rePassword = rePassword;
        this.role = role;
        this.status = status;
    }
    public User(String username, String email, String password,String rePassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.rePassword = rePassword;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}







