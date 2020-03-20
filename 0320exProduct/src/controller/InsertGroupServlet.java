package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductDAO;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/InsertGroup")
public class InsertGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("insertGroup.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String gcode = request.getParameter("gcode").toUpperCase();
		String gname = request.getParameter("gname");
		
		boolean result = ProductDAO.getInstance().insertGroupProduct(gcode, gname);
		
		if(result) {
			request.setAttribute("msg", "그룹 등록 성공");
			request.setAttribute("uri", "StatusGroup");
		}else {
			request.setAttribute("msg", "그룹 등록 실패");
			request.setAttribute("uri", "back()");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("alert.jsp");
		rd.forward(request, response);
		
		
	}

}
