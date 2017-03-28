package Utils;

import java.util.List;

public class PageBean<T> {
	private int currentPage = 1;
	private int totalPage;
	private int totalInfo;
	private int pageInfo = 15;
	private List<T> pageData;       // 数据

	public List<T> getPageData() {
		return pageData;
	}
	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		if(totalInfo%pageInfo==0){
			totalPage = totalInfo/pageInfo;
		}else{
			totalPage = totalInfo/pageInfo+1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalInfo() {
		return totalInfo;
	}
	public void setTotalInfo(int totalInfo) {
		this.totalInfo = totalInfo;
	}
	public int getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(int pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	
}
