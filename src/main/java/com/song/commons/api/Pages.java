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
	private Integer currPage;

	/** 页尺寸（显示数量） */
	private Integer pageSize;

	/** 数据总数 */
	private Integer totalNum;

	/** 总页数 */
	private Integer totalPage;

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

}
