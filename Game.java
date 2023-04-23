package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {
	private static boolean exists(Integer opponent[], Integer index) {
		for (int i = 0; i < opponent.length; i++) {
			if (opponent[i] == index)
				return true;
		}
		return false;
	}
	
	private static boolean isValid(String strInput, String[] strInputArr) {
		// 3. 입력값 유효성 검사
		if (Arrays.asList(strInputArr).indexOf("0") != -1) {
			System.out.println("*** 0은 입력할 수 없습니다 ***");
			return false;
		}
		if (strInput.length() != 3) {
			System.out.println("*** 세자리 숫자만 입력할 수 있습니다 ***");
			return false;
		}
		for (int i = 0; i < strInputArr.length; i++) {
			for (int j = 0; j < strInputArr.length; j++) {
				if (i == j)
					continue;
				if (strInputArr[i].equals(strInputArr[j])) {
					System.out.println("*** 같은 숫자는 입력할 수 없습니다 ***");
					return false;
				}
			}
		}
		
		return true;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			// 1. 임의의 정답만들기
			Integer opponent[] = new Integer[3];
			int index = 0;
			List<String> strList = new ArrayList<>();

			for (int i = 0; i < opponent.length; i++) {
				do {
					index = ((int) (Math.random() * 9) + 1);
				} while (exists(opponent, index));
				opponent[i] = index;
			}
			for (Integer opp : opponent) {
				strList.add(String.valueOf(opp));
			}

			while (true) {
				// 2. 사용자로부터 입력받기
				System.out.print("숫자를 입력해주세요 : ");
				Integer input = scanner.nextInt();

				String strInput = String.valueOf(input);
				String[] strInputArr = strInput.split("");

				boolean isValid = isValid(strInput, strInputArr);
				if(isValid == false)
					break;

				// 4. 맞춰보기
				int strike = 0;
				int ball = 0;

				for (int i = 0; i < strInputArr.length; i++) {
					if (strList.indexOf(strInputArr[i]) != -1) {
						if (strInputArr[i].equals(strList.get(i))) {
							strike++;
						} else {
							ball++;
						}
					}
				}

//				String answer = String.join("", strList);
//				System.out.println("###" + answer + "###");

				if (strike == 3) {
					System.out.println("3개의 숫자를 모두 맞히셨습니다!");
					break;
				} else {
					System.out.println(strike + "스트라이크, " + ball + "볼");
				}
			}
			
			System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
			Integer input2 = scanner.nextInt();
			
			if(input2 == 2)	{
				System.out.println("게임을 종료합니다.");
				break;	
			}
		}
		
		scanner.close();
	}
}
