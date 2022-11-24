package com.ezen.springboard.vo;

import java.sql.Date;

public class UserVO {
	private String userId;
	private String userPwd;
	private String userNm;
	private String userEmail;
	private Date userRgd;
	private int rprtCnt;
	private String userRole;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Date getUserRgd() {
		return userRgd;
	}
	public void setUserRgd(Date userRgd) {
		this.userRgd = userRgd;
	}
	public int getRprtCnt() {
		return rprtCnt;
	}
	public void setRprtCnt(int rprtCnt) {
		this.rprtCnt = rprtCnt;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userPwd=" + userPwd + ", userNm=" + userNm + ", userEmail=" + userEmail
				+ ", userRgd=" + userRgd + ", rprtCnt=" + rprtCnt + ", userRole=" + userRole + "]";
	}
	
	
}