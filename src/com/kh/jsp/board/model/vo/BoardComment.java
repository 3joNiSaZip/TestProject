package com.kh.jsp.board.model.vo;

import java.sql.Date;

public class BoardComment implements java.io.Serializable{

	private static final long serialVersionUID = 5L;

	private int cno;
	private int bno;
	private String ccontent;
	private String cwriter;
	private Date cdate;
	private String delflag;
	public BoardComment() {
		super();
	}
	public BoardComment(int cno, int bno, String ccontent, String cwriter, Date cdate, String delflag) {
		super();
		this.cno = cno;
		this.bno = bno;
		this.ccontent = ccontent;
		this.cwriter = cwriter;
		this.cdate = cdate;
		this.delflag = delflag;
	}
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
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public String getCwriter() {
		return cwriter;
	}
	public void setCwriter(String cwriter) {
		this.cwriter = cwriter;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "BoardComment [cno=" + cno + ", bno=" + bno + ", ccontent=" + ccontent + ", cwriter=" + cwriter
				+ ", cdate=" + cdate + ", delflag=" + delflag + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bno;
		result = prime * result + ((ccontent == null) ? 0 : ccontent.hashCode());
		result = prime * result + ((cdate == null) ? 0 : cdate.hashCode());
		result = prime * result + cno;
		result = prime * result + ((cwriter == null) ? 0 : cwriter.hashCode());
		result = prime * result + ((delflag == null) ? 0 : delflag.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardComment other = (BoardComment) obj;
		if (bno != other.bno)
			return false;
		if (ccontent == null) {
			if (other.ccontent != null)
				return false;
		} else if (!ccontent.equals(other.ccontent))
			return false;
		if (cdate == null) {
			if (other.cdate != null)
				return false;
		} else if (!cdate.equals(other.cdate))
			return false;
		if (cno != other.cno)
			return false;
		if (cwriter == null) {
			if (other.cwriter != null)
				return false;
		} else if (!cwriter.equals(other.cwriter))
			return false;
		if (delflag == null) {
			if (other.delflag != null)
				return false;
		} else if (!delflag.equals(other.delflag))
			return false;
		return true;
	}
	
	
}
