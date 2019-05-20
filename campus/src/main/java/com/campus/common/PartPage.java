//package com.campus.common;
//
//import java.util.List;
//
///***
// *	封装公共partpage对象,让....继承partpage对象 
// * @author Administrator
// */
//public class PartPage {
//	/**数据里面的总行数*/private int rowcount;
//	/**一页多少行数据*/private int pagesize;
//	/**查询起始行*/private int startrow;
//	/**当前点击的导航*/private int currnav;
//	/**页面维护的固定导航个数*/private int navnum;
//	/**页面数据整体导航个数  总共多少页*/private int navcount;
//	/**页面展示的起始导航*/ private int begin;
//	/**页面维护展示的结束导航*/private int end;
//	/**上一页*/ private int prev;
//	/**下一页*/ private int next;
//	/**首页*/ private int first=1;
//	/**尾页*/ private int last;
//	/**存放分页的数据*/ private List pageData;
//	//
//	public PartPage(int rowcount, int pagesize, int currnav, int navnum) {
//		super();
//		this.rowcount = rowcount;
//		this.pagesize = pagesize;
//		this.currnav = currnav;
//		this.navnum = navnum;
//		//新增;  ceil向上取整
//		this.navcount=(int) Math.ceil((rowcount * 1.0) /pagesize); 
//		this.last=this.navcount;
//		
//		//大于第一页    增强容错性
//		this.currnav=Math.max(this.first,currnav); //维护小于第一页, 维护固定第一页 取最大
//		this.currnav=Math.min(this.currnav, this.last); //超过最后一页,维护最后一页  取最小
//		//当前页的起始行 startrow = (当前点击的导航页-1)*一页多少行数据
//		this.startrow=(currnav-1)*pagesize;
//		//处理上一页
//		this.prev=Math.max(this.first,currnav-1);
//		//处理下一页  如果当前导航页是最后一页
//		this.next=Math.min(this.navcount, currnav+1);
//		//begin  页面展示的起始导航
//		this.begin=Math.max(1, currnav-navnum/2);
//		//end 页面展示的结束导航
//		this.end=Math.max(navcount, begin+navnum-1);
//		//当前导航是 76 -10 + 1=67,  1-10+1=-8
//		if((this.end-this.begin) < (this.navnum-1)) {
//			this.begin=Math.max(1, this.last-navnum+1);
//		}
//	}	
//	//
//	public int getRowcount() {
//		return rowcount;
//	}
//
//	public void setRowcount(int rowcount) {
//		this.rowcount = rowcount;
//	}
//
//	public int getPagesize() {
//		return pagesize;
//	}
//
//	public void setPagesize(int pagesize) {
//		this.pagesize = pagesize;
//	}
//
//	public int getStartrow() {
//		return startrow;
//	}
//
//	public void setStartrow(int startrow) {
//		this.startrow = startrow;
//	}
//
//	public int getCurrnav() {
//		return currnav;
//	}
//
//	public void setCurrnav(int currnav) {
//		this.currnav = currnav;
//	}
//
//	public int getNavnum() {
//		return navnum;
//	}
//
//	public void setNavnum(int navnum) {
//		this.navnum = navnum;
//	}
//
//	public int getNavcount() {
//		return navcount;
//	}
//
//	public void setNavcount(int navcount) {
//		this.navcount = navcount;
//	}
//
//	public int getBegin() {
//		return begin;
//	}
//
//	public void setBegin(int begin) {
//		this.begin = begin;
//	}
//
//	public int getEnd() {
//		return end;
//	}
//
//	public void setEnd(int end) {
//		this.end = end;
//	}
//
//	public int getPrev() {
//		return prev;
//	}
//
//	public void setPrev(int prev) {
//		this.prev = prev;
//	}
//
//	public int getNext() {
//		return next;
//	}
//
//	public void setNext(int next) {
//		this.next = next;
//	}
//
//	public int getFirst() {
//		return first;
//	}
//
//	public void setFirst(int first) {
//		this.first = first;
//	}
//
//	public int getLast() {
//		return last;
//	}
//
//	public void setLast(int last) {
//		this.last = last;
//	}
//
//	public List getPageData() {
//		return pageData;
//	}
//
//	public void setPageData(List pageData) {
//		this.pageData = pageData;
//	}
//	
//	
//}
