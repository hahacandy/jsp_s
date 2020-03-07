package servlet.guest;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.guest.GuestDAO;
import model.guest.GuestVO;

/**
 * Servlet implementation class GuestListServlet
 */
@WebServlet("/GuestListServlet")
public class GuestListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int allPage = 0;
		int currentPage = 0;
		int startPage = 0;
		int endPage = 0;
		int allPostCnt = 0;
		int postCnt = 0;
		String urlGet = "";
		int currentMode = 0; //검색 미사용(0)인지, 검색사용(1)인지 구분하기 확인하기 위함

		final int MAX_POST_COUNT = 10; 
		final int MAX_PAGE_COUNT = 10; 
		
		GuestDAO dao = new GuestDAO();
		List<GuestVO> list = null;
		StringBuffer pageButton = new StringBuffer();
		
		String search = null;
		String key = null;
		String key2 = null;
		
		
		
		//게시글 처음 접속시 페이지 매개변수 넣어줌
		if(request.getParameter("page") == null && request.getParameter("search2") == null) {
			urlGet = "page=1";
			response.sendRedirect("GuestListServlet?" + urlGet);
			return;
		}
		//첫검색시 주소창에 매개변수 붙여줌
		if(request.getParameter("search2") != null) {
			urlGet = "page=1&search=" + request.getParameter("search2") + "&key=" + URLEncoder.encode(request.getParameter("key"), "utf-8");
			response.sendRedirect("GuestListServlet?" + urlGet);
			return;
		}
		
		//현재 페이지
		currentPage = Integer.valueOf(request.getParameter("page"));

		//게시글을 보여주는 방식을 판단함(전체게시글, 검색게시글)
		if(request.getParameter("search") == null) {
			//전체 게시글 갯수 측정
			allPostCnt = dao.getPostCount();
		}else {
			search = request.getParameter("search");
			key = request.getParameter("key");
			key2 = URLEncoder.encode(request.getParameter("key"), "utf-8");

			//검색 하여 나오는 게시글 갯수 측정
			allPostCnt = dao.getPostCount(search, key);
			
			currentMode=1; //페이지 이동버튼에 필요함, 현재 검색을 하였다는 증거
		}
		
		
		//페이지 설정
		allPage = ((allPostCnt-1)/MAX_POST_COUNT)+1;
		startPage = (((currentPage-1)/MAX_PAGE_COUNT)*MAX_PAGE_COUNT)+1;
		endPage = ((currentPage-1)/MAX_PAGE_COUNT) == (allPage/MAX_PAGE_COUNT) ? allPage : (startPage-1)+MAX_PAGE_COUNT;
		
		
		//실제로 존재하는 페이지이상을 보려고하면 1페이지로 되돌림
		if(allPage < currentPage) {
			response.sendRedirect("GuestListServlet");
			return;
		}
			
		
		//현재 페이지에 맞는 게시글 번호 측정
		postCnt = allPostCnt-((currentPage-1)*MAX_POST_COUNT);

		
		//전체게시물인지, 검색게시물인지 확인하고 실제로 가져옴
		if(request.getParameter("search") == null) {
			list = dao.getPost(currentPage, MAX_POST_COUNT);
		}else {
			list = dao.getPost(currentPage, MAX_POST_COUNT, search, key);
		}
		
		
		//페이지 이동 버튼 생성
		pageButton.append("<div align='center' style='font-size: 20px'>");
		//<< 버튼 (이전페이지)
		if(MAX_PAGE_COUNT < startPage)
			pageButton.append("<a href='GuestListServlet?page="+(startPage-1)+"'><<</a>");
		//페이지 숫자 버튼
		for(int i=startPage; i<=endPage; i++) {
			if(i!=currentPage) {
				if(currentMode == 0) { //검색 사용하지 않았을 경우
					pageButton.append("&nbsp;[<a href='GuestListServlet?page="+i+"'>");
				}else { //검색 사용했을 경우
					pageButton.append("&nbsp;[<a href='GuestListServlet?page="+i+"&search="+request.getParameter("search")+"&key="+request.getParameter("key")+"'>");
				}
				
				pageButton.append(i);
				pageButton.append("</a>]&nbsp;");
				
			}else {
				pageButton.append("&nbsp;[<b>"+i+"</b>]&nbsp;");
			}
		}
		//>> 버튼(다음페이지)
		if(allPage > endPage)
			pageButton.append("<a href='GuestListServlet?page="+(endPage+1)+"'>>></a>");

		//값 전달
		request.setAttribute("allPostCnt", allPostCnt);
		request.setAttribute("postCnt", postCnt);
		request.setAttribute("list", list);
		request.setAttribute("allPage", allPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageButton", pageButton);
		request.setAttribute("search", search);
		request.setAttribute("key", key);
		request.setAttribute("key2", key2);

		

		RequestDispatcher rd = request.getRequestDispatcher("Guest/guest_list.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//검색했을경우 포스트에 오기때문에 get에서 쓰기위해 넘겨줌
		if(request.getParameter("search2") != null) {
			request.setAttribute("search2", request.getParameter("search2"));
			request.setAttribute("key", request.getParameter("key"));
		}
		
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
