package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;

/**
 * Servlet implementation class MemberIDCheckServlet
 */
@WebServlet("/id_check")
public class MemberIDCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberIDCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("Member/id_check.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid =  request.getParameter("userid");
		
		MemberDAO dao = new MemberDAO();
		
		int cnt = dao.checkMemberID(userid);
		boolean overRap = false;
		if(cnt > 0)
			overRap = true;
		
		request.setAttribute("userid", userid);
		request.setAttribute("overRap", overRap);
		
		RequestDispatcher rd = request.getRequestDispatcher("Member/id_check.jsp");
		rd.forward(request, response);
	}

}
