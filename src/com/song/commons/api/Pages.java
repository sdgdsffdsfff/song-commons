package com.song.commons.api;


/**
 * 返回分页数据列表
 * 
 * @author 张松
 * 
 */
public abstract class Pages extends Result {

	private static final long serialVersionUID = -6716545993973050647L;

	/** 当前页码 */
	private int currPage;

	/** 页尺寸（显示数量） */
	private int pageSize;

	/** 数据总数 */
	private int totalNum;

	/** 总页数 */
	private int totalPage;

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
