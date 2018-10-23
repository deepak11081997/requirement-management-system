package new12;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new12.DbCheck;

/**
 * Servlet implementation class RequireRegister
 */
@WebServlet("/RequireRegister")
public class RequireRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequireRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String chair = request.getParameter("req");
		String ChairReason = request.getParameter("reqreason");
		
		
		DbCheck d=new DbCheck();
		
		HttpSession session=request.getSession();
		String uid=(String) session.getAttribute("uno");
		String name=(String) session.getAttribute("username");
		if(chair!=null&&ChairReason.length()>0)
		{
			try {
				d.AddItem(uid,name,chair,ChairReason);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		response.sendRedirect("BaseHome.jsp");
	}

}
