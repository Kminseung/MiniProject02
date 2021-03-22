package com.java.phone;

public class PhoneVo {
	private String name ;
	private Long hp;
	private Long tel;
	
	// generator
	public PhoneVo() {
		// default generator
	}
	
	public PhoneVo(String name, Long hp) {
		this.name = name;
		this.hp = hp;
	}
	
	public PhoneVo(String name, Long hp, Long tel) {
		this(name, hp);
		this.tel = tel;
	}

	// getter / setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Long getHp() {
		return hp;
	}
	public void setHp(Long hp) {
		this.hp = hp;
	}

	public Long getTel() {
		return tel;
	}
	public void setTel(Long tel) {
		this.tel = tel;
	}
	
	
}
