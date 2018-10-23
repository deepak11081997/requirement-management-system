package new12;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DbCheck d=new DbCheck();
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String pass = request.getParameter("pass");
		String cpass = request.getParameter("cpass");
		String empno = request.getParameter("eno");
		String floor = request.getParameter("floor");
		String seatno = request.getParameter("sloc");
		HttpSession session=request.getSession(); 
		String s=(String) session.getAttribute("uno");
		int uno=Integer.parseInt(s);
		
		if(fname.length()>0 || lname.length()>0 || (pass.length()>0&& cpass.length()>0) || empno.length()>0 || floor.length()>0 || seatno.length()>0){
			try {
				d.updateinfo(uno,fname,lname,pass,cpass,empno,floor,seatno);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(fname.length()>0){
				 session.setAttribute("fname", fname);
				}
			 if(lname.length()>0){
				 session.setAttribute("lname", lname);
		            
				}
			
			 if(empno.length()>0){
				 session.setAttribute("eid", empno);
				}
			 if(floor.length()>0){
				 session.setAttribute("floor", floor);
				}
			 if(seatno.length()>0){
				 session.setAttribute("sno", seatno);
				}
			
		}
		response.sendRedirect("Profile.jsp");
	}

}
