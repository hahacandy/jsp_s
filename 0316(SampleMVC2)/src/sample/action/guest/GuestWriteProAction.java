package sample.action.guest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sample.action.Action;
import sample.model.guest.GuestDAO;

public class GuestWriteProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		GuestDAO guestDAO = GuestDAO.getInstance();
		
		List<Object> infos = new ArrayList<Object>();
		
		infos.add(request.getParameter("name"));
		infos.add(request.getParameter("subject"));
		infos.add(request.getParameter("contents"));
		
		guestDAO.writeGuest(infos);
		
		response.sendRedirect("/Guest?command=guest_list");
		
		
	}

}
