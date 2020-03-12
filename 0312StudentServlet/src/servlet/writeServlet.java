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

import model.StudentDAO;

/**
 * Servlet implementation class writeServlet
 */
@WebServlet("/writeServlet")
public class writeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public writeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("write.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		StudentDAO dao = new StudentDAO();
		List<Object> infos = new ArrayList<Object>();
		
		infos.add(Integer.valueOf(request.getParameter("bun")));
		infos.add(request.getParameter("name"));
		infos.add(request.getParameter("gender"));
		infos.add(Integer.valueOf(request.getParameter("kor")));
		infos.add(Integer.valueOf(request.getParameter("eng")));
		infos.add(Integer.valueOf(request.getParameter("mat")));
		infos.add(request.getParameter("regdate"));
		
		boolean result = dao.writeStudent(infos);

		response.sendRedirect("listServlet");
	}

}
