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
import model.ProductVO;

/**
 * Servlet implementation class ModifyServlet
 */
@WebServlet("/Modify")
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String code = request.getParameter("code");
		
		ProductVO vo =  ProductDAO.getInstance().getOneProduct(code);
		List<ProductVO> list = ProductDAO.getInstance().getAllGroup();
		
		request.setAttribute("vo", vo);
		request.setAttribute("list", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("modify.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		//수정일때
		if(request.getParameter("cost") != null &&
				request.getParameter("pnum") != null &&
				request.getParameter("jnum") != null &&
				request.getParameter("sale") != null &&
				request.getParameter("gcode") != null &&
				request.getParameter("code") != null &&
				request.getParameter("pname") != null) {
			
			List<Object> infos = new ArrayList<Object>();
			
			infos.add(Integer.valueOf(request.getParameter("cost")));
			infos.add(Integer.valueOf(request.getParameter("pnum")));
			infos.add(Integer.valueOf(request.getParameter("jnum")));
			infos.add(Integer.valueOf(request.getParameter("sale")));
			infos.add(request.getParameter("gcode"));
			infos.add(request.getParameter("code"));
			infos.add(request.getParameter("pname"));
			
			boolean result = ProductDAO.getInstance().modifyProduct(infos);
			
			if(result) {
				request.setAttribute("msg", "제품 수정 완료");
				request.setAttribute("uri", "Status");
			}else {
				request.setAttribute("msg", "제품 수정 실패");
				request.setAttribute("uri", "back()");
			}
			
		}else if(request.getParameter("code") != null && request.getParameter("pname") != null ){ //삭제 일때
			
			boolean result = ProductDAO.getInstance().deleteProduct(request.getParameter("code"), request.getParameter("pname"));
			
			if(result) {
				request.setAttribute("msg", "제품 삭제 완료");
				request.setAttribute("uri", "Status");
			}else {
				request.setAttribute("msg", "제품 삭제 실패");
				request.setAttribute("uri", "back()");
			}
			
		}else {//암것도 아닐때
			request.setAttribute("uri", "Status");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("alert.jsp");
		rd.forward(request, response);
		
	}

}
