package servlet.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/MemberInsertServlet")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("Member/userinfo_insert2.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		MemberDAO dao = new MemberDAO();
		List<Object> infos = new ArrayList<Object>();
		int result = 0;
		
		request.setCharacterEncoding("utf-8");
		
		infos.add(request.getParameter("name"));
		infos.add(request.getParameter("userid"));
		infos.add(request.getParameter("passwd"));
		infos.add(request.getParameter("tel"));
		infos.add(request.getParameter("email"));
		
		result = dao.signUpMember(infos);
		
		
		request.setAttribute("result", result);
		
		RequestDispatcher rd = request.getRequestDispatcher("Member/userinfo_insert2_pro.jsp");
		rd.forward(request, response);

	}

}
