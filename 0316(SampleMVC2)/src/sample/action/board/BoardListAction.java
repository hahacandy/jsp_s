package sample.action.board;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sample.action.Action;
import sample.model.board.BoardDAO;
import sample.model.board.BoardVO;
import sample.util.ThubanAutoPageButton;

public class BoardListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("page") == null) {
			response.sendRedirect("/Board?command=board_list&page=1");
			return;
		}
		
		String search = null;
		String key = null;
		String key2 = null; //검색을 한국어로 햇다면, 페이지 이동시 오류 안나게 하기 위함
		if(request.getParameter("search") != null && request.getParameter("key") != null) {
			search = request.getParameter("search");
			key = request.getParameter("key");
			key2 = URLEncoder.encode(request.getParameter("key"), "utf-8"); 
		}
		
		BoardDAO boardDAO = BoardDAO.getInstance();
		
		int page = Integer.valueOf(request.getParameter("page"));
		int allCnt = 0;
		ThubanAutoPageButton apb = null;
		List<BoardVO> list = null;
		String pageButton = null;
		
		//검색아닐떄
		if(search == null) {
			allCnt = boardDAO.getAllBoardCnt();
			apb = new ThubanAutoPageButton("/Board?command=board_list", page, allCnt, 10, 10, null);
			list = boardDAO.getAllBoard(apb.getStartPost(), apb.getEndPost());
			pageButton = apb.getPageButton();
		}else { //검색일때
			allCnt = boardDAO.getSearchBoardCnt(search, key);
			String tag = "&search="+search+"&key="+key2;
			apb = new ThubanAutoPageButton("/Board?command=board_list", page, allCnt, 10, 10, tag);
			list = boardDAO.getSearchBoard(search, key, apb.getStartPost(), apb.getEndPost());
			pageButton = apb.getPageButton();
		}
		
		request.setAttribute("list", list);
		request.setAttribute("allPage", apb.getAllPage());
		request.setAttribute("page", page);
		request.setAttribute("allCnt", allCnt);
		request.setAttribute("currentCnt", apb.getCurrentPostCnt());
		request.setAttribute("pageButton", pageButton);
		request.setAttribute("search", search);
		request.setAttribute("key", key);
	
		
		RequestDispatcher rd = request.getRequestDispatcher("Board/board_list.jsp");
		rd.forward(request, response);
	}
	
}
