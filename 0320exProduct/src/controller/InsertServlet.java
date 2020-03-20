package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
@WebServlet("/Insert")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("insert.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		List<Object> infos = new ArrayList<Object>();
		infos.add(request.getParameter("code"));
		infos.add(request.getParameter("pname"));
		infos.add(Integer.valueOf(request.getParameter("cost")));
		infos.add(Integer.valueOf(request.getParameter("pnum")));
		infos.add(Integer.valueOf(request.getParameter("jnum")));
		infos.add(Integer.valueOf(request.getParameter("sale")));
		infos.add(request.getParameter("gcode"));
		
		boolean result = ProductDAO.getInstance().insertProduct(infos);
		
		if(result) {
			request.setAttribute("msg", "제품 등록 성공");
			request.setAttribute("uri", "Status");
		}else {
			request.setAttribute("msg", "제품 등록 실패");
			request.setAttribute("uri", "back()");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("alert.jsp");
		rd.forward(request, response);
		
		
	}

}
