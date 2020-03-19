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
		
		int nextCustNo = MemberDAO.getInstance().getNextCustNo();
		
		request.setAttribute("nextCustNo", nextCustNo);
		
		RequestDispatcher rd = request.getRequestDispatcher("insert.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		List<Object> infos = new ArrayList<Object>();
		
		infos.add(Integer.valueOf(request.getParameter("bun")));
		infos.add(request.getParameter("name"));
		infos.add(request.getParameter("tel"));
		infos.add(request.getParameter("addr"));
		infos.add(request.getParameter("date"));
		infos.add(request.getParameter("grade"));
		infos.add(request.getParameter("code"));

		boolean result = MemberDAO.getInstance().insertMember(infos);
		
		if(result) {
			request.setAttribute("msg", "회원 등록 성공");
			request.setAttribute("uri", "List");
		}else {
			request.setAttribute("msg", "회원 등록 실패");
			request.setAttribute("uri", "back()");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("alert.jsp");
		rd.forward(request, response);
		
	}

}
