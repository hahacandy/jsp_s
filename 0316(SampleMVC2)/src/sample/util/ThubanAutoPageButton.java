package sample.util;
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
	
	private String addTag = null;
	
	
	
	public ThubanAutoPageButton(String uri, int currentPage, int allPostCnt, int maxPostCnt, int maxPageCnt, String addTag) {
		
		this.uri = uri;
		this.currentPage = currentPage;
		this.addTag = addTag;
		this.MAX_POST_COUNT = maxPostCnt;
		this.MAX_PAGE_COUNT = maxPageCnt;
		
		startPost = ((currentPage-1)*MAX_POST_COUNT)+1;
		endPost = ((currentPage-1)*MAX_POST_COUNT)+MAX_POST_COUNT;

		//페이지 설정
		this.allPage = ((allPostCnt-1)/MAX_POST_COUNT)+1;
		this.startPage = (((currentPage-1)/MAX_PAGE_COUNT)*MAX_PAGE_COUNT)+1;
		this.endPage = ((currentPage-1)/MAX_PAGE_COUNT) == (allPage/MAX_PAGE_COUNT) ? allPage : (startPage-1)+MAX_PAGE_COUNT;
		
		//현재 페이지에 맞는 게시글 번호 측정
		this.currentPostCnt = allPostCnt-((currentPage-1)*MAX_POST_COUNT);
	}
	
	
	public String getPageButton() {
		StringBuffer pageButton = new StringBuffer();

		//페이지 이동 버튼 생성
		pageButton.append("<div align='center' style='font-size: 20px'>");
		//< 버튼 (이전페이지)
		if(MAX_PAGE_COUNT < startPage) {
			if(addTag == null) 
				pageButton.append("<a class='page' href='"+uri+"&page="+(startPage-1)+"'><</a>");
			else 
				pageButton.append("<a class='page' href='"+uri+"&page="+(startPage-1)+addTag+"'><</a>");
		}
		//페이지 숫자 버튼
		for(int i=startPage; i<=endPage; i++) {
			if(i!=currentPage) {
				if(addTag == null) { //검색 사용하지 않았을 경우
					pageButton.append("&nbsp;[<a class='page' href='"+uri+"&page="+i+"'>");
				}else { //검색 사용했을 경우
					pageButton.append("&nbsp;[<a class='page' href='"+uri+"&page="+i+addTag+"'>");
				}
				
				pageButton.append(i);
				pageButton.append("</a>]&nbsp;");
				
			}else {
				pageButton.append("&nbsp;[<b>"+i+"</b>]&nbsp;");
			}
		}
		//> 버튼(다음페이지)
		if(allPage > endPage) {
			if(addTag == null) 
				pageButton.append("<a class='page' href='"+uri+"&page="+(endPage+1)+"'>></a>");
			else
				pageButton.append("<a class='page' href='"+uri+"&page="+(endPage+1)+addTag+"'>></a>");
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
	
	
	
	

}
