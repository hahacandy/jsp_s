package sample.action.board;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sample.action.Action;
import sample.model.board.BoardDAO;
import sample.model.board.BoardVO;
import sample.util.SqlMark;
import sample.util.ThubanCookie;

public class BoardViewAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idx = Integer.valueOf(request.getParameter("idx"));
		int page = Integer.valueOf(request.getParameter("page"));
		String search = null;
		String key = null;
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		
		//같은 게시물하루에 한번씩만 조회수1증가
		if(!ThubanCookie.isCookieSet(request, response, "readcnt", "B"+idx))
			boardDAO.increaseReadcnt(idx);
		
		BoardVO vo = boardDAO.getOneBoardForView(idx);
		
		vo.setContents(SqlMark.lineBreak(vo.getContents()));
		
		
		
		if(request.getParameter("search") != null && request.getParameter("key") != null) {
			search = request.getParameter("search");
			key = URLEncoder.encode(request.getParameter("key"), "utf-8") ;

			
			request.setAttribute("search", search);
			request.setAttribute("key", key);
		}
		
		request.setAttribute("idx", idx);
		request.setAttribute("page", page);
		request.setAttribute("vo", vo);
		
		RequestDispatcher rd = request.getRequestDispatcher("Board/board_view.jsp");
		rd.forward(request, response);
	}
}
