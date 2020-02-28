package board.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;

/**
 * Servlet implementation class BoardModifyServlet
 */
@WebServlet("/board_modify")
public class BoardModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idx = Integer.valueOf(request.getParameter("idx"));
		
		BoardDAO boardDAO = new BoardDAO();
		
		BoardVO vo = boardDAO.getOneBoardPost(idx);
		
		request.setAttribute("vo", vo);
		request.setAttribute("idx", idx);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("Board/board_modify.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		BoardVO vo = new BoardVO();
		
		int idx = Integer.valueOf(request.getParameter("idx"));
		
		vo.setIdx(idx);
		
		vo.setEmail(request.getParameter("email"));
		vo.setSubject(request.getParameter("subject"));
		vo.setContents(request.getParameter("contents"));
		vo.setPass(request.getParameter("pass"));
		
		BoardDAO boardDAO = new BoardDAO();
		
		int row = boardDAO.modifyPost(vo);
		
		request.setAttribute("row", row);
		request.setAttribute("idx", idx);
		
		RequestDispatcher rd = request.getRequestDispatcher("Board/board_modify_pro.jsp");
		rd.forward(request, response);
		
		
	}

}
