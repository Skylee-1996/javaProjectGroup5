package team;

import java.util.ArrayList;

public class BlackJackManager {
    ArrayList<Card> DC = new ArrayList<>(); // 딜러의 카드팩
    ArrayList<Card> UC = new ArrayList<>(); // 유저의 카드팩
    Deck card = new Deck();

    private int DCSum;      // 딜러 카드의 합계
    private int UCSum;      // 유저 카드의 합계
    private int userBet;    // 유저의 배팅 금액
    private int cnt = 2;    // 카드를 뽑는 횟수 카운터
    private int Acnt;       // A 카드 카운터
    private int count;      // 인덱스 카운터
    
    
    
    // 유저의 행동
    // 룰 설명 출력 메서드
    public void printRule() {
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

    // 카드 한 장 더 뽑는 메서드
    public void hit() throws InterruptedException {
    	System.out.println("유저의 카드를 뒤집습니다...");
    	Thread.sleep(2000); // 2초 딜레이
        UC.add(card.drawCard()); // 유저 카드 팩에 카드 추가
        setUCSum(getUCValue(UC.get(cnt))); // 유저 카드 합계 업데이트
        cnt++; // 카운터 증가
    }

    // 카드 뽑기 종료 및 딜러의 카드 뽑는 메서드
    public void stand() throws InterruptedException {
        int i = 2;	//firstTurn 메서드가 끝나고 2번째 턴 이후 인덱스
        System.out.println("딜러의 카드를 뒤집습니다...");
        while (getDCSum() <= 16) {	//딜러는 유저가 Stand시 16이하면 계속 카드뽑기
        	Thread.sleep(2000); // 2초 딜레이
            DC.add(card.drawCard()); // 딜러 카드 팩에 카드 추가
            setDCSum(getDCValue(DC.get(i))); // 딜러 카드 합계 업데이트
            i++; // 카운터 증가
        }
    }

    // 배당금 2배로 올리고 카드 한 장 더 뽑는 메서드
    public void doubledown() throws InterruptedException {
    	System.out.println("카드를 뒤집습니다...");
    	Thread.sleep(2000); // 2초 딜레이
        UC.add(card.drawCard()); // 유저 카드 팩에 카드 추가
        setUCSum(getUCValue(UC.get(cnt))); // 유저 카드 합계 업데이트
        setUserBet(getUserBet() * 2); // 배팅 금액 2배로 업데이트
        cnt++; // 카운터 증가
    }
	
	
    
	// 카드의 값을 숫자로 반환
    //딜러
	public int getDCValue(Card card) {
        String cardValue = card.getValue();
        switch (cardValue) {
            case "A": if(getDCSum()>10) {
            	return 1;            	
            }else if(getDCSum()<11) {
            	return 11;
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
	//유저
	public int getUCValue(Card card) {
        String cardValue = card.getValue();
        switch (cardValue) {
            case "A": if(getUCSum()>10) {
            	return 1;
            }else if(getUCSum()<11) {
            	return 11;
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
	
	
	//getter/setter
	public int getDCSum() {
		return DCSum;
	}

	public void setDCSum(int dCSum) {
		this.DCSum += dCSum;
	}

	public int getUCSum() {
		return UCSum;
	}

	public void setUCSum(int uCSum) {
		for(int i=getCount(); i<UC.size(); i++) {
		    int cardValue = getUCValue(UC.get(i));	// UC의 값을 carValue에 저장
		    setCount(i);	// 중복추가 방지를 위해 i값 유지

		    if(cardValue == 11) {	// cardValue에 11값이 들어오면
		        Acnt++;		// Acnt 증가
		    }
		}
    	this.UCSum += uCSum;	// 유저의 합계 누적연산
    	
    	if(Acnt>0&&this.UCSum>21) {	// A가 11로 연산되었고 UCSum이 21을 넘으면
    		Acnt--;	// 11로 연산된 Acnt를 줄이고
    		this.UCSum -= 10;	// A를 1로 다시 연산
    	}
	}

	public int getUserBet() {
		return userBet;
	}

	public void setUserBet(int userBet) {
		this.userBet = userBet;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getAcnt() {
		return Acnt;
	}

	public void setAcnt(int acnt) {
		Acnt = acnt;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}