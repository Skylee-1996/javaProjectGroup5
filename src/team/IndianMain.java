package team;

public class IndianMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IndianPokerManager iPM = new IndianPokerManager();
		
//		iPM.gameStart();
		for(int i = 0; i < 10; i++) {
			if(iPM.user.getMoney() >= 210000 && iPM.com.getMoney() >= 210000) {
				System.out.println((i + 1) + "번째 게임");
				iPM.round();				
			}else {
				break;
			}
		}
	}

}
