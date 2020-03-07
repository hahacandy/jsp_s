package servlet.board;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.board.BoardDAO;
import model.member.MemberVO;

/**
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet("/BoardDeleteServlet")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardDAO dao = new BoardDAO();
		HttpSession session = request.getSession();
		//해당게시글이 삭제하려는 유저가 작성한 게시글이면 삭제함
		boolean result = dao.deletePost(Integer.valueOf(request.getParameter("idx")), ((MemberVO)session.getAttribute("user")).getUserid()); 
		
		if(result) {
			String uri = "BoardListServlet?";
			
			//원래 주소로 돌아가기 위한 값을 전달
			if(request.getParameter("page") != null) {
				uri += "page=" + request.getParameter("page");
			}
			if(request.getParameter("search") != null) {
				uri += "&search=" + request.getParameter("search");
				uri += "&key=" + URLEncoder.encode(request.getParameter("key"), "utf-8");
			}

			request.setAttribute("msg", "게시글 삭제 완료");
			request.setAttribute("returnUri", uri);
		}else {
			request.setAttribute("msg", "게시글 삭제 실패");
			request.setAttribute("backPage", -1);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("Alert/alert.jsp");
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
