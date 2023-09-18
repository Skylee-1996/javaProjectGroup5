package team;

import java.util.ArrayList;


public class BlackJackManager{
	ArrayList<Card> DC = new ArrayList<>();		//딜러의 카드팩
	ArrayList<Card> UC = new ArrayList<>();		//유저의 카드팩
	
	Deck card = new Deck();
	
	
	private int DCSum;
	private int UCSum;
	private int userBet;
	private int cnt=2;
	private int Acnt;
	private int count;

	
	//유저의 선택지 기술
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
	
	public void hit() {	
		UC.add(card.drawCard());
		setUCSum(getUCValue(UC.get(cnt)));
		cnt++;
	}
	
	
	public void stand() {
		int i=2;
		while(getDCSum()<=16) {
			DC.add(card.drawCard());
			setDCSum(getDCValue(DC.get(i)));
			i++;
		}
	}
	
	public void doubledown() {
		UC.add(card.drawCard());
		setUCSum(getUCValue(UC.get(cnt)));
		setUserBet(getUserBet()*2);
		cnt++;
	}
	
	
	
	//다양한 설명에 대한 기술
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
		ArrayList<Integer> UCC = new ArrayList<>();
    	for(int i=getCount(); i<UC.size(); i++) {
    		setCount(i);
    		UCC.add(getUCValue(UC.get(i)));
    	}
    	
    	for(int i=getAcnt(); i<UCC.size(); i++) {
    		if(getUCSum()+uCSum>21&&UCC.get(i)==11) {
    			setAcnt(i);
    			this.UCSum += uCSum-10;
    		}else {
    			this.UCSum += uCSum;
    			break;
    		}
    	}
//		    int aceCount = 0; // A 카드의 개수를 세는 변수
//		    
//		    // 현재까지의 UC 합계 계산
//		    for (int i = getCount(); i < UC.size(); i++) {
//		        setCount(i);
//		        int cardValue = getUCValue(UC.get(i));
//		        if (cardValue == 11) { // A 카드인 경우
//		            aceCount++;
//		        }
//		    }
//		    this.UCSum += uCSum;
//
//		    // A 카드가 있고, UCSum이 21을 넘어가면 A를 1로 처리
//		    while (aceCount > 0 && this.UCSum > 21) {
//		        this.UCSum -= 10;
//		        aceCount--;
//		    }
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