package sample.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sample.action.Action;
import sample.model.board.BoardDAO;
import sample.model.board.BoardVO;

public class BoardModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int idx = Integer.valueOf(request.getParameter("idx"));
		int page = Integer.valueOf(request.getParameter("page"));
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		BoardVO vo = boardDAO.getOneBoardForModify(idx);
		
		if(request.getParameter("search") != null && request.getParameter("key") != null) {
			request.setAttribute("search", request.getParameter("search"));
			request.setAttribute("key", request.getParameter("key"));
		}
		
		request.setAttribute("vo", vo);
		request.setAttribute("idx", idx);
		request.setAttribute("page", page);
		

		RequestDispatcher rd = request.getRequestDispatcher("Board/board_modify.jsp");
		rd.forward(request, response);
	}

}
