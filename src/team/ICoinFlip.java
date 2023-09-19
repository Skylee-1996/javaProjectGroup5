package team;

import java.io.IOException;
import java.util.Scanner;

public interface ICoinFlip {
	void gameStart(Scanner scan, String id, int userBalance, UserManager uM) throws IOException, InterruptedException;
	int[] round(Scanner scan);
	int bet(Scanner scan);
	int circle() throws InterruptedException;
	void printFront();
	void print1();
	void print2();
	void print3();
	void print4();
	void print5();
	void print6();
	void print7();
	void printBack();
}
