package servlet.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemberDAO;
import model.member.MemberVO;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/MemberModifyServlet")
public class MemberModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null)
			request.setAttribute("user", session.getAttribute("user"));
		
		RequestDispatcher rd = request.getRequestDispatcher("Member/userinfo_modify.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		MemberDAO dao = new MemberDAO();
		List<Object> infos = new ArrayList<Object>();
		int result = 0;
		
		request.setCharacterEncoding("utf-8");
		
		infos.add(request.getParameter("tel"));
		infos.add(request.getParameter("email"));
		
		//회원 정보 수정에서, 비번없이 걍 정보만 수정할떄
		if(request.getParameter("passwd").length() == 0) {
			result = dao.modifyMember(infos);
			
		}else { //비번도 같이 수정할때
			infos.add(request.getParameter("passwd"));
			result = dao.modifyMemberP(infos);
		}
		
		//회원정보 수정 성공시, 세션에 담긴 회원정보 새로고침
		if(result > 0) {
			HttpSession session = request.getSession();
			MemberVO user = (MemberVO)session.getAttribute("user");
			session.setAttribute("user", dao.getLoginUserInfo(user.getUserid()));
		}
		
		request.setAttribute("result", result);
		
		RequestDispatcher rd = request.getRequestDispatcher("Member/userinfo_modify_pro.jsp");
		rd.forward(request, response);

	}

}
