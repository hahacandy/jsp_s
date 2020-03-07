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
import model.member.MemberVO;

/**
 * Servlet implementation class BoardWriteServlet
 */
@WebServlet("/BoardWriteServlet")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		request.setAttribute("user", (MemberVO)session.getAttribute("user"));
		
		RequestDispatcher rd = request.getRequestDispatcher("Board/board_write.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		BoardDAO dao = new BoardDAO();
		HttpSession session = request.getSession();
		List<Object> infos = new ArrayList<Object>();
		MemberVO user = (MemberVO)session.getAttribute("user"); 
		
		infos.add(user.getName());
		infos.add(user.getEmail());
		infos.add(request.getParameter("subject"));
		infos.add(request.getParameter("contents"));
		//infos.add(request.getRemoteAddr());
		infos.add(((MemberVO)session.getAttribute("user")).getUserid()); //db의 ip컬럼자리에 userid를 주는이유는 게시글이 회원전용이라, 해당 게시글을 작성한 회원만 수정 삭제를 할수있게 하기위함
		
		boolean result = dao.writePost(infos);
		
		if(result) {
			request.setAttribute("msg", "게시글 등록 성공");
			request.setAttribute("returnUri", "BoardListServlet");
		}else {
			request.setAttribute("msg", "게시글등록 실패");
			request.setAttribute("backPage", "-1");
		}
		

		RequestDispatcher rd = request.getRequestDispatcher("Alert/alert.jsp");
		rd.forward(request, response);
	}

}
