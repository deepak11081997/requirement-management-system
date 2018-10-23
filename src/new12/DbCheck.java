package new12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DbCheck {
	
	String dbURL = "jdbc:mysql://localhost:3306/alternatedatabase";
	String username = "root";
	String password = "";
	Connection con;
	
	public String check(String name) throws ClassNotFoundException, SQLException
	{
		String result="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 String sql = "SELECT password FROM userdetails where username='"+name+"'"; 
			 Statement statement = con.createStatement();
			 ResultSet r = statement.executeQuery(sql);
			 if(r.next())
			 result=r.getString(1);
			 
		 }
           else
               System.out.println("Not Connected");
		 con.close();
		 return result;	 
	}
	public String finduser(String uid) throws ClassNotFoundException, SQLException
	{
		String result="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 String sql = "SELECT username FROM userdetails where id='"+uid+"'"; 
			 Statement statement = con.createStatement();
			 ResultSet r = statement.executeQuery(sql);
			 if(r.next())
			 result=r.getString(1);
			 
		 }
           else
               System.out.println("Not Connected");
		 con.close();
		 return result;	 
	}
	public String GetUno(String name)throws ClassNotFoundException, SQLException {
		
		
		String result="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 String sql = "SELECT id FROM UserDetails where username='"+name+"'"; 
			 Statement statement = con.createStatement();
			 ResultSet r = statement.executeQuery(sql);
			 if(r.next())
			 {
			 int i=r.getInt(1);
			 result=Integer.toString(i);
			 }
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
		 return result;
	}
	public String[] GetDetails(String name)throws ClassNotFoundException, SQLException {
		
		
		String[] result=new String[6];
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 String sql = "SELECT role,fname,lname,eid,floor,sno FROM UserDetails where username='"+name+"'"; 
			 Statement statement = con.createStatement();
			 ResultSet r = statement.executeQuery(sql);
			 int i=0;
			 if(r.next())
			 {
				 result[i++]=r.getString(1);
				 result[i++]=r.getString(2);
				 result[i++]=r.getString(3);
				 result[i++]=r.getString(4);
				 result[i++]=r.getString(5);
				 result[i++]=r.getString(6);
			
			 }
		 }
           else           
               System.out.println("Not Connected");
		 con.close();
		 return result;
	}
	public void AddItem(String uid,String name, String item, String footReason) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 name=name+" ";
			 Statement statement = con.createStatement();
			 String sql =  "insert into ItemDetails values("+null+",'" +uid+ "', '" +item+ 
                    "', '" +footReason+"', '" +"Yet to verify"+"', '" +"Yet to verify"+"', '" +"Yet to verify"+"', '" +"Yet to verify"+"', '" +"Yet to verify"+"', '" +"not verified"+"','" +name+ "')"; 
			 statement.executeUpdate(sql);
			 con.close();
			 
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
	}
	public void addreq(String user, String item) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 Statement statement = con.createStatement();
			 String sql =  "insert into reqDetails values("+null+",'" +item+ "','True', '" +user+ "')"; 
			 statement.executeUpdate(sql);
			 con.close();
			 
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
	}
	public ArrayList<String> find(String Uid)throws ClassNotFoundException, SQLException {
		
		ArrayList<String> a = new ArrayList<String>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 String sql = "SELECT Item,itemreason,Status,Rating,id FROM ItemDetails where Uid='"+Uid+"'"; 
			 Statement statement = con.createStatement();
			 ResultSet r = statement.executeQuery(sql);
			 while(r.next())
			 {
				 a.add(r.getString(1));
				 a.add(r.getString(2));
				 a.add(r.getString(3));
				 a.add(r.getString(4));
				 a.add(r.getString(5));
			 }
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
		 return a;
	}
