package servlet.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.board.BoardDAO;
import model.board.BoardVO;
import model.member.MemberVO;

/**
 * Servlet implementation class BoardModifyServlet
 */
@WebServlet("/BoardModifyServlet")
public class BoardModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardDAO dao = new BoardDAO();
		HttpSession session = request.getSession();
		int idx = Integer.valueOf(request.getParameter("idx"));
		
		//현재 게시물이 수정하려는 이용자가 쓴 게시물이 맞는지, 아니라면 전 페이지로 돌아감
		BoardVO vo = dao.getOnePost(idx); // 현재 게시물의 정보
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(vo == null || user == null || !vo.getIp().equals(user.getUserid())) {
			request.setAttribute("msg", "올바르지 않은 접근");
			request.setAttribute("backPage", "-1");
			RequestDispatcher rd = request.getRequestDispatcher("Alert/alert.jsp");
			rd.forward(request, response);
			return;
		}
		
		//현재 사용자가 해당 게시물의 주인이므로, 해당 게시물의 정보를 자동완성시키기 위해, 해당 게시물의 정보를 넣음
		request.setAttribute("vo", vo);
		
		//원래 주소로 돌아가기 위한 값을 전달
		if(request.getParameter("page") != null) {
			request.setAttribute("page", request.getParameter("page"));
		}
		if(request.getParameter("search") != null) {
			request.setAttribute("search", request.getParameter("search"));
			request.setAttribute("key", request.getParameter("key"));
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("Board/board_modify.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		BoardDAO dao = new BoardDAO();
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		
		List<Object> infos = new ArrayList<Object>();
		infos.add(request.getParameter("subject"));
		infos.add(request.getParameter("contents"));
		infos.add(Integer.valueOf(request.getParameter("idx")));
		infos.add(user.getUserid());
		
		boolean result = dao.modifyPost(infos);
		

		if(result) {
			String uri = "BoardViewServlet?idx=" + (request.getParameter("idx"));
			if(request.getParameter("page").length() > 0)
				uri += "&page=" + request.getParameter("page");
			if(request.getParameter("search").length() > 0) {
				uri += "&search=" + request.getParameter("search");
				uri += "&key=" + request.getParameter("key");
			}
			System.out.println(uri);
		
			request.setAttribute("msg", "게시물 수정 완료");
			request.setAttribute("returnUri", uri);
		}else {
			request.setAttribute("msg", "게시물 수정 실패");
			request.setAttribute("backPage", -1);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("Alert/alert.jsp");
		rd.forward(request, response);
		
	}

}
