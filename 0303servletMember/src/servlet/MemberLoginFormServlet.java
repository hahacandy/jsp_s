package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import model.MemberDAO;

/**
 * Servlet implementation class MemberLoginFormServlet
 */
@WebServlet("/userlogin_form")
public class MemberLoginFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginFormServlet() {
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
		
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		
		//-1이면 아이디부터 틀림, 0 비밀번호 틀림, 1 로그인성공
		int result = dao.checkLogin(userid, passwd);
		
		HttpSession session = request.getSession();
		
		if(result == 1) {
			session.setAttribute("userid", userid);
			session.setMaxInactiveInterval(60*10);
			response.sendRedirect("userlogin_ok");
		}else {
			request.setAttribute("result", result);
			RequestDispatcher rd = request.getRequestDispatcher("Member/userlogin_fail.jsp");
			rd.forward(request, response);
		}
		
		
	}

}
