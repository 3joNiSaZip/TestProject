package com.kh.jsp.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable{

	private static final long serialVersionUID = 3L;
	
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private int bcount;
	private String boardfile;
	private Date bdate;
	private String delflag;
	

	public Board() { }

	public Board(int bno, String btitle, String bcontent, String bwriter, int bcount, String boardfile, Date bdate) {
		
		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bwriter = bwriter;
		this.bcount = bcount;
		this.boardfile = boardfile;
		this.bdate = bdate;
	}

	public Board(int bno, String btitle, String bcontent, String bwriter, int bcount, String boardfile, Date bdate,
			String delflag) {

		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bwriter = bwriter;
		this.bcount = bcount;
		this.boardfile = boardfile;
		this.bdate = bdate;
		this.delflag = delflag;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getBwriter() {
		return bwriter;
	}

	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}

	public int getBcount() {
		return bcount;
	}

	public void setBcount(int bcount) {
		this.bcount = bcount;
	}

	public String getBoardfile() {
		return boardfile;
	}

	public void setBoardfile(String boardfile) {
		this.boardfile = boardfile;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	@Override
	public String toString() {
		return "Board [bno=" + bno + ", btitle=" + btitle + ", bcontent=" + bcontent + ", bwriter=" + bwriter
				+ ", bcount=" + bcount + ", boardfile=" + boardfile + ", bdate=" + bdate + ", delflag=" + delflag + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bcontent == null) ? 0 : bcontent.hashCode());
		result = prime * result + bcount;
		result = prime * result + ((bdate == null) ? 0 : bdate.hashCode());
		result = prime * result + bno;
		result = prime * result + ((boardfile == null) ? 0 : boardfile.hashCode());
		result = prime * result + ((btitle == null) ? 0 : btitle.hashCode());
		result = prime * result + ((bwriter == null) ? 0 : bwriter.hashCode());
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
		Board other = (Board) obj;
		if (bcontent == null) {
			if (other.bcontent != null)
				return false;
		} else if (!bcontent.equals(other.bcontent))
			return false;
		if (bcount != other.bcount)
			return false;
		if (bdate == null) {
			if (other.bdate != null)
				return false;
		} else if (!bdate.equals(other.bdate))
			return false;
		if (bno != other.bno)
			return false;
		if (boardfile == null) {
			if (other.boardfile != null)
				return false;
		} else if (!boardfile.equals(other.boardfile))
			return false;
		if (btitle == null) {
			if (other.btitle != null)
				return false;
		} else if (!btitle.equals(other.btitle))
			return false;
		if (bwriter == null) {
			if (other.bwriter != null)
				return false;
		} else if (!bwriter.equals(other.bwriter))
			return false;
		if (delflag == null) {
			if (other.delflag != null)
				return false;
		} else if (!delflag.equals(other.delflag))
			return false;
		return true;
	}
	
	
	

	
	
}
