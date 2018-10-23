<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="new12.DbCheck"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Raise query here</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"
	rel="stylesheet">
	
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
		
		<%if(session.getAttribute("role").equals("superadmin")){ %>
		
		<form class="form-inline my-2 my-lg-0" action="AdminAdd"  style="float: right;" method="get">
		
      <input class="form-control mr-sm-2" type="text" placeholder="Enter item to add" name="item"  required>
      
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Add</button> 
    </form>
   
    <%} %>
    
    &nbsp;
		
		<a href="UserLogout.jsp" class="btn btn-secondary btn-lg active"
			role="button" aria-pressed="true"  style="float: right;">Log out</a>
			&nbsp;
			
			<p style="float: right;" >&nbsp;&nbsp;</p>

	</div>
</nav>

	<div class="container">
		<form action="RequireRegister" method="get">
			<div class="form-group">
				<br>
				<h3>Request an Item</h3>
				<br>
				<div class="form-group row">
					<div class="col-sm-2">Select the requirement</div>
					<div class="col-sm-10">
					<select id="req" class="form-control" name="req" style="height:36px;">
        			<option selected>Choose the requirement</option>
        			<%ArrayList<String> ser = new ArrayList<String>();
        			DbCheck d=new DbCheck();
        			ser=d.finditems();
        			int j=0;
        	        while(j<ser.size()){
        	        %>
        			<option value="<%out.print(ser.get(j)); %>"><%out.print(ser.get(j)); %></option>
        			<%j++;} %>
      				</select>
      				<br>
						<input type="text" class="form-control" name="reqreason"
							placeholder="Reason for requirement" required> <br>
						
					</div>
				</div>
			</div>


			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<br>
	<%} %>

</body>
</html>