
public class test {
	public static void main(String[] args) {
		

		int allPage = 0;
		int currentPage = 21;
		int startPage = 0;
		int endPage = 0;
		int allPostCnt = 25;
		int postCnt = 0;
		String urlGet = "";
		
		final int MAX_POST_COUNT = 1; 
		final int MAX_PAGE_COUNT = 10; 
		
		//페이지 설정
		allPage = ((allPostCnt-1)/MAX_POST_COUNT)+1;
		startPage = (((currentPage-1)/MAX_PAGE_COUNT)*MAX_PAGE_COUNT)+1;
		endPage = ((currentPage-1)/MAX_PAGE_COUNT) == (allPage/MAX_PAGE_COUNT) ? allPage : (startPage-1)+MAX_PAGE_COUNT;
		
		

		//;
		
		System.out.println("전체 페이지:" + allPage);
		System.out.println("시작 페이지:" + startPage);
		System.out.println("현재 페이지:" + currentPage);
		System.out.println("끝 페이지:" + endPage);


	}
}
