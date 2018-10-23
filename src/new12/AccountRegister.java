package new12;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new12.DbCheck;

/**
 * Servlet implementation class AccountRegister
 */
@WebServlet("/AccountRegister")
public class AccountRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("email");
		String pass = request.getParameter("pass");
		String cpass = request.getParameter("cpass");
		String role = request.getParameter("role");
		
		if(pass.equals(cpass)&&  username.length()>0 && pass.length()>0&& role.length()>0){
			DbCheck d=new DbCheck();
			try {
				boolean t=d.fix(username,pass,role);
				if(t){
					response.sendRedirect("AlterUserPermission.jsp");
					
				}
				else{
					RequestDispatcher req = request.getRequestDispatcher("AlterUserPermission.jsp");
					req.include(request, response);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			RequestDispatcher req = request.getRequestDispatcher("AlterUserPermission.jsp");
			req.include(request, response);
		}
	}

}
