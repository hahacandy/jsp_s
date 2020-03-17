package sample.action.guest;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sample.action.Action;
import sample.model.guest.GuestDAO;
import sample.model.guest.GuestVO;
import sample.util.ThubanCookie;

public class GuestViewAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
		int idx = Integer.valueOf(request.getParameter("idx"));
		int page = Integer.valueOf(request.getParameter("page"));
		
		GuestDAO dao = GuestDAO.getInstance();
		
		//조회수 증가
		if(!ThubanCookie.isCookieSet(request, response, "readcnt", "G"+idx)) {
			dao.increaseReadcnt(idx);
		}
		

		GuestVO vo = dao.getOneGuest(idx);
		
		request.setAttribute("vo", vo);
		request.setAttribute("idx", idx);
		request.setAttribute("page", page);
		
		RequestDispatcher rd = request.getRequestDispatcher("Guest/guest_view.jsp");
		rd.forward(request, response);
	}
}
