package team;

public interface IBlackJackManager {
	public void printRule();
	// BlackJack게임 룰 설명 메서드
	
	public void hit() throws InterruptedException;
	//유저가 카드를 한장 뽑는 메서드
	/* 유저의 카드를 유저의 list에 추가하고
	 * 그 값을 누적 연산한다
	 * 
	 * 인덱스 변수인 cnt를 증가시켜 다음 번지에 다음 카드를 추가 할 수 있도록 한다.
	 */
	
	public void stand() throws InterruptedException;
	//유저가 카드뽑기를 중단하는 메서드
	/* getDCSum의 값이 16이 될때까지 카드를 뽑고 while문 탈출
	 * 딜러의 카드를 list에 추가하고 값 누적연산
	 */
	
	public void doubledown() throws InterruptedException;
	//유저가 카드를 한장 뽑고 베팅칩을 2배로 올리는 메서드
	/* 유저의 카드를 유저의 list에 추가하고
	 * 그 값을 누적 연산한다
	 * 
	 * 인덱스 변수인 cnt를 증가시켜 다음 번지에 다음 카드를 추가 할 수 있도록 한다.
	 * 
	 * 베팅칩을 2배로 올린다.
	 */
	
	public int getValue(Card card);
	//유저의 카드값을 정수형으로 바꿔주는 메서드
	/* A는 UCSum의 값에 따라 1로 받을지 11로 받을지 변경
	 * 
	 * 나머지 J, Q, K는 10으로 연산
	 */
}
