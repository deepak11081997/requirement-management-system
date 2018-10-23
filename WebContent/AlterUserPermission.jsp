<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="new12.DbCheck" %>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alter users permission</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet">
<script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.3.1.js"></script> 
<script type="text/javascript"  charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
    	$('#example1').DataTable();
	} );
	</script>
<script>
$(document).ready(function(){
	$("#con2").hide();
    $("#btn").click(function(){
    	$("#con1").toggle();
        $("#con2").toggle();
    });
    $("#cancel").click(function(){
    	$("#con1").toggle();
        $("#con2").toggle();
    });
});
</script>
</head>
<body>
<%
    if ((session.getAttribute("username") == null) || (session.getAttribute("username") == "")) {
    	response.sendRedirect("Home.jsp");

  } else{ %>
<nav class="navbar navbar-expand-lg navbar-light bg-light"> <a
		class="navbar-brand"><h6>
			<b>Welcome</b> <br><%
			String s=(String)session.getAttribute("fname");
			if(s.equals("Not available"))
				s="";
			
			%><%=session.getAttribute("role")+" "+s %>
		</h6></a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="BaseHome.jsp">Request item<span class="sr-only">(current)</span></a>
			</li>
			<li class="nav-item active"><a class="nav-link"
				href="Profile.jsp">Profile<span class="sr-only">(current)</span></a>
			</li>
			<li class="nav-item active"><a class="nav-link"
				href="UserHistory.jsp">Jobs Details<span class="sr-only">(current)</span></a>
			</li>
			<%if(session.getAttribute("role").equals("superadmin")){ %>
			<li class="nav-item active"><a class="nav-link"
				href="AlterUserPermission.jsp">User permissions<span class="sr-only">(current)</span></a>
			</li>
			
			<%} %>

		</ul>
		
		<a href="UserLogout.jsp" class="btn btn-secondary btn-lg active"
			role="button" aria-pressed="true"  style="float: right;">Log out</a>
			&nbsp;
			
			<p style="float: right;" >&nbsp;&nbsp;</p>
		

	</div>
</nav>
	
	
	<br>

	
<div id="con1" class="container">
	
	<br>
	<h2>User Details</h2><br>
	
	
	
	<button type="button" id="btn"  class="btn btn-success">Add New Users</button>
	
	<br>
	<br>
	<table class="table" id="example1">
  <thead class="thead-dark">
    <tr>
      <th scope="col">S.No.</th>
      <th scope="col">User No.</th>
      <th scope="col">User Name</th>
      <th scope="col">Emp No.</th>
      <th scope="col">Role</th>
      <th scope="col">Firstname</th>
      <th scope="col">Lastname</th>
      <th scope="col">Floor</th>
      <th scope="col">Seat No.</th>
      <th scope="col" width="200dp">Change Role</th>
    </tr>
  </thead>
  <tbody>
  <%DbCheck d=new DbCheck();
  ArrayList<String> al = new ArrayList<String>();
  String user=(String)session.getAttribute("username");
  al=d.findusers(user);
  int k=1;
  int i=0;
  while(i<al.size()){
  %>
    <tr>
      <th scope="row"><%= k %></th>
      <td><%out.print(al.get(i));i++; %></td>
      <td><%out.print(al.get(i));i++; %></td>
      <td><%out.print(al.get(i));i++; %></td>
      <td><%out.print(al.get(i));i++; %></td>
      <td><%out.print(al.get(i));i++; %></td>
      <td><%out.print(al.get(i));i++; %></td>
      <td><%out.print(al.get(i));i++; %></td>
      <td><%out.print(al.get(i));i++;%></td>
      <td>
      <form action="AlterRole" method="post">
      <select id="role" class="btn btn-secondary btn-sm dropdown-toggle" name="role">
        <option selected>Allocate</option>
        <option value="user">User</option>
        <option value="service">Service</option>
        <option value="admin">admin</option>
        <option value="superadmin">super admin</option>
      </select>
      <input type="hidden"  name="Iid" value="<%=al.get(i-8)%>">
      <button type="submit" class="btn btn-primary btn-sm">Allocate</button>
      </form>
      </td>
      <%k++;} %>
      
  </tbody>
</table>
</div>


<div id="con2" class="container">
<h2>Create Account</h2><br>
<form action="AccountRegister" method="post">

  <div class="form-group">
    <label for="exampleInputEmail1">Username</label>
    <input type="text" class="form-control" name="email" aria-describedby="emailHelp" placeholder="Enter username">
    <small id="emailHelp" class="form-text text-muted">Use unique names</small>
  </div>
  
  
  <div class="form-row">
    <div class="col">
      <label for="pass1">Password</label>
      <input type="password" class="form-control" name="pass" id="pass1" placeholder="Enter password">
    </div>
    <div class="col">
      <label for="pass2">Confirm password</label>
      <input type="password" class="form-control" name="cpass" id="pass2" placeholder="Confirm password">
    </div>
  </div>
  <br>
  <label for="role">Choose role</label>
      <select id="role" class="form-control " name="role" style="height:36px;"> 
              <option selected>Choose...</option>       
        <option value="user">user</option>
        <option value="service">service provider</option>
        <option value="admin">administrator</option>
        <option value="superadmin">super administrator</option>
      </select>
      <br>
  <button type="submit" class="btn btn-primary">Create</button>
  <button id="cancel"type="button" class="btn btn-secondary">Cancel</button>
  
</form>
</div>


<%} %>
</body>
</html>