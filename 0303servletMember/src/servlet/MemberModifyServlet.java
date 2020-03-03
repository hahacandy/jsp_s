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
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberVO;

/**
 * Servlet implementation class MemberModifyServlet
 */
@WebServlet("/userinfo_modify")
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
		
		MemberDAO dao = new MemberDAO();
		
		HttpSession session = request.getSession();
		
		MemberVO vo = dao.getSearchUserid((String)session.getAttribute("userid"));
		
		String[] gubun = new String[2];
		if(vo.getGubun().equals("1")) 
			gubun[0] = "checked";
		else
			gubun[1] = "checked";
		
		String[] fa = new String[11];
		if(vo.getFavorite().contains("건강"))
			fa[0] = "checked";
		if(vo.getFavorite().contains("문화예술"))
			fa[1] = "checked";
		if(vo.getFavorite().contains("경제"))
			fa[2] = "checked";
		if(vo.getFavorite().contains("연예오락"))
			fa[3] = "checked";
		if(vo.getFavorite().contains("뉴스"))
			fa[4] = "checked";
		if(vo.getFavorite().contains("여행레져"))
			fa[5] = "checked";
		if(vo.getFavorite().contains("생활"))
			fa[6] = "checked";
		if(vo.getFavorite().contains("스포츠"))
			fa[7] = "checked";
		if(vo.getFavorite().contains("교육"))
			fa[8] = "checked";
		if(vo.getFavorite().contains("컴퓨터"))
			fa[9] = "checked";
		if(vo.getFavorite().contains("학문"))
			fa[10] = "checked";
		
		String[] job = new String[12];
		for(int i=0; i<job.length; i++) {
			if(vo.getJob().equals(String.valueOf(i)))
				job[i] = "selected";
		}

		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", gubun);
		request.setAttribute("fa", fa);
		request.setAttribute("job", job);
		
		RequestDispatcher rd = request.getRequestDispatcher("Member/userinfo_modify.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		MemberDAO memberDAO = new MemberDAO();
		List<Object> infos = new ArrayList<Object>();
		
		infos.add(request.getParameter("passwd"));
		infos.add(request.getParameter("gubun"));
		infos.add(request.getParameter("zip"));
		infos.add(request.getParameter("addr1"));
		infos.add(request.getParameter("addr2"));
		infos.add(request.getParameter("tel"));
		infos.add(request.getParameter("email"));
		
		String[] fas = request.getParameterValues("fa");
		String fa = "";
		if(fas != null) {
			fa = fas[0];
			for(int i=1; i<fas.length; i++) {
				fa += "." + fas[i];
			}
		}
		infos.add(fa);
		
		infos.add(request.getParameter("job"));
		infos.add(request.getParameter("intro"));
		
		HttpSession session = request.getSession();
		
		infos.add((String)session.getAttribute("userid"));
		
		int row = memberDAO.modifyMember(infos);
		
		response.sendRedirect("userinfo_list");
	}

}
