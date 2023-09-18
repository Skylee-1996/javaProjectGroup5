package team;

import java.io.IOException;
import java.util.Scanner;

public interface IBaccarat {
	void start(Scanner scan, String id, int userBalance, UserManager u) throws InterruptedException, IOException;
	/*바카라 게임시작 메서드
	 * 카지노메인에서 gameselect 메서드를 통해 스캐너, 아이디, 자본금, 유저리스트를 포함한 유저매니저 객체를 받아옴
	 */
}
