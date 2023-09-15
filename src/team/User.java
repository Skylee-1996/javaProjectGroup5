package team;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class User {
	Scanner scan = new Scanner(System.in);	
    private HashMap<String, Integer> user1 = new HashMap<>(); //아디 비번
    private HashMap<String, Integer> user2 = new HashMap<>(); //이름 잔액
    
    //메뉴
    public void menu() {
    	System.out.println("----user data----");
    	System.out.println("1.회원 등록 | 2.회원 삭제 | 3.회원 수정 | 4.회원 검색 | 5.자본금 관리 | 6.순위 | 7.종료");
    	System.out.println("메뉴 입력> ");
    }
    
    //회원등록
    public void userAdd() {
    	System.out.println("등록하실 회원ID,비밀번호,회원명,자본금을 입력해주세요.");
    	System.out.println("회원ID: ");
    	String id = scan.next();
    	System.out.println("비밀번호: ");
    	int password = scan.nextInt();
    	user1.put(id, password);
    	System.out.println("회원명: ");
    	String name = scan.next();
    	System.out.println("자본금: ");
    	int balance = scan.nextInt();
    	user2.put(name, balance);
    	System.out.println("입력하신 정보로 등록되었습니다.");
    	System.out.println("메뉴를 선택해주세요.> ");
    	
    	System.out.println("메뉴를 선택해주세요.> ");	
    }
    
    //회원삭제
    public void deleteUser() {
    	System.out.println("삭제하실 회원ID와 회원명을 입력해주세요.");
    	System.out.println("회원ID: ");
    	String id = scan.next();
    	System.out.println("회원명: ");
    	String name = scan.next();
<<<<<<< HEAD
    	if (user1.containsKey(id) && user2.containsKey(name)) {
    	user1.remove(id);
    	user2.remove(name);
    	System.out.println("삭제가 완료되었습니다.");
    	System.out.println("메뉴를 선택해주세요.> ");
    }
    	}
    
=======
    	 if(user1.containsKey(id) && user2.containsKey(name)) {
    	        user1.remove(id);
    	        user2.remove(name);
    	        System.out.println("삭제가 완료되었습니다.");
    	    } else {
    	        System.out.println("회원정보가 일치하지 않거나 없는 회원입니다.");
    	    }
    	    System.out.println("메뉴를 선택해주세요.> ");

    }
