package com.kh.jsp.board.model.vo;

public class PageInfo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;

	private int currentPage;
	private int listCount;
	private int limit;
	private int startPage;
	private int endPage;
	private int maxPage;
	
	public PageInfo() {
		super();
	}

	public PageInfo(int currentPage, int listCount, int limit, int startPage, int endPage, int maxPage) {
		super();
		this.currentPage = currentPage;
		this.listCount = listCount;
		this.limit = limit;
		this.startPage = startPage;
		this.endPage = endPage;
		this.maxPage = maxPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PageInfo [currentPage=" + currentPage + ", listCount=" + listCount + ", limit=" + limit + ", startPage="
				+ startPage + ", endPage=" + endPage + ", maxPage=" + maxPage + "]";
	}
	
	
	
}

