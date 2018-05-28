package com.kh.jsp.board.model.vo;

import java.sql.Date;
import java.util.ArrayList;

public class Attachment {
	private int fno;
	private int bno;
	private String originName;
	private String changeName;
	private String filePath;
	private Date uploadDate;
	private int fileLevel;
	private int downloadCount;
	private String delflag;
	
	public Attachment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Attachment(int fno, int bno, String originName, String changeName, String filePath, Date uploadDate,
			int fileLevel, int downloadCount, String delflag) {
		super();
		this.fno = fno;
		this.bno = bno;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.fileLevel = fileLevel;
		this.downloadCount = downloadCount;
		this.delflag = delflag;
	}

	public int getFno() {
		return fno;
	}

	public void setFno(int fno) {
		this.fno = fno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}

	public int getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bno;
		result = prime * result + ((changeName == null) ? 0 : changeName.hashCode());
		result = prime * result + ((delflag == null) ? 0 : delflag.hashCode());
		result = prime * result + downloadCount;
		result = prime * result + fileLevel;
		result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
		result = prime * result + fno;
		result = prime * result + ((originName == null) ? 0 : originName.hashCode());
		result = prime * result + ((uploadDate == null) ? 0 : uploadDate.hashCode());
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
		Attachment other = (Attachment) obj;
		if (bno != other.bno)
			return false;
		if (changeName == null) {
			if (other.changeName != null)
				return false;
		} else if (!changeName.equals(other.changeName))
			return false;
		if (delflag == null) {
			if (other.delflag != null)
				return false;
		} else if (!delflag.equals(other.delflag))
			return false;
		if (downloadCount != other.downloadCount)
			return false;
		if (fileLevel != other.fileLevel)
			return false;
		if (filePath == null) {
			if (other.filePath != null)
				return false;
		} else if (!filePath.equals(other.filePath))
			return false;
		if (fno != other.fno)
			return false;
		if (originName == null) {
			if (other.originName != null)
				return false;
		} else if (!originName.equals(other.originName))
			return false;
		if (uploadDate == null) {
			if (other.uploadDate != null)
				return false;
		} else if (!uploadDate.equals(other.uploadDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Attachment [fno=" + fno + ", bno=" + bno + ", originName=" + originName + ", changeName=" + changeName
				+ ", filePath=" + filePath + ", uploadDate=" + uploadDate + ", fileLevel=" + fileLevel
				+ ", downloadCount=" + downloadCount + ", delflag=" + delflag + "]";
	}
	
	
}
