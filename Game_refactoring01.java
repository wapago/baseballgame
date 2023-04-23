package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 1. 요구사항에 맞는 숫자를 만든다.
// 2. 요구사항에 맞는 숫자를 입력받는다.
// 3. 두 숫자를 비교한다.
public class Game_refactoring01 {
	public static final int LENGTH_OF_NUMBER = 3;
	public static String answer;
	
	public static void main(String[] args) {
		start();
		
	}
	
	public static void start() {
		generateRndNumber();
	}
	
	public static void generateRndNumber() {
		List<Integer> oneToNine = new ArrayList<>();
		Integer[] rndNumberArr = new Integer[3];
		String[] rndNumber = new String[3];
		
		for(int i=1; i<=9; i++) {
			oneToNine.add(i);
		}
		Collections.shuffle(oneToNine);
		
		for(int i=0; i<3; i++) {
			rndNumberArr[i] = oneToNine.get(i);
			rndNumber[i] = String.valueOf(rndNumberArr[i]);
		}
		
		answer = String.join("", rndNumber);
	}
}
