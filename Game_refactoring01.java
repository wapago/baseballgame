package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Game_refactoring01 {
	private static Scanner scanner = new Scanner(System.in);
	private static final int LENGTH_OF_NUMBER = 3;
	
	private static Integer[] randomNumberArr = new Integer[LENGTH_OF_NUMBER];
	private static int[] playNumIntArr = new int[LENGTH_OF_NUMBER];
	private static String playNumStr;
	private static String[] playNumStrArr;
	
	public static void main(String[] args) {
		start();
	}
	
	public static void start() {
		randomNumberArr = createRndNumber();
		play();
	}
	
	public static Integer[] createRndNumber() {
		List<Integer> oneToNine = new ArrayList<>();
		Integer[] rndNumberArr = new Integer[LENGTH_OF_NUMBER];
		
		for(int i=1; i<=9; i++) {
			oneToNine.add(i);
		}
		
		Collections.shuffle(oneToNine);
		
		for(int i=0; i<LENGTH_OF_NUMBER; i++) {
			rndNumberArr[i] = oneToNine.get(i);
		}
		
		return rndNumberArr;
	}
	
	public static void play() {
		System.out.println("숫자를 입력하세요 : ");
		
		int playNumber = scanner.nextInt();
		
		isValidInput(playNumber);
		matchWithRndNumber();
	}
	
	public static void isValidInput(int playNumber) {
		playNumStr = String.valueOf(playNumber);
		playNumStrArr = playNumStr.split("");
		
		List<String> playNumStrList = Arrays.asList(playNumStrArr);
		Set<String> playNumStrSet = new HashSet<>(playNumStrList);
		
		if(playNumStr.contains("0")) {
			throw new IllegalArgumentException("0은 입력할 수 없습니다.");
		}
		
		if(playNumStrArr.length != LENGTH_OF_NUMBER) {
			throw new IllegalArgumentException("세 자리 정수를 입력해주세요");
		}
		
		if(playNumStrList.size() != playNumStrSet.size()) {
			throw new IllegalArgumentException("같은 숫자는 입력할 수 없습니다.");
		}
	}
	
	public static void matchWithRndNumber() {
		int strike = 0;
		int ball = 0;
		
		List<Integer> randomNumberList = Arrays.asList(randomNumberArr);
		
		for(int i=0; i<LENGTH_OF_NUMBER; i++) {
			playNumIntArr[i] = Integer.parseInt(playNumStrArr[i]);
		}
		
		for(int i=0; i<LENGTH_OF_NUMBER; i++) {
			if(randomNumberList.get(i).equals(playNumIntArr[i])) {
				strike++;
			}
			
			if(!randomNumberList.get(i).equals(playNumIntArr[i]) && randomNumberList.contains(playNumIntArr[i])) {
				ball++;
			}
		}
		
		printResult(strike, ball);
	}
	
	public static void printResult(int strike, int ball) {
		if(strike == 0 && ball == 0) {
			System.out.println("낫싱");
			play();
		}
		
		if(strike == 3) {
			System.out.println("3개의 숫자를 모두 맞히셨습니다!!");
			isRestart();
		}
		
		System.out.println(strike + "스트라이크, " + ball + "볼");
		play();
	}
	
	public static void isRestart() {
		System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
		int restart = scanner.nextInt();
		
		if(restart == 1)
			start();
		
		if(restart == 2) 
			System.exit(0);
		
		throw new IllegalArgumentException("1 또는 2를 입력해주세요");
	}
}
