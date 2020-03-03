package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;
import model.MemberVO;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/userinfo_list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String[] selected = new String[2];
		String order = null;
		if(request.getParameter("order") == null) {
			order = "name";
		}else {
			order = request.getParameter("order");
		}
		
		if(order.equals("name"))
			selected[0] = "selected";
		else if(order.equals("addr1"))
			selected[1] = "selected";
		
		MemberDAO memberDAO = new MemberDAO();
		List<MemberVO> list = null;
		if(request.getParameter("search") == null) {//검색안했을때의 전체 멤버 정보
			list = memberDAO.getAllMember(order); 
		}else {//검색의 사용하였을대의 멤버 정보
			list = memberDAO.getSearchMember(request.getParameter("search"), order);
		}
		
		
		request.setAttribute("list", list);
		request.setAttribute("selected", selected);
		if(request.getParameter("search") != null)
			request.setAttribute("search", request.getParameter("search"));
		
		RequestDispatcher rd = request.getRequestDispatcher("Member/userinfo_list.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
