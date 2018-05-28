package com.kh.jsp.notice.model.vo;

import java.sql.Date;

public class Notice implements java.io.Serializable{

	private static final long serialVersionUID = 10L;
	
	private int nno;
	private String ntitle;
	private String ncontent;
	private String nwriter;
	private int ncount;
	private Date ndate;

	public Notice() {
		
	}
	
	public Notice(int nno, String ntitle, String ncontent, String nwriter, int ncount, Date ndate) {
		super();
		this.nno = nno;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.nwriter = nwriter;
		this.ncount = ncount;
		this.ndate = ndate;
	}
	public int getNno() {
		return nno;
	}
	public void setNno(int nno) {
		this.nno = nno;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getNwriter() {
		return nwriter;
	}
	public void setNwriter(String nwriter) {
		this.nwriter = nwriter;
	}
	public int getNcount() {
		return ncount;
	}
	public void setNcount(int ncount) {
		this.ncount = ncount;
	}
	public Date getNdate() {
		return ndate;
	}
	public void setNdate(Date ndate) {
		this.ndate = ndate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notice other = (Notice) obj;
		if (ncontent == null) {
			if (other.ncontent != null)
				return false;
		} else if (!ncontent.equals(other.ncontent))
			return false;
		if (ncount != other.ncount)
			return false;
		if (ndate == null) {
			if (other.ndate != null)
				return false;
		} else if (!ndate.equals(other.ndate))
			return false;
		if (nno != other.nno)
			return false;
		if (ntitle == null) {
			if (other.ntitle != null)
				return false;
		} else if (!ntitle.equals(other.ntitle))
			return false;
		if (nwriter == null) {
			if (other.nwriter != null)
				return false;
		} else if (!nwriter.equals(other.nwriter))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Notice [nno=" + nno + ", ntitle=" + ntitle + ", ncontent=" + ncontent + ", nwriter=" + nwriter
				+ ", ncount=" + ncount + ", ndate=" + ndate + "]";
	}
	
	
	
	
}
