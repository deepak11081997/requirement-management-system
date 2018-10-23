package new12;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class SuperSearch
 */
@WebServlet("/SuperSearch")
public class SuperSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuperSearch() {
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
		String sort=request.getParameter("sort");
		String toverify=request.getParameter("check2");
		HttpSession session=request.getSession(); 
		String Uid=(String)session.getAttribute("uno");
		String name=(String)session.getAttribute("username");
		String page=request.getParameter("page");
		int s=Integer.parseInt(page);
		s=(s-1)*100;
		
		JSONObject obj=new JSONObject();
		
		DbCheck d=new DbCheck();
		
		ArrayList<String> al = new ArrayList<String>();
  		try {
			al=d.choosespecific(Uid,search,me,toverify,s,sort);  
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		JSONArray array=new JSONArray();
  		JSONObject user = null;
  		//System.out.println(al.size());
  		if(al.size()>0)
  		{
  			int i=0;
  			while(al.size()>i){
  				user=new JSONObject();
  				try {if(i==0){
					user.put("Me",name);}
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
  				try {
  					user.put("id",al.get(i));
				} catch (JSONException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}i++;
				try {
					user.put("Uid",d.finduser(al.get(i)));
				} catch (JSONException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}i++;
  				try {
					user.put("Item",al.get(i));
				} catch (JSONException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}i++;
  				try {
					user.put("itemreason",al.get(i));
				} catch (JSONException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}i++;
				try {
					user.put("verified",al.get(i));
				} catch (JSONException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}i++;
  				try {
					user.put("Status",al.get(i));
				} catch (JSONException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}i++;
  				try {
					user.put("Rating",al.get(i));
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}i++;
  				try {
					user.put("allocate",al.get(i));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}i++;
				try {
					user.put("allocatedby",al.get(i));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}i++;
				try {
					user.put("allocatedto",al.get(i));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}i++;
  				array.put(user);
  				}
  				try {
					obj.put("user",array);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
  				try {
					System.out.println(user.get("itemreason"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
  				response.setContentType("application/json");
  				response.getWriter().write(obj.toString());
  
  		}
  		else{
  			user=new JSONObject();
  			try {
				user.put("Item","nothing present");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  			array.put(user);
  			try {
				obj.put("user",array);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  			response.setContentType("application/json");
				response.getWriter().write(obj.toString());
  		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
