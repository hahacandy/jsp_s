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

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/Search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = null;
	 	MemberVO vo = null;
	 	int custno = 0;
	 	if(request.getParameter("custno") != null){
	 		custno = Integer.valueOf(request.getParameter("custno"));
	 		vo = MemberDAO.getInstance().getMember(custno);
	 		if(vo == null){
	 			request.setAttribute("msg", "검색 결과가 없습니다.");
	 			request.setAttribute("uri", "Search");
	 			rd = request.getRequestDispatcher("alert.jsp");
	 		}else {
	 			request.setAttribute("custno", custno);
	 			String joindate = vo.getJoindate();
	 			joindate = joindate.substring(0,10).replace("-", "");
	 			vo.setJoindate(joindate);
	 			request.setAttribute("vo", vo);
	 		}
	 	}
	 	
	 	if(rd == null)
	 		rd = request.getRequestDispatcher("search.jsp");
	 	
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		List<Object> infos = new ArrayList<Object>();
		
		infos.add(request.getParameter("name"));
		infos.add(request.getParameter("tel"));
		infos.add(request.getParameter("addr"));
		infos.add(request.getParameter("date"));
		String grade = request.getParameter("grade");
		if(grade.equalsIgnoreCase("a") || grade.equalsIgnoreCase("vip"))
			grade = "A";
		else if(grade.equalsIgnoreCase("b") || grade.equalsIgnoreCase("일반"))
			grade = "B";
		else if(grade.equalsIgnoreCase("c") || grade.equalsIgnoreCase("직원"))
			grade = "C";
		infos.add(grade);
		infos.add(request.getParameter("code"));
		infos.add(Integer.valueOf(request.getParameter("bun")));
		
		boolean result = MemberDAO.getInstance().modifyMember(infos);
		
		if(result) {
			request.setAttribute("msg", "회원 수정 완료");
			
		}else {
			request.setAttribute("msg", "회원 수정 실패");
		}
		request.setAttribute("uri", "Search?custno="+request.getParameter("bun"));
		RequestDispatcher rd = request.getRequestDispatcher("alert.jsp");
		rd.forward(request, response);
	}

}
