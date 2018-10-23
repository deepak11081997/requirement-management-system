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

import org.json.*;

/**
 * Servlet implementation class AdminSearch
 */
@WebServlet("/AdminSearch")
public class AdminSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSearch() {
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
		String sort=request.getParameter("sort");
		String page=request.getParameter("page");
		int s=Integer.parseInt(page);
		s=(s-1)*100;
		JSONObject obj=new JSONObject();
		JSONObject obj1=new JSONObject();
		JSONObject obj2=new JSONObject();
		
		System.out.println(me+" "+toallocate+" "+toserve);
		
		DbCheck d=new DbCheck();
		
		ArrayList<String> al = new ArrayList<String>();
		ArrayList<String> al1 = new ArrayList<String>();
		try {
			al1=d.findserver(name);
		} catch (ClassNotFoundException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		} catch (SQLException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
  		try {
			al=d.AdminNotAllocateSpecific(Uid,name,search,me,toallocate,toserve,s,sort);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		JSONArray array=new JSONArray();
  		JSONObject user = null;
  		JSONArray array1=new JSONArray();
  		JSONObject user1 = null;
  		JSONArray array2=new JSONArray();
  		JSONObject user2 = null;
  		
  		if(al1.size()>0)
  		{
  			int i=0;
  			while(al1.size()>i){
  				user1=new JSONObject();
  				try {
					user1.put("server",al1.get(i));
				} catch (JSONException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}i++;
  				
  				array1.put(user1);
  				}
  				try {
					obj1.put("user1",array1);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
  				
  
  		}
  		else{
  			user1=new JSONObject();
  			try {
				user1.put("server","nothing present");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  			array1.put(user1);
  			try {
				obj1.put("user1",array1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  			
  		}
	
  		
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
					try {
						user.put("Uid",d.finduser(al.get(i)));
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (JSONException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
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
					user.put("Status",al.get(i));
				} catch (JSONException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}i++;
				try {
					user.put("AllocatedTo",al.get(i));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}i++;
				try {
					user.put("AllocatedBy",al.get(i));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}i++;
  				try {
					user.put("Rating",al.get(i));
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}i++;
  				array.put(user);
  				}
  				try {
					obj.put("user",array);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
  				
  
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
  			
				
  		}
  		array2.put(obj);
  		array2.put(obj1);
  		try {
			obj2.put("user2",array2);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		response.setContentType("application/json");
  		response.getWriter().write(obj2.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
