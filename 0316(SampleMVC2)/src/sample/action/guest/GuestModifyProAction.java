package sample.action.guest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sample.action.Action;
import sample.model.guest.GuestDAO;

public class GuestModifyProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("진입");
		
		int idx = Integer.valueOf(request.getParameter("idx"));
		int page = Integer.valueOf(request.getParameter("page"));
		
		GuestDAO guestDAO = GuestDAO.getInstance();
		
		List<Object> infos = new ArrayList<Object>();
		infos.add(request.getParameter("subject"));
		infos.add(request.getParameter("contents"));
		infos.add(idx);
		
		boolean result = guestDAO.modifyGuest(infos);
		
		response.sendRedirect("/Guest?command=guest_view&idx="+idx+"&page="+page);
		
	}

}
