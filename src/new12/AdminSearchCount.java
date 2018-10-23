package new12;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class AdminSearchCount
 */
@WebServlet("/AdminSearchCount")
public class AdminSearchCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSearchCount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String search=request.getParameter("key");
		String me=request.getParameter("check1");
		String toallocate=request.getParameter("check2");
		String toserve=request.getParameter("check3");
		HttpSession session=request.getSession(); 
		String Uid=(String)session.getAttribute("uno");
		String name=(String)session.getAttribute("username");
		JSONObject obj=new JSONObject();
		
		DbCheck d=new DbCheck();
		
		String s=null;
		
		try {
			s=d.AdminNotAllocateSpecificcount(Uid,name,search,me,toallocate,toserve);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			obj.put("user",s);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setContentType("application/json");
			response.getWriter().write(obj.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}