public ArrayList<String> findspecific(String Uid,String name,String torate,int s,String sort)throws ClassNotFoundException, SQLException {
		
		ArrayList<String> a = new ArrayList<String>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 String sql=null;
		 if (con != null)             
		 {
			 if(torate.equals("notchecked")){
			 sql = "SELECT Item,itemreason,Status,Rating,id FROM ItemDetails where Uid='"+Uid+"' and Item like '"+name+"%'"; 
			 }
			 else{
				 sql = "SELECT Item,itemreason,Status,Rating,id FROM ItemDetails where Uid='"+Uid+"' and Item like '"+name+"%' and Rating='Not yet rated' and Status='Completed'";
			 }
			 if(sort.equals("1"))
				 sql+=" order by id";
			 if(sort.equals("2"))
				 sql+=" order by Item";
			 if(sort.equals("3"))
				 sql+=" order by Status";
			 if(sort.equals("4"))
				 sql+=" order by Rating";
			 sql+=" limit "+s+",100";
			Statement statement = con.createStatement();
			 ResultSet r = statement.executeQuery(sql);
			 while(r.next())
			 {
				 a.add(r.getString(1));
				 a.add(r.getString(2));
				 a.add(r.getString(3));
				 a.add(r.getString(4));
				 a.add(r.getString(5));
			 }
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
		 return a;
	}
public String findspecificcount(String Uid,String name,String torate)throws ClassNotFoundException, SQLException {
	
	String a = null ;
	Class.forName("com.mysql.cj.jdbc.Driver");
	 con = DriverManager.getConnection(dbURL, username, password);
	 String sql=null;
	 if (con != null)             
	 {
		 if(torate.equals("notchecked")){
		 sql = "SELECT count(id) FROM ItemDetails where Uid='"+Uid+"' and Item like '"+name+"%'"; 
		 }
		 else{
			 sql = "SELECT count(id) FROM ItemDetails where Uid='"+Uid+"' and Item like '"+name+"%' and Rating='Not yet rated' and Status='Completed'";
		 }
			 Statement statement = con.createStatement();
		 ResultSet r = statement.executeQuery(sql);
		if(r.next())
			a=r.getString(1);
	 }
        else           
            System.out.println("Not Connected");
	 con.close();
	 return a;
}
	public ArrayList<String> finditems()throws ClassNotFoundException, SQLException {
		
		ArrayList<String> a = new ArrayList<String>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 String sql = "SELECT name FROM reqDetails where status='True'"; 
			 Statement statement = con.createStatement();
			 ResultSet r = statement.executeQuery(sql);
			 while(r.next())
			 {
				 a.add(r.getString(1));
			 }
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
		 return a;
	}
	public void rate(String Rid,String rating) throws ClassNotFoundException, SQLException
	{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 Statement stmt = con.createStatement();
	         
	            // Updating database
	            String q1 = "UPDATE ItemDetails set rating = '" + rating + 
	                    "' WHERE id = '" +Rid+ "'";
	            stmt.executeUpdate(q1);
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
		 
	
		 
	}
	public void updateinfo(int uno,String fname,String lname,String pass,String cpass,String empno,String floor,String seatno) throws SQLException, ClassNotFoundException{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 Statement stmt = con.createStatement();

			 
	         
	            // Updating database
			 if(fname.length()>0){
				 String q1 = "UPDATE userDetails set fname = '" + fname + 
		                    "' WHERE id = '" +uno+ "'";
		            stmt.executeUpdate(q1);
				}
			 if(lname.length()>0){
				 String q1 = "UPDATE userDetails set lname = '" + lname + 
		                    "' WHERE id = '" +uno+ "'";
		            stmt.executeUpdate(q1);
		            
				}
			 if(cpass.length()>0 && pass.length()>0 && pass.equals(cpass)){
				 String q1 = "UPDATE userDetails set password = '" + pass + 
		                    "' WHERE id = '" +uno+ "'";
		            stmt.executeUpdate(q1);
				}
			 if(empno.length()>0){
				 String q1 = "UPDATE userDetails set eid = '" + empno + 
		                    "' WHERE id = '" +uno+ "'";
		            stmt.executeUpdate(q1);
				}
			 if(floor.length()>0){
				 String q1 = "UPDATE userDetails set floor = '" + floor + 
		                    "' WHERE id = '" +uno+ "'";
		            stmt.executeUpdate(q1);
				}
			 if(seatno.length()>0){
				 String q1 = "UPDATE userDetails set sno = '" + seatno + 
		                    "' WHERE id = '" +uno+ "'";
		            stmt.executeUpdate(q1);
				}
		 }
           else           
               System.out.println("Not Connected");
		 con.close();
		
	}
	public ArrayList<String> AdminNotAllocate(String Uid,String name)throws ClassNotFoundException, SQLException {
		
		ArrayList<String> a = new ArrayList<String>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 String sql = "SELECT id,Uid,Item,ItemReason,Status,AllocatedTo,AllocatedBy,rating FROM ItemDetails where (verified='verified' and Uid!='"+Uid+"') or Uid='"+Uid+"' or AllocatedTo='"+name+"'"; 
			 Statement statement = con.createStatement();
			 ResultSet r = statement.executeQuery(sql);
			 while(r.next())
			 {
				 a.add(r.getString(1));
				 a.add(r.getString(2));
				 a.add(r.getString(3));
				 a.add(r.getString(4));
				 a.add(r.getString(5));
				 a.add(r.getString(6));
				 a.add(r.getString(7));
				 a.add(r.getString(8));
			 }
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
		 return a;
	}
