package team;

import java.util.ArrayList;
import java.util.Scanner;


public class BlackJack {
	/* 게임 방법
	 * 1. 게임시작 전 플레이어는 걸고싶은 액수의 돈을 건다
	 * 2. 플레이어 먼저 2장의 카드를 받고 딜러는 2장을 받는다(참가자의 카드는 전부 오픈, 딜러의 카드는 첫번째 한장만 오픈)
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
	 * 2. Stand 	=	카드를 뽑지않는 것
	 * 3. Split	=	처음 받은 카드가 같은 숫자일 경우 덱을 2개로 나눠서 게임을 동시에 2번 진행
	 * 같은 숫자가 또 나올시엔, 스플릿 가능 (나눈 덱에도 똑같은 배팅금액을 정해야함)
	 * 나누고 나면 카드 한장씩 더 베팅 받아서 각 2장을 만들어야함
	 * 스플릿해서 블랙잭이 나올경우 일반 배당으로 받는다
	 * 4. DoubleDown 	=	배당금을 2배로 올리면서 카드 한장뽑기
	 * 
	 * 
	 * 
	 * 게임의 결과 종류
	 * 1. Win (일반 승리)
	 * 2. Bust	(패배)
	 * 3. Push	(무승부)
	 * 4. BlackJack (21점으로 승리)
	 * 
	 * 
	 * 
	 * 배당금
	 * 무승부 배팅금액만 돌려받음 1배
	 * 일반배당 2배
	 * 블랙잭 2.5배
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		BlackJackManager BJM = new BlackJackManager();
		
		a:for(;;) {
			System.out.print("게임을 시작하시겠습니까?(y/n) ");
			char choice = scan.next().charAt(0);
			
			if(choice=='y') {
				int move = 0;
				BJM.start();
				
				while(move==2) {
					BJM.printChoice();	
					System.out.print("행동을 선택해주세요> ");
					move = scan.nextInt();
					switch(move){
					case 1:	BJM.hit();
						break;
					case 2: BJM.stand();
						break;
					case 3: BJM.doubleDown();
						break;
					case 0:	BJM.printRule();
						break;
						default:
							System.out.println("잘못된 값입니다.");
							continue;
					}
				}
				
				switch(BJM.result()) {
				case 0: System.out.println("진행자 BUST, 유저 WIN");
					break a;
				case 1:	System.out.println("유저 WIN");
					break a;
				case 2: System.out.println("Push");
					break a;
				case 3:	System.out.println("유저 BUST, 진행자 WIN");
					break a;
				case 4: System.out.println("진행자 WIN");
					break a;
				case 999: System.out.println("오류발생");
					break a;
				}
			}else if(choice=='n') {
				System.out.println("게임을 종료합니다.");
				break a;
			}else {
				System.out.println("잘못된 값입니다.");
				continue a;
			}			
		}
	}
}


class BlackJackManager{
	Scanner scan = new Scanner(System.in);
	ArrayList<Card> DC = new ArrayList<>();		//딜러의 카드팩
	ArrayList<Card> UC = new ArrayList<>();		//유저의 카드팩
	Deck card = new Deck();
	private int UCsum;
	private int DCsum;
	
	public BlackJackManager() {}
	
	public void printRule() {				//룰설명
		
	}
	
	public void start() {	//게임 최초진행 메서드
		System.out.println("진행자 : 게임을 시작합니다.");
		
		System.out.println("진행자 : 카드 패를 섞고있습니다.");
		card.shuffle();
		
		System.out.println("진행자 : 카드를 셋팅중입니다.");
		
		UC.add(card.drawCard());	//유저 한장
		DC.add(card.drawCard());	//진행자 한장
		UC.add(card.drawCard());	//유저 한장
		DC.add(card.drawCard());	//진행자 한장
		
		System.out.println("진행자 : 카드 셋팅이 완료되었습니다.");
		
		
		//드로우한 카드 보여주는곳
		System.out.println("진행자의 카드");
		
		System.out.println(DC.get(0).toString());	//진행자는 첫 드로우한 패부터 보여줌
		setDCsum(getDValue(DC.get(0)));				//진행자의 카드패의 값을 누적연산
		setDCsum(getDValue(DC.get(1)));
		
		System.out.println("------------------------");
		System.out.println("당신의 카드");
		
		System.out.println(UC.get(0).toString());	//유저의 패 보기
		System.out.println(UC.get(1).toString());
		setUCsum(getUValue(UC.get(0)));				//유저의 패 누적연산
		setUCsum(getUValue(UC.get(1)));
	}
	
	public void printChoice() {
		System.out.println("1.Hit|2.Stand|3.doubleDown|0.룰설명");
	}
	
	public void hit() {
		System.out.println("Hit!!!");
		System.out.println("------------------------");
		System.out.println("당신의 카드");
		
		UC.add(card.drawCard());						//유저가 hit동작시, 카드 드로우 후
		for(int i=0; i<UC.size(); i++) {
			System.out.println(UC.get(i).toString());	//지금까지 뽑은 카드 출력
		}
		
		System.out.println("------------------------");
	}
	
	public void stand() {
		System.out.println("Stand!!!");
		System.out.println("------------------------");
		System.out.println("진행자의 카드");
		
		int cnt=2;
		while(DCsum<=16) {								//유저가 stand선택시, 진행자만 카드를 뽑은다.
			DC.add(card.drawCard());					//16이하면 진행자는 무조건 카드를 뽑아야한다.
			setDCsum(getDValue(DC.get(cnt)));			//카드를 계속 뽑으면 그 값 누적연산
			cnt++;
		}
		if(DCsum>=17) {									//17이상이면 진행자 또한 stand동작 진행
			for(int i=0; i<DC.size(); i++) {
				System.out.println(DC.get(i).toString());	//진행자가 지금까지 뽑은 카드 출력
			}
		}
		
		System.out.println("------------------------");
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
	
	
	
	public int result() {
		if(DCsum>21) {									//진행자가 21을 넘어가면 진행자 버스트로 패배
			return 0;								
		}else if(21-UCsum<21-DCsum) {					//남은 숫자가 더 큰쪽은 근접하지 못했다는 의미로 진행자 패배
			return 1;
		}else if(DCsum==UCsum) {						//숫자가 같으면 push
			return 2;
		}else if(UCsum>21) {							//유저가 21을 넘어가면 유저 버스트로 패배
			return 3;
		}else if(21-UCsum>21-DCsum) {					//유저 패배
			return 4;
		}else
			return 999;
	}
	
	

	public int getUValue(Card card) {
        String cardValue = card.getValue();
        switch (cardValue) {
            case "A": if(UCsum<=10) {				//유저의 A는 10이하면 11로 연산
            	return 11;
            }else if(UCsum>=11) {					//유저의 A는 11이상이면 1로 연산
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
}