package team;



public class User {
   
   private String id; //아이디
   private String password; //비번
   private String name; //회원명
   private int balance; //자본금
   private int rank; //순위
   
   public User() {}
    
    public User(String id, String password, String name) {
      this.id = id;
      this.password = password;
      this.name = name;
      this.balance = 10000000;      
    }
    public User(String id, String password, String name, int balance) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.balance = balance;      
      }

      public String getId() {
         return id;
      }

      public void setId(String id) {
         this.id = id;
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

      public int setBalance(int balance) {
         return this.balance += balance;
      }

      public int getRank() {
         return rank;
      }

      public void setRank(int rank) {
         this.rank = rank;
      }
      
       
}