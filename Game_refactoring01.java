package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Game_refactoring01 {
	// 메소드를 여러개 만들어서 하긴 했는데 static을 너무 많이 쓴건 아닌지 껄적지근함. static써보는거 처음.
	public static Scanner scanner = new Scanner(System.in);
	
	public static final int LENGTH_OF_NUMBER = 3;
	public static final int MIN_NUMBER_RANGE = 1;
	public static final int MAX_NUMBER_RANGE = 9;
	
	public static Integer[] randomNumberArr = new Integer[LENGTH_OF_NUMBER];
	public static String playNumStr;
	public static String[] playNumStrArr;
	public static int[] playNumIntArr = new int[LENGTH_OF_NUMBER];
	
	public static int strike = 0;
	public static int ball = 0;
	
	public static void main(String[] args) {
		start();
	}
	
	public static void start() {
		createRndNumber();
		play();
	}
	
	public static void createRndNumber() {
		List<Integer> oneToNine = new ArrayList<>();
		
		for(int i=MIN_NUMBER_RANGE; i<=MAX_NUMBER_RANGE; i++) {
			oneToNine.add(i);
		}
		
		Collections.shuffle(oneToNine);
		
		for(int i=0; i<LENGTH_OF_NUMBER; i++) {
			randomNumberArr[i] = oneToNine.get(i);
		}
	}
	
	public static void play() {
		System.out.println("숫자를 입력하세요 : ");
		
		int playNumber = scanner.nextInt();
		
		isValidInput(playNumber);
		matchWithRndNumber();
		printResult();
		isRestart();
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
		strike = 0;
		ball = 0;
		
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
	}
	
	public static void printResult() {
		if(strike == 0 && ball == 0) {
			System.out.println("낫싱");
			play();
		}
		
		if(strike != 3 && (strike != 0 || ball != 0)) {
			System.out.println(strike + "스트라이크, " + ball + "볼");
			play();
		}
		
		if(strike == 3) {
			System.out.println("3개의 숫자를 모두 맞히셨습니다!!");
		}
	}
	
	public static void isRestart() {
		System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
		
		int restart = scanner.nextInt();
		
		if(restart == 1) {
			start();
		}
		
		if(restart == 2) {
			System.exit(0);
		}
		
		throw new IllegalArgumentException("1 또는 2를 입력해주세요");
	}
}