public ArrayList<String> AdminNotAllocateSpecific(String Uid,String name,String search,String me,String toallocate,String toserve,int s,String sort)throws ClassNotFoundException, SQLException {
		
		ArrayList<String> a = new ArrayList<String>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 String sql=null;
		 if (con != null)             
		 {
			 
			    sql = "SELECT id,Uid,Item,ItemReason,Status,AllocatedTo,AllocatedBy,rating FROM ItemDetails where ((verified='verified' and Uid!='"+Uid+"') or Uid='"+Uid+"' or AllocatedTo='"+name+"') and (Item like '"+search+"%' or uname like '"+search+"%')"; 
			 
			    if(me.equals("checked"))
			    	sql+="and Uid='"+Uid+"'";
			    if(toallocate.equals("checked"))
			    	sql+="and AllocatedTo='Not allocated'";
			    if(toserve.equals("checked"))
			    	sql+="and AllocatedTo='"+name+"'";
			    if(sort.equals("1"))
					 sql+=" order by id";
				 if(sort.equals("2"))
					 sql+=" order by Item";
				 if(sort.equals("3"))
					 sql+=" order by AllocatedTo";
				 if(sort.equals("4"))
					 sql+=" order by AllocatedBy";
			    
			   sql+=" limit "+s+",100";
			    
			
			 Statement statement = con.createStatement();
			 ResultSet r = statement.executeQuery(sql);
			 while(r.next())
			 {
				 a.add(r.getString(1));
				 a.add(r.getString(2));
				 a.add(r.getString(3));
				 a.add(r.getString(4));
				 a.add(r.getString(5));
				 a.add(r.getString(6));
				 a.add(r.getString(7));
				 a.add(r.getString(8));
			 }
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
		 return a;
	}
public String AdminNotAllocateSpecificcount(String Uid,String name,String search,String me,String toallocate,String toserve)throws ClassNotFoundException, SQLException {
	
	String a = null;
	Class.forName("com.mysql.cj.jdbc.Driver");
	 con = DriverManager.getConnection(dbURL, username, password);
	 String sql=null;
	 if (con != null)             
	 {
		 
		    sql = "SELECT count(id) FROM ItemDetails where ((verified='verified' and Uid!='"+Uid+"') or Uid='"+Uid+"' or AllocatedTo='"+name+"') and (Item like '"+search+"%' or uname like '"+search+"%') "; 
		 
		    if(me.equals("checked"))
		    	sql+="and Uid='"+Uid+"'";
		    if(toallocate.equals("checked"))
		    	sql+="and AllocatedTo='Not allocated'";
		    if(toserve.equals("checked"))
		    	sql+="and AllocatedTo='"+name+"'";
		    
		    System.out.println(sql);
		
		 Statement statement = con.createStatement();
		 ResultSet r = statement.executeQuery(sql);
		 if(r.next())
		 {
			 a=r.getString(1);
		 }
	 }
        else           
            System.out.println("Not Connected");
	 con.close();
	 return a;
}
	//this is not used
	public ArrayList<String> AdminAllocate()throws ClassNotFoundException, SQLException {
		
		ArrayList<String> a = new ArrayList<String>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 String sql = "SELECT id,Uid,Item,ItemReason,Status,AllocatedTo,AllocatedBy,rating FROM ItemDetails where allocate='Allocated' "; 
			 Statement statement = con.createStatement();
			 ResultSet r = statement.executeQuery(sql);
			 while(r.next())
			 {
				 a.add(r.getString(1));
				 a.add(r.getString(2));
				 a.add(r.getString(3));
				 a.add(r.getString(4));
				 a.add(r.getString(5));
				 a.add(r.getString(6));
				 a.add(r.getString(7));
				 a.add(r.getString(8));
			 }
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
		 return a;
	}
	public boolean fix(String name,String pass,String role) throws ClassNotFoundException, SQLException
	{
		//System.out.println("this is executed1");

		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 Statement statement = con.createStatement();
			 String sql =  "insert into UserDetails values("+null+",'" +name+ "', '" +pass+ 
                     "', '" +role+"','Not available','Not available','Not available','Not available','Not available')";
			
			 int x = statement.executeUpdate(sql);
			 con.close();
			 //System.out.println(x+"this is the value");
			 if (x > 0)            
				 return true;            
	            else           
	            	return false;
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
		 
	return false;
		 
	}
	public ArrayList<String> findserver(String user)throws ClassNotFoundException, SQLException {
		
		ArrayList<String> a = new ArrayList<String>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 String sql = "SELECT username FROM userDetails where role='service' or username='"+user+"'"; 
			 Statement statement = con.createStatement();
			 ResultSet r = statement.executeQuery(sql);
			 while(r.next())
			 {
				 a.add(r.getString(1));
				 
			 }
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
		 return a;
	}
	public void allocate(String Iid,String Rid,String assign) throws ClassNotFoundException, SQLException
	{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 Statement stmt = con.createStatement();
	         
	            // Updating database
	            String q1 = "UPDATE ItemDetails set allocate='Allocated',AllocatedTo = '" + assign + "',AllocatedBy ='"+ Rid +
	                    "' WHERE id = '" +Iid+ "'";
	            stmt.executeUpdate(q1);
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
		 System.out.println("This is executed2");
	
		 
	}
	
	public ArrayList<String> findusers(String name)throws ClassNotFoundException, SQLException {
		
		ArrayList<String> a = new ArrayList<String>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 String sql = "SELECT id,username,eid,role,fname,lname,floor,sno FROM userDetails where username!='"+name+"'"; 
			 Statement statement = con.createStatement();
			 ResultSet r = statement.executeQuery(sql);
			 while(r.next())
			 {
				 a.add(r.getString(1));
				 a.add(r.getString(2));
				 a.add(r.getString(3));
				 a.add(r.getString(4));
				 a.add(r.getString(5));
				 a.add(r.getString(6));
				 a.add(r.getString(7));
				 a.add(r.getString(8));
			 }
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
		 return a;
	}
	public void changerole(String Iid,String role) throws ClassNotFoundException, SQLException
	{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 Statement stmt = con.createStatement();
	         
	            // Updating database
	            String q1 = "UPDATE userDetails set role = '" + role + 
	                    "' WHERE id = '" +Iid+ "'";
	            stmt.executeUpdate(q1);
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
		 
	
		 
	}
	public ArrayList<String> ServiceAllocate(String name,String Uid)throws ClassNotFoundException, SQLException {
		
		ArrayList<String> a = new ArrayList<String>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 String sql = "SELECT id,Uid,Item,ItemReason,Status,rating FROM ItemDetails where (AllocatedTo='"+name+"' and (Status='Yet to start' or Status='Under progress' or Status='Completed')) or uid='"+Uid+"'"; 
			 Statement statement = con.createStatement();
			 ResultSet r = statement.executeQuery(sql);
			 while(r.next())
			 {
				 a.add(r.getString(1));
				 a.add(r.getString(2));
				 a.add(r.getString(3));
				 a.add(r.getString(4));
				 a.add(r.getString(5));
				 a.add(r.getString(6));
			 }
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
		 return a;
	}
public ArrayList<String> ServiceAllocatespecific(String Uid,String name,String search,String me,String toserve,int s,String sort)throws ClassNotFoundException, SQLException {
		
		ArrayList<String> a = new ArrayList<String>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 String sql = "SELECT id,Uid,Item,ItemReason,Status,rating FROM ItemDetails where (AllocatedTo='"+name+"'  or uid='"+Uid+"') and (Item like '"+search+"%' or uname like '"+search+"%')";
			 if(me.equals("checked"))
				 sql+=" and Uid='"+Uid+"'";
			 if(toserve.equals("checked"))
				 sql+=" and Status!='Completed' and Uid!='"+Uid+"'";
			 
			 if(sort.equals("1"))
				 sql+=" order by id";
			 if(sort.equals("2"))
				 sql+=" order by uname";
			 if(sort.equals("3"))
				 sql+=" order by Item";
			 sql+=" limit "+s+",100";
			 Statement statement = con.createStatement();
			 ResultSet r = statement.executeQuery(sql);
			 while(r.next())
			 {
				 a.add(r.getString(1));
				 a.add(r.getString(2));
				 a.add(r.getString(3));
				 a.add(r.getString(4));
				 a.add(r.getString(5));
				 a.add(r.getString(6));
			 }
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
		 return a;
	}
public String ServiceAllocatespecificcount(String Uid,String name,String search,String me,String toserve)throws ClassNotFoundException, SQLException {
	
	String a = null;
	Class.forName("com.mysql.cj.jdbc.Driver");
	 con = DriverManager.getConnection(dbURL, username, password);
	 if (con != null)             
	 {
		 String sql = "SELECT count(id) FROM ItemDetails where (AllocatedTo='"+name+"'  or uid='"+Uid+"') and (Item like '"+search+"%' or uname like '"+search+"%')";
		 if(me.equals("checked"))
			 sql+=" and Uid='"+Uid+"'";
		 if(toserve.equals("checked"))
			 sql+=" and Status!='Completed' and Uid!='"+Uid+"'";
		 //System.out.println(sql);
		 Statement statement = con.createStatement();
		 ResultSet r = statement.executeQuery(sql);
		 if(r.next())
		 {
			 //System.out.println(sql);
			 a=r.getString(1);
		 }
	 }
        else           
            System.out.println("Not Connected");
	 con.close();
	 return a;
}
	public String findfloor(int uno) throws ClassNotFoundException, SQLException
	{
		String result="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 String sql = "SELECT floor FROM UserDetails where id='"+uno+"'"; 
			 Statement statement = con.createStatement();
			 ResultSet r = statement.executeQuery(sql);
			 if(r.next())
			 result=r.getString(1);
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
		 return result;
	
		 
	}
	public String findseat(int uno) throws ClassNotFoundException, SQLException
	{
		String result="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 String sql = "SELECT sno FROM UserDetails where id='"+uno+"'";
			 Statement statement = con.createStatement();
			 ResultSet r = statement.executeQuery(sql);
			 if(r.next())
			 result=r.getString(1);
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
		 return result;
	
		 
	}
	public void updateStatus(String Iid,String status) throws ClassNotFoundException, SQLException
	{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 Statement stmt = con.createStatement();
	         
	            // Updating database
	            String q1 = "UPDATE ItemDetails set Status = '" + status  +
	                    "' WHERE id = '" +Iid+ "'";
	          
	            stmt.executeUpdate(q1);
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
		 
	
		 
	}
	//This has been removed
public ArrayList<String> CompletedService(String name)throws ClassNotFoundException, SQLException {
		
		ArrayList<String> a = new ArrayList<String>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 String sql = "SELECT id,Uid,Item,ItemReason,rating FROM ItemDetails where AllocatedTo='"+name+"' and (Status='Completed')"; 
			 Statement statement = con.createStatement();
			 ResultSet r = statement.executeQuery(sql);
			 while(r.next())
			 {
				 a.add(r.getString(1));
				 a.add(r.getString(2));
				 a.add(r.getString(3));
				 a.add(r.getString(4));
				 a.add(r.getString(5));
			 }
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
		 return a;
	}
//This has been removed
	public ArrayList<String> AdminNotVerified()throws ClassNotFoundException, SQLException {
	
	ArrayList<String> a = new ArrayList<String>();
	Class.forName("com.mysql.cj.jdbc.Driver");
	 con = DriverManager.getConnection(dbURL, username, password);
	 if (con != null)             
	 {
		 String sql = "SELECT id,Uid,Item,ItemReason FROM ItemDetails where verified='not verified'"; 
		 Statement statement = con.createStatement();
		 ResultSet r = statement.executeQuery(sql);
		 while(r.next())
		 {
			 a.add(r.getString(1));
			 a.add(r.getString(2));
			 a.add(r.getString(3));
			 a.add(r.getString(4));
		 }
	 }
        else           
            System.out.println("Not Connected");
	 con.close();
	 return a;
}
	public void verify(String Iid,String assign) throws ClassNotFoundException, SQLException
	{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 Statement stmt = con.createStatement();
	         
	            // Updating database
			 String q1;
	
	            if(assign.equals("rejected"))
	            {
	            	 q1 = "UPDATE ItemDetails set status='rejected',rating='rejected',allocatedby='rejected',allocatedto='rejected',allocate='rejected',verified= '" + assign + "' WHERE id = '" +Iid+ "'";
	            }
	            else
	            	 q1 = "UPDATE ItemDetails set status='Yet to start',rating='Not yet rated',allocate='Not allocated',allocatedto='Not allocated',allocatedby='Not allocated',verified= '" + assign + "' WHERE id = '" +Iid+ "'";
	            System.out.println(q1);
	            stmt.executeUpdate(q1);
		 }
            else           
                System.out.println("Not Connected");
		 con.close();
		 System.out.println("This is executed2");
	
		 
	}
	public ArrayList<String> choose()throws ClassNotFoundException, SQLException {
		
		ArrayList<String> a = new ArrayList<String>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 String sql = "SELECT id,Uid,Item,ItemReason,verified,status,rating,allocate,allocatedby,allocatedto FROM ItemDetails"; 
			 Statement statement = con.createStatement();
			 ResultSet r = statement.executeQuery(sql);
			 while(r.next())
			 {
				 a.add(r.getString(1));
				 a.add(r.getString(2));
				 a.add(r.getString(3));
				 a.add(r.getString(4));
				 a.add(r.getString(5));
				 a.add(r.getString(6));
				 a.add(r.getString(7));
				 a.add(r.getString(8));
				 a.add(r.getString(9));
				 a.add(r.getString(10));
				 
			 }
		 }
	        else           
	            System.out.println("Not Connected");
		 con.close();
		 return a;
	}
