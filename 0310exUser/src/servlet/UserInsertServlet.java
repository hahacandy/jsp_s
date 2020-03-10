package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDAO;
import util.ThubanPasswd;

/**
 * Servlet implementation class UserInsertServlet
 */
@WebServlet("/user_insert")
public class UserInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("User/user_insert.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO dao = UserDAO.getInstance();
		
		request.setCharacterEncoding("utf-8");
		List<Object> infos = new ArrayList<Object>();
		
		infos.add(request.getParameter("name"));
		infos.add(request.getParameter("userid"));
		String passwd = request.getParameter("passwd");
		passwd = ThubanPasswd.changePasswdToSHA256(passwd);
		System.out.println(passwd);
		infos.add(passwd);
		infos.add(request.getParameter("tel"));
		infos.add(request.getParameter("email1") + "@" + request.getParameter("email2"));
		
		boolean result = dao.regiUser(infos);
		
		if(result) {
			System.out.println("회원가입 성공");
		}else {
			System.out.println("회원가입 실패");
		}
		
		response.sendRedirect("user_list");
	}

}
