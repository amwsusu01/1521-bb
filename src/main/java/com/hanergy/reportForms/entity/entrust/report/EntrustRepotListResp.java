package com.hanergy.reportForms.entity.entrust.report;

import java.io.Serializable;
import java.util.List;

public class EntrustRepotListResp implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<CandidateReportEntity>  list;
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalSize;
	public List<CandidateReportEntity> getList() {
		return list;
	}
	public void setList(List<CandidateReportEntity> list) {
		this.list = list;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}
	
}
