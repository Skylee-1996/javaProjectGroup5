package team;

import java.io.IOException;
import java.util.Scanner;

public interface IBlackJack {
    void start(Scanner scan, String id, int userBalance, UserManager u) throws InterruptedException, IOException;
    // 블랙잭 게임 시작 메서드
    /* 1. 게임시작 전 플레이어는 걸고싶은 액수의 돈을 건다
     * 2. 유저와 딜러가 각 2장을 받는다.(참가는 모든 카드를 오픈하되, 딜러는 받은 첫번째 카드만 오픈한다.)
     * 
     * 딜러 규칙
     * 1. 16이하면 무조건 히트, 17이상이면 무조건 스탠드
     * 2. 딜러는 버스트 되지않는한 무조건 A는 11로 카운트
     * 
     * 유저의 선택지
     * 1. Hit	=	카드를 한장 더 뽑는 것
     * 2. Stand 	=	카드를 더는 뽑지않고 결과를 보는것
     * 3. DoubleDown 	=	배당금을 2배로 올리면서 카드 한장뽑기
     * 
     * 게임의 결과 종류
     * 1. Win (21에 근접한 숫자로 승리)
     * 2. Bust	(21초과한 숫자로 패배)
     * 3. Push	(무승부)
     * 4. BlackJack (21점으로 승리)
     * 
     * 배당금
     * Push =  배팅금 1배
     * Win = 배팅금의 2배
     * BlackJack = 배팅금의 2.5배
     */
    
    int printMenu(Scanner scan);
    // 게임 메뉴 출력 및 선택지 입력
    /* 메뉴를 출력하고
     * 그 선택지(정수값)를 반환하는 메서드
     */
    
    void firstTurn() throws InterruptedException;
    // 초기 게임 세팅
    /* 카드 덱을 셔플하는 메서드 실행 후
     * 유저와 딜러에게 카드 2장씩 할당
     * 
     * 그 값을 누적 연산하고 출력
     */
    
    void printUserCard();
    // 유저 카드 출력
    /* 유저의 카드와 현재 값, 배팅칩 갯수를 출력
     */
    
    void printResult(int result) throws InterruptedException;
    // 게임 결과 출력
    /* result에는 게임의 결과값이 들어가는데
     * 이번에 진행한 게임의 최종 결과와 점수를 출력
     * 
     * 유저가 어떤식으로 승패가 결정난건지 기술
     */

    void userBet(Scanner scan);
    // 베팅 금액 설정
    /* 칩 베팅 갯수를 설정하고 옳은 값을 입력하면 탈출
     * 유저는 본인 전체 칩갯수 이상을 베팅할 수 없고 음수의 값을 베팅 할 수 없다.
     */

    int gameResult(UserManager u);
    // 게임 결과 계산 및 반환
    /* printResul메서드의 매개변수로 들어가는 메서드
     * 
     * 유저의 승패 방식에 따라서 베팅한 금액을 얻고 잃는다.
     * 그 승패방식의 번호값을 리턴
     */
}