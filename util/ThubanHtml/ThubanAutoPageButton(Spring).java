package com.jslhrd.util;

import java.net.URLEncoder;

public class ThubanAutoPageButton {

	private String uri = null;
	private int allPage = 0;
	private int currentPage = 0;
	private int startPage = 0;
	private int endPage = 0;
	
	private int currentPostCnt = 0;
	
	private int MAX_POST_COUNT = 10; 
	private int MAX_PAGE_COUNT = 10; 
	
	private int startPost = 0;
	private int endPost = 0;
	
	private String search = null;
	private String key = null;
	
	
	public ThubanAutoPageButton(String uri, int currentPage, int allPostCnt, int maxPostCnt, int maxPageCnt, String search, String key) {
		
		this.uri = uri;
		this.currentPage = currentPage;
		this.MAX_POST_COUNT = maxPostCnt;
		this.MAX_PAGE_COUNT = maxPageCnt;
		
		this.search = search;
		this.key = key;
		
		//페이지 설정
		this.allPage = ((allPostCnt-1)/MAX_POST_COUNT)+1;
		this.startPage = (((currentPage-1)/MAX_PAGE_COUNT)*MAX_PAGE_COUNT)+1;
		this.endPage = ((currentPage-1)/MAX_PAGE_COUNT) == (allPage/MAX_PAGE_COUNT) ? allPage : (startPage-1)+MAX_PAGE_COUNT;
		
		//현재 페이지가, 존재하는 페이지가 아닌경우
		if(currentPage <= 0)
			this.currentPage = 1;
		else if(currentPage > this.allPage)
			this.currentPage = this.allPage;
		
		//몇개의 포스트를 긁어올지
		startPost = ((this.currentPage-1)*MAX_POST_COUNT)+1;
		endPost = ((this.currentPage-1)*MAX_POST_COUNT)+MAX_POST_COUNT;
		
		//현재 페이지에 맞는 게시글 번호 측정
		this.currentPostCnt = allPostCnt-((this.currentPage-1)*MAX_POST_COUNT);
	}
	
	
	public String getPageButton() {
		StringBuffer pageButton = new StringBuffer();

		//페이지 이동 버튼 생성
		pageButton.append("<div align='center' style='font-size: 20px'>");
		//< 버튼 (이전페이지)
		if(MAX_PAGE_COUNT < startPage) {
			if(search == null) 
				pageButton.append("<a href='"+uri+"?page="+(startPage-1)+"'><</a>");
			else 
				pageButton.append("<a href='"+uri+"?page="+(startPage-1)+"&search="+search+"&key="+URLEncoder.encode(key)+"'><</a>");
		}
		//페이지 숫자 버튼
		for(int i=startPage; i<=endPage; i++) {
			if(i!=currentPage) {
				if(search == null) { //검색 사용하지 않았을 경우
					pageButton.append("&nbsp;[<a href='"+uri+"?page="+i+"'>");
				}else { //검색 사용했을 경우
					pageButton.append("&nbsp;[<a href='"+uri+"?page="+i+"&search="+search+"&key="+URLEncoder.encode(key)+"'>");
				}
				
				pageButton.append(i);
				pageButton.append("</a>]&nbsp;");
				
			}else {
				pageButton.append("&nbsp;[<b>"+i+"</b>]&nbsp;");
			}
		}
		//> 버튼(다음페이지)
		if(allPage > endPage) {
			if(search == null) 
				pageButton.append("<a href='"+uri+"?page="+(endPage+1)+"'>></a>");
			else
				pageButton.append("<a href='"+uri+"?page="+(endPage+1)+"&search="+search+"&key="+URLEncoder.encode(key)+"'>></a>");
		}
		
		return String.valueOf(pageButton);
	}
	
	public int getCurrentPostCnt() {
		return currentPostCnt;
	}


	public int getStartPost() {
		return startPost;
	}


	public int getEndPost() {
		return endPost;
	}
	
	public int getAllPage() {
		return allPage;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	

}
