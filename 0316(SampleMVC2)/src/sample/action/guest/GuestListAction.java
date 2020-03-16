package sample.action.guest;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sample.action.Action;
import sample.model.guest.GuestDAO;
import sample.model.guest.GuestVO;
import sample.util.ThubanAutoPageButton;

public class GuestListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//페이지 초기값 설정
		if(request.getParameter("page") == null) {
			response.sendRedirect("/Guest?command=guest_list&page=1");
			return;
		}
		
		int currentPage = Integer.valueOf(request.getParameter("page"));
		GuestDAO guestDAO = GuestDAO.getInstance();
		
		int allCnt = guestDAO.getAllGuestCNT();
		
		ThubanAutoPageButton apb = new ThubanAutoPageButton
				("http://localhost:8090/Guest?command=guest_list", currentPage, allCnt, 10, 10, null);
		
		List<GuestVO> list = guestDAO.getAllGuest(apb.getStartPost(), apb.getEndPost());
		

		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("allCnt", allCnt);
		request.setAttribute("allPage", apb.getAllPage());
		request.setAttribute("list", list);
		request.setAttribute("cnt", apb.getCurrentPostCnt());
		request.setAttribute("pageButton", apb.getPageButton());
		
		RequestDispatcher rd = request.getRequestDispatcher("Guest/guest_list.jsp");
		rd.forward(request, response);
	}

}
