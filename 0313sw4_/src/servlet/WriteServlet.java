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

import model.ProductDAO;

/**
 * Servlet implementation class WriteServlet
 */
@WebServlet("/product_write")
public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteServlet() {
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
		
		//제품 추가를 했을경우
		if(request.getParameter("code") != null) {
			ProductDAO dao = ProductDAO.getInstance();
			List<Object> infos = new ArrayList<Object>();
			int result = 0;
			
			
			infos.add(request.getParameter("code"));
			infos.add(request.getParameter("pname"));
			System.out.println(request.getParameter("pname"));
			infos.add(Integer.valueOf(request.getParameter("cost")));
			infos.add(Integer.valueOf(request.getParameter("pnum")));
			infos.add(Integer.valueOf(request.getParameter("jnum")));
			infos.add(Integer.valueOf(request.getParameter("sale")));
			infos.add(request.getParameter("gcode"));
			
			result = dao.writeProduct(infos);
			
			//write.jsp창에서 바로 제품 등록 성공, 실패 메시지를 확인할수 있게 하기 위함
			request.setAttribute("result", result); 
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("write.jsp");
		rd.forward(request, response);
	}

}
