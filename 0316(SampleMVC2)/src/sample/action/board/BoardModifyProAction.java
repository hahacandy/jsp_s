package sample.action.board;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sample.action.Action;
import sample.model.board.BoardDAO;

public class BoardModifyProAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idx = Integer.valueOf(request.getParameter("idx"));
		int page = Integer.valueOf(request.getParameter("page"));
		String search = null;
		String key = null;
		
		String uri = null; //성공했을때 가는 uri
		String returnUri = request.getParameter("returnUri"); //실패했을때 가는 uri
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		List<Object> infos = new ArrayList<Object>();
		
		infos.add(request.getParameter("email"));
		infos.add(request.getParameter("subject"));
		infos.add(request.getParameter("contents"));
		infos.add(idx);
		infos.add(request.getParameter("pass"));
		
		if(request.getParameter("search") != null && request.getParameter("key") != null) {
			search = request.getParameter("search");
			key = URLEncoder.encode(request.getParameter("key"), "utf-8");
			
			uri = "Board?command=board_view&idx="+idx+"&page="+page+"&search="+search+"&key="+key;

		}else {
			uri = "Board?command=board_view&idx="+idx+"&page="+page;
		}
		
		boolean result = boardDAO.modifyBoard(infos);
		
		if(result) {
			request.setAttribute("msg", "수정성공");
			request.setAttribute("uri", uri);
		}else {
			request.setAttribute("msg", "수정 실패");
			request.setAttribute("uri", returnUri);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("Alert/alert.jsp");
		rd.forward(request, response);
		
	}
}
