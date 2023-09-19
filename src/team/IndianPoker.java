package team;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IndianPoker {
	private String id;
	private int money;
	ArrayList<Card> handCard = new ArrayList<>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money -= money;
	}
	public ArrayList<Card> gethandCard() {
		return handCard;
	}
	public void sethandCard(ArrayList<Card> handCard) {
		this.handCard = handCard;
	}
	public void firstMoney(int money) {
		this.money = money;
	}
	
	
	public IndianPoker() {
		this.id = "computer";
		this.money = 100;
	}
	
	public IndianPoker(String id, int money) {
		this.id = id;
		this.money = money;
	}
	
	
	
	
//	public String[] readInfo() throws IOException {
//		Scanner scan = new Scanner(System.in);
//		
//		BufferedReader br = new BufferedReader(new FileReader("userInfo.txt"));
//		
//		System.out.printf("Input ID: ");
//		String id = scan.next();
//		
//		while(true) {
//			String insert = br.readLine();
//			if(insert == null) {
//				break;
//			}
//			
//			String copyInfo[] = insert.split(" ");
//			if(id.equals(copyInfo[0])){
//				br.close();
//				return copyInfo;
//			}
//			
//		}
//		br.close();
//		return null;
//		
//		
//	}
	
	
	
	public int paySetting() {
		this.money -= 1;
		return 1;
	}
	
	public void plusMoney(int money) {
		this.money += money; 
	}
	
	public int pay(Scanner scan) {
		System.out.printf("얼마를 베팅하시겠습니까?");
		int payPrice = scan.nextInt();
		setMoney(payPrice);
		return payPrice;
	}
	
	public int pay(int money) {
		setMoney(money);
		return money;
	}
	
}
