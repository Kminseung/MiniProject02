package com.java.phone;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PhoneController {
	static Scanner s = new Scanner(System.in);
	
	public static void listPhoneBook() {
		PhoneDao pDao = new PhoneRepository();
		List<PhoneVo> list = pDao.getList();
		
		Iterator<PhoneVo> it = list.iterator();
		
		while(it.hasNext()) {
			PhoneVo pVo = it.next();
			System.out.printf("%d, %s, %s, %s%n", pVo.getId(), pVo.getName(), pVo.getHp(), pVo.getTel());
		}
	}
	
	public static void insertPhone() {
		System.out.print("이름 : ");
		String name = s.next();
		System.out.print("휴대폰 번호 : ");
		String hp = s.next();
		System.out.print("집 번호 : ");
		String tel = s.next();
		
		PhoneVo pVo = new PhoneVo(name, hp, tel);
		
		PhoneDao pDao = new PhoneRepository();
		pDao.insert(pVo);
	}
	
	public static void deletePhone() {
		System.out.print("삭제 ID 입력 : ");
		Long id = s.nextLong();
		
		PhoneDao pDao = new PhoneRepository();
		pDao.delete(id);
	}
	
	public static void searchPhone() {
		System.out.print("검색 이름 입력 : ");
		String name = s.next();
		
		PhoneDao pDao = new PhoneRepository();
		List<PhoneVo> list = pDao.search(name);
		
		Iterator<PhoneVo> it = list.iterator();
		
		while(it.hasNext()) {
			PhoneVo pVo = it.next();
			System.out.println(pVo);
		}
	}
	
	public void execute() {
		PhoneView pv = new PhoneView();
		pv.phoneView();
	}
}