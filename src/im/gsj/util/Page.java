package im.gsj.util;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	private long total;
	private int pageSize = 12;
	private int pageNo = 1;
	private long totalPage;
	private int displaySize = 5;
	private List<T> list = new ArrayList<T>();

	public Page(int pageNo, int pageSize, long total) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.total = total;
	}
	
	public Page(int pageNo, long total) {
		this.pageNo = pageNo;
		this.total = total;
	}

	public Page(){
	}
	
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public long getTotalPage() {
		if(total%pageSize == 0){
			totalPage = total/pageSize;
		}
		else{
			totalPage = total/pageSize +1;
		}
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
}
