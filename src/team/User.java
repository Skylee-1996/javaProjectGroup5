package team;

public class User {
    private String username;   // 사용자 아이디
    private String password;   // 사용자 비밀번호 
    private String name;       // 사용자 이름
    private int balance;       // 사용자 게임 잔액

    // 생성자
    public User(String username, String password, String name, int balance) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.balance = balance;
    }

    //getter/setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}