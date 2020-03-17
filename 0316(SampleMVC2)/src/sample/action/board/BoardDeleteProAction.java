package sample.action.board;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sample.action.Action;
import sample.model.board.BoardDAO;

public class BoardDeleteProAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idx = Integer.valueOf(request.getParameter("idx"));
		String pass = request.getParameter("pass");
		int page = Integer.valueOf(request.getParameter("page"));
		
		String uri = null;
		
		
		if(request.getParameter("search") != null && request.getParameter("key") != null) {
			uri = "/Board?command=board_list&page="+page+"&search="+request.getParameter("search")+"&key="+URLEncoder.encode(request.getParameter("key"), "utf-8") ;
		}else {
			uri = "/Board?command=board_list&page="+page;
		}

		BoardDAO boardDAO = BoardDAO.getInstance();
		
		boolean result = boardDAO.deleteBoard(idx, pass);
		
		if(result) {
			System.out.println("삭제 완료");
			request.setAttribute("msg", "삭제 완료");
			request.setAttribute("puri", uri); //부모 페이지를 이동시키고, 자신의 팝업을 닫음
		}else {
			System.out.println("삭제 실패");
			request.setAttribute("msg", "삭제 실패");
			uri = request.getParameter("returnUri");
			request.setAttribute("uri", uri);
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/Alert/alert.jsp");
		rd.forward(request, response);
		

	}
}
