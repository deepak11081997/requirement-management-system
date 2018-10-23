package new12;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserLoginCheck
 */
@WebServlet("/UserLoginCheck")
public class UserLoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String orgPass="";
		DbCheck d=new DbCheck();
		try {
			orgPass=d.check(username);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(username.isEmpty() || password.isEmpty() )
		{
			RequestDispatcher req = request.getRequestDispatcher("Home.jsp");
			req.include(request, response);
		}
		else if(password.equals(orgPass))
		{
			HttpSession session=request.getSession();  
	        session.setAttribute("username",username); 
	        try {
				session.setAttribute("uno",d.GetUno(username));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        String details[]=new String[6];
	        try {
				 details=d.GetDetails(username);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        session.setAttribute("role",details[0]);
	        session.setAttribute("fname",details[1]);
	        session.setAttribute("lname",details[2]);
	        session.setAttribute("eid",details[3]);
	        session.setAttribute("floor",details[4]);
	        session.setAttribute("sno",details[5]);
	        response.sendRedirect("BaseHome.jsp");
	        
		}
		else{
			RequestDispatcher req = request.getRequestDispatcher("Home.jsp");
			req.include(request, response);
		}
	}

}
