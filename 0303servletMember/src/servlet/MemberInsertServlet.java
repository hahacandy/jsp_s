package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;
import model.MemberVO;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/userinfo_insert")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("Member/userinfo_insert.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		MemberDAO memberDAO = new MemberDAO();
		MemberVO memberVO = new MemberVO();
		
		memberVO.setName(request.getParameter("name"));
		memberVO.setUserid(request.getParameter("userid"));
		memberVO.setPasswd(request.getParameter("passwd"));
		memberVO.setGubun(request.getParameter("gubun"));
		memberVO.setZipcode(request.getParameter("zip"));
		memberVO.setAddr1(request.getParameter("addr1"));
		memberVO.setAddr2(request.getParameter("addr2"));
		memberVO.setTel(request.getParameter("tel"));
		memberVO.setEmail(request.getParameter("email"));
		
		String[] fas = request.getParameterValues("fa");
		String fa = "";
		if(fas != null) {
			fa = fas[0];
			for(int i=1; i<fas.length; i++) {
				fa += "." + fas[i];
			}
		}
		memberVO.setFavorite(fa);
		
		memberVO.setJob(request.getParameter("job"));
		memberVO.setIntro(request.getParameter("intro"));
		
		boolean flag = memberDAO.memberInsert(memberVO);
		
		
		response.sendRedirect("userinfo_list");
	}

}
