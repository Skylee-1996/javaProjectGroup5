package team;

import java.io.IOException;
import java.util.Scanner;

public interface IndianPokerInterface {
	void gameStart(Scanner scan, String id, int userBalance, UserManager uM) throws IOException, InterruptedException;
	/* 게임을 시작하는 메서드
	 * 1번 선택시 10번의 게임 [round()] 을 실행
	 * 게임이 끝나고 사용자의 금액을 확인
	 * 21만원보다 적으면 게임 종료
	 * 
	 * 2번 선택시 게임 설명 출력
	 * 아무키 입력시 다시 메뉴로 이동
	 * 
	 */
	
	void checkWin();
	/* 사용자의 카드와 컴퓨터의 카드를 비교
	 * 총 카드 20장이 존재하며 인덱스 0 ~ 19
	 * int 타입 변수에 각각 넣고 1을 증가시켜 1 ~ 20 표시
	 * 
	 * 사용자의 값이 1 ~ 10일때 컴퓨터의 값이 1 ~ 10인지, 11 ~ 20인지 확인 후
	 * 컴퓨터의 값이 1 ~ 10이면 숫자를 비교하여 결과 문구 출력, 컴퓨터의 값이 11 ~ 20이면 
	 * 사용자의 값에 10을 더하여 숫자를 비교한 후 결과 문구 출력
	 * 
	 * 사용자의 값이 11 ~20일때 컴퓨터의 값이 1 ~ 10인지, 11 ~ 20인지 확인 후
	 * 컴퓨터의 값이 1 ~10이면 컴퓨터의 값에 10을 더하여 숫자를 비교한 후 결과 문구 출력, 
	 * 컴퓨터의 값이 11 ~ 20이면 숫자를 비교하여 결과 문구 출력
	 */
	
	void round() throws InterruptedException;
	/* 시작시 choice1() 메서드를 통한 값을 입력 받고, comCal() 메서드 결과에 따라 진행
	 * FOLD를 받게되면 사용자의 승리
	 * 
	 * 2, 3번째 선택시에는 choice2() 메서드를 통한 값을 입력 받고 comCal() 메서드 결과에 따라 진행
	 * 
	 */
	
	int choice1(Scanner scan);
	/* 사용자가 처음에 FOLD 할지, BETTING 할지 선택
	 * 1.FOLD 선택시, 1 리턴
	 * 2.BETTING 선택시, 2 리턴
	 * 
	 * 숫자가 아닌 문자 입력시 다시 입력하도록 반복문을 통해 입력
	 * 
	 */
	
	int choice2(Scanner scan);
	/* 2, 3번째 선택에서 사용자가 FOLD 할지, BETTING 할지, CHECK 할지 선택
	 * 1.FOLD 선택시, 1 리턴
	 * 2.BETTING 선택시, 2 리턴
	 * 3.CHECK 선택시, 3 리턴
	 * 
	 * 숫자가 아닌 문자 입력시 다시 입력하도록 반복문을 통해 입력
	 * 
	 */
	
	int comCal() throws InterruptedException;
	/* 컴퓨터가 사용자의 패를 기준으로 FOLD, BETTING, CHECK 할지 선택
	 * Math.random() 메서드로 1 ~ 100 숫자를 뽑고, 상대방의 패를 기준으로 
	 * 확률적으로 FOLD, BETTING, CHECK 선택
	 * 
	 */
	
	
}
