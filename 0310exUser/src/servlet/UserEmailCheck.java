package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ThubanMail;
import util.ThubanPasswd;

/**
 * Servlet implementation class UserEmailCheck
 */
@WebServlet("/user_email_check")
public class UserEmailCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEmailCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = ThubanPasswd.createTempPasswd(6); //6자리의 랜덤코드 생성
		String email = request.getParameter("email");
		
		//System.out.println(code);
		//System.out.print(email);
		
		if(util.ThubanMail.naverSend(네이버아이디, 네이버비밀번호, email, "인증번호발송", code + " 입니다."))
			System.out.println("이메일 보내기 성공");
		else
			System.out.println("이메일 보내기 실패");
	
		request.setAttribute("code", code);
		
		RequestDispatcher rd = request.getRequestDispatcher("User/user_emailcheck.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
