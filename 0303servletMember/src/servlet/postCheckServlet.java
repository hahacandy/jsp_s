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

import model.MemberDAO;
import model.MemberVO;
import model.ZIPcodeVO;

/**
 * Servlet implementation class postCheckServlet
 */
@WebServlet("/postCheck")
public class postCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public postCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("Member/post_check.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		MemberDAO dao = null;
		List<ZIPcodeVO> list = new ArrayList<ZIPcodeVO>();
		if(request.getParameter("addr") != null) {
			dao = new MemberDAO();
			list = dao.getSearchZip(request.getParameter("addr"));
			request.setAttribute("list", list);
		}

		RequestDispatcher rd = request.getRequestDispatcher("Member/post_check.jsp");
		rd.forward(request, response);
	}

}
