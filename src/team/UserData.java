package team;

import java.util.Scanner;


public class UserData {
	//1.회원등록 | 2.회원삭제 | 3.회원수정 | 4.회원검색 | 5.자본금 | 6.순위 | 7.종료
		public static void main(String[] args) {
			Scanner scan = new Scanner(System.in);
			User u = new User();
			u.menu();
			int menu = 0;	
			do {		
				menu = scan.nextInt();
				switch(menu) {
				case 1: u.userAdd(); break;
				case 2: u.deleteUser(); break;
				case 3: u.editUser(); break;
				case 4: u.searchUser(); break;
				case 5: u.balanceChoice(); break;
				case 6: u.rankUser(); break;          
				case 7: break;
				}				
			} while(menu != 7);
			System.out.println("프로그램을 종료합니다.");
		}

	
		
	

}
