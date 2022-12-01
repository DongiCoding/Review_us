package com.ezen.springboard.vo;

import java.sql.Date;

public class CommentVO {
//	CREATE TABLE COMMENT (
//			CNO INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
//		    BNO INT NOT NULL,
//		    WRITER VARCHAR(30) NOT NULL,
//		    CONTENT VARCHAR(300) NOT NULL,
//			REGDATE DATETIME NOT NULL DEFAULT NOW(),
//		    CONSTRAINT COMMENT_BOARD_NO_FK
//				FOREIGN KEY (BNO)
//		        REFERENCES BOARD (BOARD_NO)
//		);
	
	private int cno;
	private int bno;
	private String writer;
	private String content;
	private Date regdate;
	
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "CommentVO [cno=" + cno + ", bno=" + bno + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + "]";
	}
	
	
}
