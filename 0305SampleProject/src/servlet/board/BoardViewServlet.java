package servlet.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.board.BoardDAO;
import model.board.BoardVO;
import model.member.MemberVO;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/BoardViewServlet")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		BoardDAO dao = new BoardDAO();
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		String boardCokies = null;
		boolean isReadCnt = false;
		
		//하루에 한번씩만 해당게시물 조회수 1증가
		for(int i=0; i<cookies.length; i++) {
			if(cookies[i].getName().equals("board")) {
				boardCokies = cookies[i].getValue();
				if(cookies[i].getValue().contains("B"+request.getParameter("idx"))) {
					isReadCnt=true;
					break;
				}
			}
		}
		if(!isReadCnt) {
			Cookie temp = null;
			String value = null;
			if(boardCokies == null) {
				value = "B"+request.getParameter("idx");
			}else {
				value = boardCokies+"B"+request.getParameter("idx");
			}
			temp = new Cookie("board", value);
			temp.setMaxAge(60*60*24);
			response.addCookie(temp);
			dao.increasePostReadCnt(Integer.valueOf(request.getParameter("idx")));
		}
		

		MemberVO user = (MemberVO)session.getAttribute("user");
		BoardVO vo = dao.getOnePost(Integer.valueOf(request.getParameter("idx")));
		
		//해당 게시물 내용부분에 줄바꿈기능
		vo.setContents(util.SqlMark.lineBreak(vo.getContents()));
		
		request.setAttribute("user", user);
		request.setAttribute("vo", vo);
		
		//페이지 또는 검색한 기록이 있다면, 값 넘겨줌
		if(request.getParameter("page") != null) {
			request.setAttribute("page", Integer.valueOf(request.getParameter("page")));
		}
		if(request.getParameter("search") != null) {
			request.setAttribute("search", request.getParameter("search"));
			request.setAttribute("key", request.getParameter("key"));
		}
		

		
		RequestDispatcher rd = request.getRequestDispatcher("Board/board_view.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
