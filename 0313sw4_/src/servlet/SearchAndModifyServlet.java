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
import model.ProductVO;

/**
 * Servlet implementation class SearchAndModifyServlet
 */
@WebServlet("/product_search_modify")
public class SearchAndModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAndModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("searchAndModify.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		if(request.getParameter("mode") != null) {
			
			ProductDAO dao = ProductDAO.getInstance();
			
			String mode = request.getParameter("mode");

			if(mode.equals("search")) {//조회 눌렀을떄
				String code = request.getParameter("code");
				ProductVO vo = dao.getProduct(code);
				request.setAttribute("vo", vo);
				
				request.setAttribute("mode", mode);

			}else if(mode.equals("modify")) {//수정 눌럿을때

				List<Object> infos = new ArrayList<Object>();
				boolean result = false;
				
				request.setCharacterEncoding("utf-8");
				infos.add(request.getParameter("code"));
				infos.add(request.getParameter("pname"));
				infos.add(Integer.valueOf(request.getParameter("cost")));
				infos.add(Integer.valueOf(request.getParameter("pnum")));
				infos.add(Integer.valueOf(request.getParameter("jnum")));
				infos.add(Integer.valueOf(request.getParameter("sale")));
				infos.add(request.getParameter("gcode"));
				
				result = dao.modifyProduct(infos);
				
				//modify.jsp창에서 수정 성공, 실패 메시지를 확인할수 있게 하기 위함
				request.setAttribute("mode", mode);
				request.setAttribute("result", result); 
			}else if(mode.equals("delete")) {//삭제 눌렀을떄
				boolean result = false;
				String code = request.getParameter("code");
				
				result = dao.deleteProduct(code);
				
				request.setAttribute("mode", mode);
				request.setAttribute("result", result);
			}
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("searchAndModify.jsp");
		rd.forward(request, response);
	}

}