public ArrayList<String> choosespecific(String Uid,String search,String me,String toverify,int s,String sort)throws ClassNotFoundException, SQLException {
		
		ArrayList<String> a = new ArrayList<String>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(dbURL, username, password);
		 if (con != null)             
		 {
			 String sql = "SELECT id,Uid,Item,ItemReason,verified,status,rating,allocate,allocatedby,allocatedto FROM ItemDetails where (Item like '"+search+"%' or uname like '"+search+"%') "; 
			 if(me.equals("checked"))
				 sql+=" and Uid='"+Uid+"'";
			 if(toverify.equals("checked"))
				 sql+=" and verified='not verified'";
			 
			 if(sort.equals("1"))
				 sql+=" order by id";
			 if(sort.equals("2"))
				 sql+=" order by Item";
			 if(sort.equals("3"))
				 sql+=" order by AllocatedTo";
			 if(sort.equals("4"))
				 sql+=" order by AllocatedBy";
			 
			 sql+=" limit "+s+",100";
			 
			 Statement statement = con.createStatement();
			 ResultSet r = statement.executeQuery(sql);
			 while(r.next())
			 {
				 a.add(r.getString(1));
				 a.add(r.getString(2));
				 a.add(r.getString(3));
				 a.add(r.getString(4));
				 a.add(r.getString(5));
				 a.add(r.getString(6));
				 a.add(r.getString(7));
				 a.add(r.getString(8));
				 a.add(r.getString(9));
				 a.add(r.getString(10));
				 
			 }
			 System.out.println(sql);
		 }
	        else           
	            System.out.println("Not Connected");
		
		 con.close();
		 return a;
	}
public String choosespecificcount(String Uid,String search,String me,String toverify)throws ClassNotFoundException, SQLException {
	
	String a = null;
	Class.forName("com.mysql.cj.jdbc.Driver");
	 con = DriverManager.getConnection(dbURL, username, password);
	 if (con != null)             
	 {
		 String sql = "SELECT count(id) FROM ItemDetails where (Item like '"+search+"%' or uname like '"+search+"%') "; 
		 if(me.equals("checked"))
			 sql+=" and Uid='"+Uid+"'";
		 if(toverify.equals("checked"))
			 sql+=" and verified='not verified'";
		 
		 Statement statement = con.createStatement();
		 ResultSet r = statement.executeQuery(sql);
		 if(r.next())
		 {
			 a=r.getString(1);
			 
		 }
	 }
        else           
            System.out.println("Not Connected");
	 con.close();
	 return a;
}

}
