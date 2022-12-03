package com.fastcampus.ch2;

import java.util.Arrays;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User {

	private String id;
	private String pwd;
	private String name;
	private String[] hobby;
	private Date birth;		// 스프링이 기본적으로 변환 가능한 날짜 형식이 아닐 경우 에러
	private String sns;		// 스프링이 배열(String[] sns)을 문자열로 자동 변환
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date join;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] gethobby() {
		return hobby;
	}
	public void sethobby(String[] hobby) {
		this.hobby = hobby;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getSns() {
		return sns;
	}
	public void setSns(String sns) {
		this.sns = sns;
	}
	public Date getJoin() {
		return join;
	}
	public void setJoin(Date join) {
		this.join = join;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", pwd=" + pwd + ", name=" + name + ", hobby=" + Arrays.toString(hobby) +
				", birth=" + birth + ", sns=" + sns + ", join=" + join + "]";
	}
	
}
