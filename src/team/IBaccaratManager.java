package team;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public interface IBaccaratManager {
	Deck initializeGame();
	//게임 초기화 메서드 덱을 생성하고 초기화 해서 리턴함
	int hBetting(Scanner scan,int userBalance);
	//얼마나 배팅하는지 정하는 메서드
	String wBetting(Scanner scan, int userBalance, int betAmount);
	//플레이어 뱅커 타이 어디곳에 배팅할지 정하는 메서드
	void drawCardWithDelay(List<Card> cards, Deck deck) throws InterruptedException;
	//카드리스트와 덱을 매개변수로 주면 카드한장을 뽑아서 뒷면을 보여준후 2초후 앞면출력 그리고 리스트에 추가
	void printBack() throws InterruptedException;
	//카드 뒷면 출력용 메서드(그냥 출력용임 카드는 그냥 아무것도 담고있지않음)
	void showHistory(List<String> gameHistory);
	//게임 기록을 저장후 출력
	boolean shouldBankerDrawThirdCard(int bankerScore, int playerThirdCardValue);
	//3번째 카드뽑아야 하는지 체크하는 메서드
//	뱅커의 점수가 0, 1, 또는 2일 경우: 뱅커는 항상 세 번째 카드를 뽑습니다.
//	뱅커의 점수가 3일 경우: 플레이어의 세 번째 카드의 값이 8이 아니면 뱅커는 세 번째 카드를 뽑습니다.
//	뱅커의 점수가 4일 경우: 플레이어의 세 번째 카드의 값이 2에서 7 사이이면 뱅커는 세 번째 카드를 뽑습니다.
//	뱅커의 점수가 5일 경우: 플레이어의 세 번째 카드의 값이 4에서 7 사이이면 뱅커는 세 번째 카드를 뽑습니다.
//	뱅커의 점수가 6일 경우: 플레이어의 세 번째 카드의 값이 6 또는 7이면 뱅커는 세 번째 카드를 뽑습니다.
//	뱅커의 점수가 7 이상일 경우: 뱅커는 세 번째 카드를 뽑지 않습니다.
	int getScore(Card card1, Card card2);
	//두카드의 value 를 더한후 10의 자리를 때고 계산합값을 돌려줌
	int getValue(Card card);
	//카드를 객체를 받아서 그카드의 value 값을 리턴해줍니다 a는 1로 j q k = 10으로 리턴받습니다 
	String getResult(int playerScore, int bankerScore);
	//플레이어와 뱅커의 스코어를 비교해주고 누가 이겼는지 혹은 비겼을때 타이로 반환됨
	String historyResult(int playerScore, int bankerScore);
	//게임 결과값을       ○ 플레이어 승리        ■ 뱅커 승리        ▲ 타이   로 반환해줌
	void delay();
	//1.5초의 딜레이를줌
	boolean endGame(Scanner scan, String id, int userBalance, UserManager u) throws InterruptedException, IOException;
	// 게임 한번더 / 게임선택 / 종료 창 선택할수 있는 메서드

}
