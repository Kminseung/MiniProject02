package com.java.phone;

import java.util.Scanner;

public class PhoneView {
	private int n = 0;

	public void phoneView() {
		System.out.println("************************************");
		System.out.println("*	   전화번호 관리 프로그램	   *");
		System.out.println("************************************");
		
		Scanner s = new Scanner(System.in);
		while(n != 5) {
			System.out.println("1. 리스트  2. 등록  3. 삭제  4. 검색  5. 종료");
			System.out.println("------------------------------------");
			System.out.print("메뉴번호> ");
			n = s.nextInt();
			System.out.println();
			
			switch(n) {
			case 1:
				// 리스트
				System.out.println("<1. 리스트>");
				PhoneController.listPhoneBook();
				System.out.println();
				continue;
			case 2:
				// 등록
				System.out.println("<2. 등록>");
				PhoneController.insertPhone();
				System.out.println("[등록되었습니다.]\n");
				continue;
			case 3:
				// 삭제
				System.out.println("<3. 삭제>");
				PhoneController.deletePhone();
				System.out.println("[삭제되었습니다.]\n");
				continue;
			case 4:
				// 검색
				System.out.println("<4. 검색>");
				PhoneController.searchPhone();
				System.out.println();
				continue;
			case 5:
				// 종료
				System.out.println();
				System.out.println("************************************");
				System.out.println("*	      감사합니다.		   *");
				System.out.println("************************************");
				break;
			default:
				System.out.println("[다시 입력해 주세요.]\n");
				continue;
			}
		}
		s.close();
	}
}