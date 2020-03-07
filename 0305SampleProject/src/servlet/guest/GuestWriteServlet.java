package servlet.guest;

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
import model.guest.GuestDAO;
import model.member.MemberVO;

/**
 * Servlet implementation class GuestWriteServlet
 */
@WebServlet("/GuestWriteServlet")
public class GuestWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("Guest/guest_write.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		GuestDAO dao = new GuestDAO();

		List<Object> infos = new ArrayList<Object>();

		
		infos.add(request.getParameter("name"));
		infos.add(request.getParameter("subject"));
		infos.add(request.getParameter("contents"));

		boolean result = dao.writePost(infos);
		
		if(result) {
			request.setAttribute("msg", "게시글 등록 성공");
			request.setAttribute("returnUri", "GuestListServlet");
		}else {
			request.setAttribute("msg", "게시글등록 실패");
			request.setAttribute("backPage", "-1");
		}
		

		RequestDispatcher rd = request.getRequestDispatcher("Alert/alert.jsp");
		rd.forward(request, response);
	}

}
