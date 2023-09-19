package team;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserManager {
	  public List<User> userList = new ArrayList<>();
	  
	 //회원등록
	  public void userAdd(Scanner scan) throws IOException {
		    System.out.println("등록하실 회원ID,비밀번호,회원명,자본금을 입력해주세요.");
		    
		    System.out.print("회원ID: ");
		    String id = scan.next();
		    
		    //아이디 중복 확인
		    if (isUserIdExists(id)) {
		        System.out.println("이미 존재하는 아이디입니다.");
		        return;
		    }
		    
		    System.out.print("비밀번호: ");
		    String password = scan.next();     
		    
		    System.out.print("회원명: ");
		    String name = scan.next();

		    //입력 검증
		    if (id.isEmpty() || password.isEmpty() || name.isEmpty()) {
		        System.out.println("잘못된 입력입니다. 모든 필드를 올바르게 채우세요.");
		        return;
		    }

		    userList.add(new User(id, password, name));
		    System.out.println("입력하신 정보로 등록되었습니다.");
		    userUpdate();
	  }

	  //아이디 중복검사
	  private boolean isUserIdExists(String id) {
		    for (User user : userList) {
		        if (user.getId().equals(id)) {
		            return true;
		        }
		    }
		    return false;
		}
	  
    //회원삭제
	  public void deleteUser(Scanner scan) throws IOException {
		    System.out.println("삭제하실 회원ID와 비밀번호를 입력해주세요.");
		    
		    System.out.print("회원ID: ");
		    String id = scan.next();
		    
		    boolean userFound = false;
		    for (int i = 0; i < userList.size(); i++) {
		        if (userList.get(i).getId().equals(id)) {
		            userList.remove(i);
		            System.out.println("삭제가 완료되었습니다.");
		            userUpdate();
		            System.out.print("메뉴를 선택해주세요.> ");
		            userFound = true;
		            break;
		        }
		    }
		    
		    if (!userFound) {
		        System.out.println("존재하지 않는 아이디거나 회원명이 일치하지 않습니다.");
		    }
		}   

    //회원수정
	  public void editUser(Scanner scan) throws IOException {
		    System.out.println("기존 회원ID를 입력해주세요.");
		    System.out.println("회원ID: ");
		    String id = scan.next();
		    boolean found = false;

		    for (int i = 0; i < userList.size(); i++) {
		        User currentUser = userList.get(i);
		        if (currentUser.getId().equals(id)) {
		            found = true;
		            int choice = -1;
		            do {
		                System.out.println("---회원정보 수정 메뉴---");
		                System.out.println("1. ID 수정");
		                System.out.println("2. 비밀번호 수정");
		                System.out.println("3. 회원명 수정");
		                System.out.println("4. 종료");
		                try {
		                    choice = scan.nextInt();
		                } catch (InputMismatchException e) {
		                    System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
		                    scan.next(); 
		                    continue;
		                }

		                switch (choice) {
		                    case 1:
		                        System.out.println("새로운 ID를 입력해주세요.");
		                        String newId = scan.next();
		                        currentUser.setId(newId);
		                        System.out.println("입력하신 정보로 수정되었습니다.");
		                        break;
		                    case 2:
		                        System.out.println("새로운 비밀번호를 입력해주세요.");
		                        String newPassword = scan.next();
		                        currentUser.setPassword(newPassword);
		                        System.out.println("입력하신 정보로 수정되었습니다.");
		                        break;
		                    case 3:
		                        System.out.println("새로운 회원명을 입력해주세요.");
		                        String newName = scan.next();
		                        currentUser.setName(newName);
		                        System.out.println("입력하신 정보로 수정되었습니다.");
		                        break;
		                    case 4:
		                        break;
		                    default:
		                        System.out.println("잘못된 입력입니다.");
		                }
		                userUpdate();
		            } while (choice != 4);
		            System.out.println("프로그램을 종료합니다.");
		            break;
		        }
		    }

		    if (!found) {
		        System.out.println("해당 ID와 비밀번호를 가진 사용자를 찾을 수 없습니다.");
		    }
		}
                        
    //회원검색 후 출력
    public void searchUser(Scanner scan) {
        System.out.println("검색하실 회원ID를 입력해주세요.");
        System.out.println("회원ID: ");
        String id = scan.next();
        boolean found = false;

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId().equals(id)) {
                found = true;
                System.out.println("☞"+id+"님의 정보입니다.");
                System.out.println("회원ID: " + userList.get(i).getId() + ", 비밀번호: " + userList.get(i).getPassword());
                System.out.println("회원명: " + userList.get(i).getName() + ", 자본금: " + userList.get(i).getBalance());
                break;
            }
        }

        if (!found) {
            System.out.println("존재하지 않는 회원입니다.");
        }
    }      
    public void setUserBalance(String id, int newBalance) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                    user.setBalance(newBalance);
                    return;
            }
        }
        System.out.println("해당 ID를 가진 사용자를 찾을 수 없습니다.");
    }
      //자본금-메뉴
    public void balanceChoice(Scanner scan) {
        while (true) {
            System.out.println("입금:1 ,출금:2, 종료:3");
            try {
                int num = scan.nextInt();
                switch(num) {
                    case 1: 
                        depositMoney(scan); 
                        break;
                    case 2: 
                        withdrawMoney(scan); 
                        break;
                    case 3: 
                        System.out.println("자본금 관리메뉴를 종료합니다."); 
                        return;
                    default: 
                        System.out.println("잘못된 메뉴입니다. 다시 선택해주세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자로 다시 입력해주세요.");
                scan.next();  // 입력 버퍼를 비웁니다.
            }
            System.out.println("메뉴를 선택해주세요.");
        }
    }
      //자본금-입금
    public void depositMoney(Scanner scan) {
        System.out.println("회원ID를 입력해주세요.");
        System.out.println("회원ID: ");
        String id = scan.next();
        for(int i=0; i<userList.size(); i++) {
            if (userList.get(i).getId().equals(id)) {
                try {
                    System.out.println("입금하실 금액을 입력해주세요.");
                    int amount = scan.nextInt();
                    int balance = userList.get(i).setBalance(amount);
                    System.out.println("입력하신 " + amount + "원이 성공적으로 입금처리되었습니다.");
                    System.out.println("잔액: " +userList.get(i).getBalance()+"원");
                    return;
                } catch (InputMismatchException e) {
                    System.out.println("유효하지 않은 금액입니다. 숫자로 입력해주세요.");
                    scan.next();
                }
            }
        }
        System.out.println("회원정보가 일치하지 않거나 없는 회원입니다.");
    }
      
      //자본금-출금
      public void withdrawMoney(Scanner scan) {
          System.out.println("회원ID를 입력해주세요.");
          System.out.println("회원ID: ");
          String id = scan.next();
          for(int i=0;i<userList.size();i++) {             
          if (userList.get(i).getId().equals(id)) {
              System.out.println("출금하실 금액을 입력해주세요.");
              int amount = scan.nextInt();          
              if (userList.get(i).getBalance() >= amount) {
            	 userList.get(i).setBalance(-amount); 
                  System.out.println("입력하신 " + amount + "원이 성공적으로 출금처리되었습니다.");
                  System.out.println("잔액: " +userList.get(i).getBalance()+"원");
              } else {
                  System.out.println("잔액이 부족합니다.");
              }
          }        
      }
      }

       // 순위 결정 메서드
          public void rankUsersByBalance() {
              // 자본금을 기준으로 내림차순 정렬
              Collections.sort(userList, new Comparator<User>() {
                  @Override
                  public int compare(User user1, User user2) {
                      return Integer.compare(user2.getBalance(), user1.getBalance());
                  }
              });
           // 순위 부여
              for (int i = 0; i < userList.size(); i++) {
                  User user = userList.get(i);
                  user.setRank(i + 1); // 순위를 1부터 시작하도록 설정
              }

              // 순위 출력
              System.out.println("            ☠𝑹𝒂𝒏𝒌☠");
              System.out.println();
              for (User user : userList) {
                  System.out.println(" 순위: " + user.getRank() + " | 회원명: " + user.getName() + " | 자본금: " + user.getBalance());
                  System.out.println("⩶⩶⩶⩶⩶⩶⩶⩶⩶⩶⩶⩶⩶⩶⩶⩶⩶⩶");
                  System.out.println();
              }   
       }
          
      // 파일 업데이트
      	public void userUpdate() throws IOException {
    		// 파일출력
    		FileWriter fs = new FileWriter("user.txt");
    		BufferedWriter bw = new BufferedWriter(fs);
    		
    		StringBuffer sb = new StringBuffer();
    		//.append : 데이터 추가
    		String data = null;
    		for(User user : userList) {
    			sb.append(user.getId()+" "+user.getPassword()+" "+user.getName()+" "+user.getBalance());
    			sb.append("\r\n"); 
    		}
    		
    		data = sb.toString();
    		bw.write(data);
    		
    		bw.close();
    		fs.close();
    	}
      	public void downList() throws IOException {

            BufferedReader br = new BufferedReader(new FileReader("user.txt"));

            while(true) {
                String insert = br.readLine();
                if(insert == null) {
                    break;
                }

                String copyInfo[] = insert.split(" ");
                String id = copyInfo[0];
                String pass = copyInfo[1];
                String name = copyInfo[2];
                int balance = Integer.parseInt(copyInfo[3]);
                userList.add(new User(id, pass, name, balance)); 
               
                
                }
            br.close();
        }
      	

      	public static void printInBox(String s) {
    	   System.out.println("╔═══════════════════════════════════════════════╗");
	       System.out.println("  "+s+"      ");
	       System.out.println("╚═══════════════════════════════════════════════╝");
      	}

      
      public boolean endGame(Scanner scan, String id, int userBalance, UserManager u) throws InterruptedException, IOException {
	    	CasinoManager m = new CasinoManager();
	    	  System.out.println("1.다음게임시작 2.게임선택 3.종료");
	          int select = scan.nextInt();

	          switch(select) {
	           case 1: break;
	           case 2: m.selectGame(scan, id, userBalance, u); break;
	           case 3: System.out.println("프로그램을 종료합니다.");
	        	   	return false;
	          
	          }
	          return true;
	    }

}
