package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDAO;
import util.ThubanPasswd;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/user_login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("User/user_login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDAO dao = UserDAO.getInstance();
		
		request.setCharacterEncoding("utf-8");
		
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		passwd = ThubanPasswd.changePasswdToSHA256(passwd);
		
		System.out.println(userid);
		System.out.println(passwd);
		
		boolean result = dao.login(userid, passwd);
		
		if(result) {
			System.out.println("로그인 성공");
		}else {
			System.out.print("로그인 실패");
		}
		response.sendRedirect("user_login");
	}

}
