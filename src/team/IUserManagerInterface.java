package team;

import java.io.IOException;
import java.util.Scanner;

public interface IUserManagerInterface {
	void userAdd(Scanner scan) throws IOException;
	//(회원가입)유저등록시 아이디가 중복되는지, 빈칸이 있는지 확인 후 추가되도록 설정
	boolean isUserIdExists(String id);
	//(ID중복확인)유저등록시 아이디가 중복되는지 확인하는 메서드
	void deleteUser(Scanner scan) throws IOException;
	//(회원삭제)검색어가 회원ID가 일치하는지 확인 후 유저가 삭제되도록 설정
	void editUser(Scanner scan) throws IOException;
	//(회원수정)검색어가 회원ID가 일치하면 ID,password,회원명을 각각 수정할 수 있도록 설정
	void searchUser(Scanner scan);
	//(회원검색)검색하고 싶은 회원ID가 일치할 경우에만 회원정보가 출력되도록 설정
	void setUserBalance(String id, int newBalance);
	//입력받은 아이디에 새로운 자본금 설정
	void balanceChoice(Scanner scan);
	//자본금관리메뉴(입금1,출금2,종료3)메서드
	void depositMoney(Scanner scan);
	//(자본금-입금)자본금관리메뉴에서 입금을 선택 시 출력되는 메서드, ID가 일치할 경우에만 입금이 되도록 설정
	void withdrawMoney(Scanner scan);
	//(자본금-출금)자본금관리메뉴에서 출금을 선택 시 출력되는 메서드, ID가 일치할 경우에만 출금이 되도록 설정
	void rankUsersByBalance();
	//(자본금-순위)자본금을 기준으로 내림차순으로 순위 결정 메서드
	void userUpdate() throws IOException;
	//(유저업뎃)유저정보를 'user.txt'로 생성하는 메서드
	void downList() throws IOException;
	//유저정보가 등록된 파일을 ID,password,이름,자본금으로 각각 나누어 구분하는 메서드
	void printInBox(String s);
	//(디자인)네모상자
	
	

}
