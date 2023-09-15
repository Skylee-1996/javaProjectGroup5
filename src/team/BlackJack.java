package team;

import java.util.ArrayList;
import java.util.Scanner;


public class BlackJack {
	/* 게임 방법
	 * 1. 게임시작 전 플레이어는 걸고싶은 액수의 돈을 건다
	 * 2. 플레이어 먼저 2장 받고 딜러가 2장을 받는다(참가는 모든 카드를 오픈하되, 딜러는 받은 첫번째 카드만 오픈한다.)
	 * 
	 * 
	 * 
	 * 딜러 규칙
	 * 1. 16이하면 무조건 히트, 17이상이면 무조건 스탠드
	 * 2. 딜러는 버스트 되지않는한 무조건 A는 11로 카운트
	 * 
	 * 
	 * 
	 * 유저의 선택지
	 * 1. Hit	=	카드를 한장 더 뽑는 것
	 * 2. Stand 	=	카드를 더는 뽑지않고 결과를 보는것
	 * 3. DoubleDown 	=	배당금을 2배로 올리면서 카드 한장뽑기
	 * 
	 * 
	 * 
	 * 게임의 결과 종류
	 * 1. Win (21에 근접한 숫자로 승리)
	 * 2. Bust	(21초과한 숫자로 패배)
	 * 3. Push	(무승부)
	 * 4. BlackJack (21점으로 승리)
	 * 
	 * 
	 * 
	 * 배당금
	 * Push =  배팅금 1배
	 * Win = 배팅금의 2배
	 * BlackJack = 배팅금의 2.5배
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		
		a:while(true) {
			System.out.print("게임을 시작하시겠습니까?(y/n) ");
			char choice = scan.next().charAt(0);
			BlackJackManager BJM = new BlackJackManager();
			if(choice=='y') {
				int move = 0;
				BJM.start();
				
				while(true) {
					BJM.printChoice();	
					System.out.print("행동을 선택해주세요> ");
					move = scan.nextInt();
					
					switch(move){
					case 1:	BJM.hit();
						if(BJM.getUCsum()<21) {
							continue;							
						}else
							break;
					case 2: BJM.stand();
						break;
					case 3: BJM.doubleDown();
						break;
					case 0:	BJM.printRule();
						continue;
						default:
							System.out.println("잘못된 값입니다.");
							continue;
					}
					
					switch(BJM.result()) {
					case 0: BJM.printResult();
						System.out.println("진행자 BUST, 유저 WIN");
						continue a;
					case 1:	BJM.printResult();
						System.out.println("유저 WIN");
						continue a;
					case 2: BJM.printResult();
						System.out.println("Push");
						continue a;
					case 3:	BJM.printResult();
						System.out.println("유저 BUST, 진행자 WIN");
						continue a;
					case 4: BJM.printResult();
						System.out.println("진행자 WIN");
						continue a;
					case 5: BJM.printResult();
						System.out.println("유저 BlackJack");
						continue a;
					case 6: BJM.printResult();
						System.out.println("진행자 BlackJack");
						continue a;
					case 999: System.out.println("오류발생");
						continue a;
					}
				}
				
			}else if(choice=='n') {
				System.out.println("게임을 종료합니다.");
				break a;
			}else {
				System.out.println("잘못된 값입니다.");
				continue a;
			}			
		}
		scan.close();
	}
}


class BlackJackManager{
	Scanner scan = new Scanner(System.in);
	ArrayList<Card> DC = new ArrayList<>();		//딜러의 카드팩
	ArrayList<Card> UC = new ArrayList<>();		//유저의 카드팩
	Deck card = new Deck();
	private int UCsum;
	private int DCsum;
	private int userCnt;
	
	public BlackJackManager() {}
	
	public void printRule() {				//룰설명
		System.out.println("	 * 1. 게임시작 전 플레이어는 걸고싶은 액수의 돈을 건다\r\n"
				+ "	 * 2. 플레이어 먼저 2장 받고 딜러가 2장을 받는다(참가는 모든 카드를 오픈하되, 딜러는 받은 첫번째 카드만 오픈한다.)\r\n"
				+ "	 * \r\n"
				+ "	 * \r\n"
				+ "	 * \r\n"
				+ "	 * 딜러 규칙\r\n"
				+ "	 * 1. 16이하면 무조건 히트, 17이상이면 무조건 스탠드\r\n"
				+ "	 * 2. 딜러는 버스트 되지않는한 무조건 A는 11로 카운트\r\n"
				+ "	 * \r\n"
				+ "	 * \r\n"
				+ "	 * \r\n"
				+ "	 * 유저의 선택지\r\n"
				+ "	 * 1. Hit	=	카드를 한장 더 뽑는 것\r\n"
				+ "	 * 2. Stand 	=	카드를 더는 뽑지않고 결과를 보는것\r\n"
				+ "	 * 3. DoubleDown 	=	배당금을 2배로 올리면서 카드 한장뽑기\r\n"
				+ "	 * \r\n"
				+ "	 * \r\n"
				+ "	 * \r\n"
				+ "	 * 게임의 결과 종류\r\n"
				+ "	 * 1. Win (21에 근접한 숫자로 승리)\r\n"
				+ "	 * 2. Bust	(21초과한 숫자로 패배)\r\n"
				+ "	 * 3. Push	(무승부)\r\n"
				+ "	 * 4. BlackJack (21점으로 승리)\r\n"
				+ "	 * \r\n"
				+ "	 * \r\n"
				+ "	 * \r\n"
				+ "	 * 배당금\r\n"
				+ "	 * Push =  배팅금 1배\r\n"
				+ "	 * Win = 배팅금의 2배\r\n"
				+ "	 * BlackJack = 배팅금의 2.5배");
	}
	
	public void start() {	//게임 최초진행 메서드
		System.out.println("진행자 : 게임을 시작합니다.");
		
		System.out.println("진행자 : 카드 패를 섞고있습니다.");
		card.shuffle();
		
		System.out.println("진행자 : 카드를 셋팅중입니다.");
		
		for(int i=0; i<=1; i++) {		//카드 2장씩 배분
			UC.add(card.drawCard());	
			DC.add(card.drawCard());	
		}
		System.out.println("진행자 : 카드 셋팅이 완료되었습니다.");
		
		
		//드로우한 카드 보여주는곳
		System.out.println("진행자의 카드");
		
		System.out.println(DC.get(0).toString());	//진행자는 첫 드로우한 패부터 보여줌
		setDCsum(getDValue(DC.get(0)));				//진행자의 카드패의 값을 누적연산
		setDCsum(getDValue(DC.get(1)));
		
		System.out.println("------------------------");
		System.out.println("유저의 카드");
		
		for(int i=0; i<UC.size(); i++) {
			System.out.println(UC.get(i).toString());	//유저의 패 보기
		}
		setUCsum(getUValue(UC.get(userCnt)));				//유저의 패 누적연산
		userCnt++;
		setUCsum(getUValue(UC.get(userCnt)));
		System.out.println("유저의 점수: "+UCsum);
	}
	
	public void printChoice() {
		System.out.println("1.Hit|2.Stand|3.doubleDown|0.룰설명");
	}
	
	public void hit() {
		System.out.println("Hit!!!");
		System.out.println("------------------------");
		System.out.println("당신의 카드");
		
		UC.add(card.drawCard());						//유저가 hit동작시, 카드 드로우 후
		userCnt++;
		setUCsum(getUValue(UC.get(userCnt)));			
		for(int i=0; i<UC.size(); i++) {
			System.out.println(UC.get(i).toString());	//지금까지 뽑은 카드 출력
		}
		System.out.println("유저의 점수: "+UCsum);
		System.out.println("------------------------");
	}
	
	public void stand() {
		System.out.println("Stand!!!");
		
		int cnt=2;
		while(DCsum<=16) {								//유저가 stand선택시, 진행자만 카드를 뽑은다.
			DC.add(card.drawCard());					//16이하면 진행자는 무조건 카드를 뽑아야한다.
			setDCsum(getDValue(DC.get(cnt)));			//카드를 계속 뽑으면 그 값 누적연산
			cnt++;
		}
	}
	
	public void doubleDown() {
		System.out.println("DoubleDown!!!");
		System.out.println("------------------------");
		System.out.println("당신의 카드");
		
		UC.add(card.drawCard());						//doubledown 동작시, 배팅금액 2배 레이즈하면서 hit동작과 동일
		for(int i=0; i<UC.size(); i++) {				//카드를 드로우하고 유저의 카드 전체 출력
			System.out.println(UC.get(i).toString());
		}
		
		System.out.println("------------------------");
	}
	
	public void printResult() {
		System.out.println("------------------------");
		System.out.println("진행자의 카드");
		for(int i=0; i<DC.size(); i++) {
			System.out.println(DC.get(i).toString());
		}
		System.out.println("진행자의 점수: "+DCsum);
		System.out.println("------------------------");
		System.out.println("유저의 카드");
		for(int i=0; i<UC.size(); i++) {
			System.out.println(UC.get(i).toString());
		}
		System.out.println("유저의 점수: "+UCsum);
		System.out.println("------------------------");
	}
	
	public int result() {
		if(DCsum>21) {									//진행자가 21을 넘어가면 진행자 버스트로 패배
			return 0;								
		}else if(((21-UCsum)<(21-DCsum))&&UCsum<21) {					//남은 숫자가 더 큰쪽은 근접하지 못했다는 의미로 진행자 패배
			return 1;
		}else if(DCsum==UCsum) {
			return 2;
		}else if(UCsum==21) {						//숫자가 같으면 push
			return 5;
		}else if(UCsum>21) {							//유저가 21을 넘어가면 유저 버스트로 패배
			return 3;
		}else if(((21-UCsum)>(21-DCsum))&&DCsum<21) {					//유저 패배
			return 4;
		}else if(DCsum==21) {
			return 6;
		}
		else
			return 999;
	}
	
	

	public int getUValue(Card card) {
        String cardValue = card.getValue();
        switch (cardValue) {
            case "A": if(UCsum<=10) {				//유저의 A는 10이하면 11로 연산할지 1로 연산할지 바꿀수있다.
            	return 11;
            }else if(UCsum>=11)					//유저의 A는 11이상이면 1로 연산
            	return 1;								
            case "2": for(int i=0; i<UC.size(); i++) {
            		if(UC.get(i).getShape().equals("A")&&((UCsum+2)>21)) {
            			return -9;            		
            		}            		
            	}return 2;
            case "3": for(int i=0; i<UC.size(); i++) {
            		if(UC.get(i).getShape().equals("A")&&((UCsum+3)>21)) {
            			return -8;            		
            		}            		
            	}return 3;
            case "4": for(int i=0; i<UC.size(); i++) {
            		if(UC.get(i).getShape().equals("A")&&((UCsum+4)>21)) {
            			return -7;            		
            		}            		
            	}return 4;
            case "5": for(int i=0; i<UC.size(); i++) {
            		if(UC.get(i).getShape().equals("A")&&((UCsum+5)>21)) {
            			return -6;            		
            		}            		
            	}return 5;
            case "6": for(int i=0; i<UC.size(); i++) {
            		if(UC.get(i).getShape().equals("A")&&((UCsum+6)>21)) {
            			return -5;            		
            		}            		
            	}return 6;
            case "7": for(int i=0; i<UC.size(); i++) {
            		if(UC.get(i).getShape().equals("A")&&((UCsum+7)>21)) {
            			return -4;            		
            		}            		
            	}return 7;
            case "8": for(int i=0; i<UC.size(); i++) {
            		if(UC.get(i).getShape().equals("A")&&((UCsum+8)>21)) {
            			return -3;            		
            		}            		
            	}return 8;
            case "9": for(int i=0; i<UC.size(); i++) {
            		if(UC.get(i).getShape().equals("A")&&((UCsum+9)>21)) {
            			return -2;            		
            		}            		
            	}return 9;
            default: 
            	for(int i=0; i<UC.size(); i++) {
            		if(UC.get(i).getShape().equals("A")&&((UCsum+10)>21)) {
            			return -1;            		
            		}            		
            	}return 10;
        }
    }
	
	public int getDValue(Card card) {
        String cardValue = card.getValue();
        switch (cardValue) {
            case "A": if(DCsum<=10) {				//진행자의 A는 10이하면 11로 연산
            	return 11;
            }else if(DCsum>=11) {					//진행자는 bust되지 않는 한, 11로 연산한다. 
            	return 1;
            }
            case "2": return 2;
            case "3": return 3;
            case "4": return 4;
            case "5": return 5;
            case "6": return 6;
            case "7": return 7;
            case "8": return 8;
            case "9": return 9;
            default: return 10;
        }
    }
	
	
	public int getUCsum() {
		return UCsum;
	}

	public void setUCsum(int uCsum) {
		UCsum += uCsum;
	}

	public int getDCsum() {
		return DCsum;
	}

	public void setDCsum(int dCsum) {
		DCsum += dCsum;
	}
	
	public int getuserSum() {
		return userCnt;
	}

	public void setuserSum(int UserCnt) {
		userCnt = UserCnt;
	}
}