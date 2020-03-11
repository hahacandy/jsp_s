package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jdk.nashorn.internal.runtime.Context;
import model.PdsDAO;
import model.PdsVO;

/**
 * Servlet implementation class PdsWriteServlet
 */
@WebServlet("/pds_write")
public class PdsWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PdsWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("Pds/pds_write.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		PdsDAO pdsDAO = PdsDAO.getInstance();
		
		ServletContext context = getServletContext();
		String path = context.getRealPath("/upload");
		int maxSize = 2*1024*1024;
		String encType = "UTF-8";
		
		MultipartRequest mr = new MultipartRequest(request, path, maxSize, encType, new DefaultFileRenamePolicy());
		
		String name = mr.getParameter("name");
		String pass = mr.getParameter("pass");
		String email = mr.getParameter("email");
		String subject = mr.getParameter("subject");
		String contents = mr.getParameter("contents");
		String filename = mr.getFilesystemName("filename");
		
		
		List<Object> infos = new ArrayList<Object>();
		infos.add(name);
		infos.add(pass);
		infos.add(email);
		infos.add(subject);
		infos.add(contents);
		infos.add(filename);

		boolean result = pdsDAO.writePds(infos);
		
		request.setAttribute("result", result);
		
		response.sendRedirect("pds_list");

	}

}
