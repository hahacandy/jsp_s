package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemberDAO;
import model.member.MemberVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("Member/userlogin_form.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDAO dao = new MemberDAO();
		MemberVO vo = null;
		
		vo = dao.login(request.getParameter("userid"), request.getParameter("passwd"));
		
		if(vo != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", vo);
			session.setMaxInactiveInterval(600);
			request.setAttribute("login", "ok");
		}else {
			request.setAttribute("login", "no");
		}
		
		//로그인실패시 로그인장소에따라  도착주소를 다르게 하기 위함
		if(request.getParameter("sublogin") != null) {
			request.setAttribute("sublogin", request.getParameter("sublogin"));
		}
		
		//로그인 성공하면 원래 있던 주소로 돌아가기위함
		if(request.getParameter("returnUri") != null) {
			request.setAttribute("returnUri", request.getParameter("returnUri"));
		}

		RequestDispatcher rd = request.getRequestDispatcher("Member/userlogin_form_pro.jsp");
		rd.forward(request, response);

	}

}
