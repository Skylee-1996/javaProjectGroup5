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
				BJM.start();
				while(BJM.getUCsum()<21) {
					BJM.printChoice();	
					System.out.print("행동을 선택해주세요> ");
					int move = scan.nextInt();
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
	
	public void printRule() {
		
	}
	
	public void start() {	//게임 최초진행 메서드
		System.out.println("진행자 : 게임을 시작합니다.");
		
		System.out.println("진행자 : 카드 패를 섞고있습니다.");
		card.shuffle();
		
		System.out.println("진행자 : 카드를 셋팅중입니다.");
		UC.add(card.drawCard());
		DC.add(card.drawCard());
		UC.add(card.drawCard());
		DC.add(card.drawCard());
		System.out.println("진행자 : 카드 셋팅이 완료되었습니다.");
		
		
		//드로우한 카드 보여주는곳
		System.out.println("진행자의 카드");
		System.out.println(DC.get(0).toString());
		System.out.println("------------------------");
		
		System.out.println("당신의 카드");
		System.out.println(UC.get(0).toString());
		System.out.println(UC.get(1).toString());
	}
	
	public void printChoice() {
		System.out.println("1.Hit|2.Stand|3.doubleDown|0.룰설명");
	}
	
	public void hit() {
		System.out.println("Hit!!!");
		System.out.println("------------------------");
		System.out.println("당신의 카드");
		UC.add(card.drawCard());
		for(int i=0; i<UC.size(); i++) {
			System.out.println(UC.get(i).toString());
		}
		System.out.println("------------------------");
	}
	
	public void stand() {
		System.out.println("Stand!!!");
		while(DCsum<=16) {
			DC.add(card.drawCard());
		}
		if(DCsum>=17) {
			for(int i=0; i<DC.size(); i++) {
				System.out.println(DC.get(i).toString());
			}
		}
	}
	
	
	public void doubleDown() {
		System.out.println("DoubleDown!!!");
		System.out.println("------------------------");
		System.out.println("당신의 카드");
		UC.add(card.drawCard());
		for(int i=0; i<UC.size(); i++) {
			System.out.println(UC.get(i).toString());
		}
		System.out.println("------------------------");
	}

	public int getValue(Card card) {
        String cardValue = card.getValue();
        switch (cardValue) {
            case "A": if(UCsum<=10) {
            	return 11;
            }else if(UCsum>=11) {
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
		UCsum = uCsum;
	}

	public int getDCsum() {
		return DCsum;
	}

	public void setDCsum(int dCsum) {
		DCsum = dCsum;
	}
}