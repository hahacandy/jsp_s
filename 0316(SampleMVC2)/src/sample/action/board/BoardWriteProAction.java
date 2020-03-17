package sample.action.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sample.action.Action;
import sample.model.board.BoardDAO;

public class BoardWriteProAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardDAO boardDAO = BoardDAO.getInstance();
		
		List<Object> infos = new ArrayList<Object>();
		
		infos.add(request.getParameter("name"));
		infos.add(request.getParameter("email"));
		infos.add(request.getParameter("subject"));
		infos.add(request.getParameter("contents"));
		infos.add(request.getParameter("pass"));
		infos.add(request.getRemoteAddr());
		
		boolean result = boardDAO.writeBoard(infos);
		if(result) {
			request.setAttribute("msg", "게시물 등록 성공");
			request.setAttribute("uri", "/Board?command=board_list");
		}else {
			request.setAttribute("msg", "게시물 등록 실패");
			request.setAttribute("uri", "/Board?command=board_write");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/Alert/alert.jsp");
		rd.forward(request, response);
	
	}
}
