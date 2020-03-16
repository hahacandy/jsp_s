package sample.action.guest;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sample.action.Action;
import sample.model.guest.GuestDAO;
import sample.model.guest.GuestVO;

public class GuestModifyAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idx = Integer.valueOf(request.getParameter("idx"));
		int page = Integer.valueOf(request.getParameter("page"));
		
		GuestDAO guestDAO = GuestDAO.getInstance();
		
		GuestVO vo = guestDAO.getOneGuest(idx);
		
		request.setAttribute("idx", idx);
		request.setAttribute("page", page);
		request.setAttribute("vo", vo);
		
		RequestDispatcher rd = request.getRequestDispatcher("Guest/guest_modify.jsp");
		rd.forward(request, response);
	}
}
