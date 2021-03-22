package com.java.phone;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PhoneController {
	public static void listPhoneBook() {
		PhoneDao pDao = new PhoneRepository();
		List<PhoneVo> list = pDao.getList();
		
		Iterator<PhoneVo> it = list.iterator();
		
		while(it.hasNext()) {
			PhoneVo pVo = it.next();
			System.out.printf("%s, %d, %d%n", pVo.getName(), pVo.getHp(), pVo.getTel());
		}
	}
	
	public static void insertPhone() {
		Scanner s = new Scanner(System.in);
		System.out.print("이름 : ");
		String name = s.next();
		System.out.print("휴대폰 번호 : ");
		Long hp = s.nextLong();
		System.out.print("집 번호 : ");
		Long tel = s.nextLong();
		
		PhoneVo pVo = new PhoneVo(name, hp, tel);
		
		PhoneDao pDao = new PhoneRepository();
		boolean success = pDao.insert(pVo);
		
		System.out.println("PHONE INSERT : " + (success ? "성공" : "실페"));
		
		s.close();
	}
	
	public static void deletePhone() {
		Scanner s = new Scanner(System.in);
		System.out.print("삭제 이름 입력 : ");
		String name = s.next();
		
		PhoneDao pDao = new PhoneRepository();
		boolean success = pDao.delete(name);
		
		System.out.println("PHONE DELETE : " + (success ? "성공" : "실패"));
		
		s.close();
	}
	
	public static void searchPhone() {
		Scanner s = new Scanner(System.in);
		System.out.print("검색 이름 입력 : ");
		String name = s.next();
		
		PhoneDao pDao = new PhoneRepository();
		List<PhoneVo> list = pDao.search(name);
		
		Iterator<PhoneVo> it = list.iterator();
		
		while(it.hasNext()) {
			PhoneVo pVo = it.next();
			System.out.println(pVo);
		}
		s.close();
	}
	
	public void execute() {
		PhoneView pv = new PhoneView();
		pv.phoneView();
	}
}