>>>>>>> 33ab7dbb000e3b9168e9c6ad9d4fad602251fd46

    //회원수정
    public void editUser() {
        System.out.println("수정하실 회원ID와 회원명을 입력해주세요.");
	    System.out.println("회원ID: ");
        String currentId = scan.next();
        System.out.println("회원명: ");
        String currentName = scan.next();
        if (user1.containsKey(currentId) && user2.containsKey(currentName)) {
            int choice;
            do {
                System.out.println("---회원정보 수정 메뉴---");
                System.out.println("1. ID 수정");
                System.out.println("2. 비밀번호 수정");
                System.out.println("3. 회원명 수정");
                System.out.println("0. 종료");
                choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("새로운 ID를 입력해주세요.");
                        String newId = scan.next();
                        if (!user1.containsKey(newId)) {
                            int password = user1.remove(currentId);
                            user1.put(newId, password);
                            System.out.println("입력하신 정보로 수정되었습니다.");
                        } else {
                            System.out.println("기존 아이디와 일치하므로 변경하실 수 없습니다.");
                        }
                        break;
                    case 2:
                        System.out.println("새로운 비밀번호를 입력해주세요.");
                        int newPassword = scan.nextInt();
                        user1.put(currentId, newPassword);
                        System.out.println("입력하신 정보로 수정되었습니다.");
                        break;

                    case 3:
                        System.out.println("새로운 회원명을 입력해주세요.");
                        String newName = scan.next();
                        if (!user2.containsKey(newName)) {
                            int balance = user2.remove(currentName); 
                            user2.put(newName, balance);
                            System.out.println("입력하신 정보로 수정되었습니다.");
                        } else {
                            System.out.println("기존 회원명과 일치하므로 변경하실 수 없습니다.");
                        }
                        break;
                    case 0:
                        System.out.println("회원수정을 종료합니다."); break;

                    default:
                        System.out.println("잘못된 메뉴를 입력하셨습니다. 다시 입력해주세요."); break;
                }
            } while (choice != 0);
        } else {
            System.out.println("회원이 존재하지 않거나 회원정보가 일치하지 않습니다.");
        }
    }

    //회원검색 후 출력
		public void searchUser() {
		    System.out.println("검색하실 회원ID와 회원명을 입력해주세요.");
		    System.out.println("회원ID: ");
		    String id = scan.next();
		    System.out.println("회원명: ");
		    String name = scan.next();
		    if (user1.containsKey(id)) {
		        System.out.println("회원ID: " + id + ",비밀번호: " + user1.get(id));
		    } else {
		        System.out.println("존재하지 않는 회원입니다.");
		    }
		    if (user2.containsKey(name)) {
		        System.out.println("회원명: " + name + ",자본금: " + user2.get(name));
		    } else {
		        System.out.println("존재하지 않는 회원입니다.");
		    }
		}
		
		//자본금-메뉴
		public void balanceChoice() {
			System.out.println("입금:1 ,출금:2, 종료:3");
			int num = scan.nextInt();
			switch(num) {
			case 1: depositMoney(); break;
			case 2: withdrawMoney(); break;
			case 3: System.out.println("자본금 관리메뉴를 종료합니다."); break;
			default: System.out.println("잘못된 메뉴입니다. 다시 선택해주세요.");
			}
			System.out.println("메뉴를 선택해주세요.");
		}

		//자본금-입금
		public void depositMoney() {
		    System.out.println("회원ID와 회원명을 입력해주세요.");
		    System.out.println("회원ID: ");
		    String id = scan.next();
		    System.out.println("회원명: ");
		    String name = scan.next();
		    if (user1.containsKey(id) && user2.containsKey(name)) {
		        System.out.println("입금하실 금액을 입력해주세요.");
		        int amount = scan.nextInt();
		        int currentBalance = user2.get(name);
		        user2.put(name, currentBalance + amount);
		        System.out.println("입력하신 " + amount + "원이 성공적으로 입금처리되었습니다.");
		        System.out.println("현재 자본금: " + user2.get(name));
		    } else {
		        System.out.println("회원정보가 일치하지 않거나 없는 회원입니다.");
		    }
		}
		
		//자본금-출금
		public void withdrawMoney() {
		    System.out.println("회원ID와 회원명을 입력해주세요.");
		    System.out.println("회원ID: ");
		    String id = scan.next();
		    System.out.println("회원명: ");
		    String name = scan.next();
		    if (user1.containsKey(id) && user2.containsKey(name)) {
		        System.out.println("출금하실 금액을 입력해주세요.");
		        int amount = scan.nextInt();
		        int balance = user2.get(name);
		        if (balance >= amount) {
		            user2.put(name, balance - amount);
		            System.out.println("입력하신 " + amount + "원이 성공적으로 출금처리되었습니다.");
		            System.out.println("잔액: " + user2.get(name));
		        } else {
		            System.out.println("잔액이 부족합니다.");
		        }
		    } else {
		        System.out.println("회원정보가 일치하지 않거나 없는 회원입니다.");
		    }
		}

	//순위
		public void rankUser() {
	        List<Map.Entry<String, Integer>> list = new ArrayList<>(user2.entrySet());
	        list.sort(new Comparator<Map.Entry<String, Integer>>() {
	           
	        	@Override
	            public int compare(Map.Entry<String, Integer> user1, Map.Entry<String, Integer> user2) {
	                return user2.getValue().compareTo(user1.getValue());
	            }
	        });
	        
	        System.out.println("--자본금 순위--");
	        int rank = 1;
	        for (Map.Entry<String, Integer> entry : list) {
	            System.out.println("랭크 " + rank + "위: " + entry.getKey() + " ,잔액: " + entry.getValue());
	            rank++;
	        }
	    }
	    
}