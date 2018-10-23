<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
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
			
			%><%=session.getAttribute("role")+" "+s %>		</h6></a>
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
<div class="container">
<h2>Update details</h2><br>
<form action="UpdateUser" method="post">
<div class="form-row">
    <div class="col">
      <label for="fname">First name</label>
      <input type="text" class="form-control" id="fname" name="fname" placeholder="First name">
    </div>
    <div class="col">
      <label for="lname">Last name</label>
      <input type="text" class="form-control" id="lname" name="lname" placeholder="Last name">
    </div>
  </div>
  <br>
  <label for="totpass"><h4>Change password</h4></label>
  <div id="totpass" class="form-row">
    <div class="col">
      <label for="pass1">Enter password</label>
      <input type="password" class="form-control" name="pass" id="pass1" placeholder="Enter password">
    </div>
    <div class="col">
      <label for="pass2">Confirm password</label>
      <input type="password" class="form-control" name="cpass" id="pass2" placeholder="Confirm password">
    </div>
  </div>
  <br>
  <div class="form-row">
    <div class="col">
      <label for="eno">Employee number</label>
      <input type="text" class="form-control" id="eno" name="eno" placeholder="Employee number">
    </div>
    <div class="col">
      <label for="floor">Floor</label>
      <select id="floor" class="form-control" name="floor" style="height:36px;" >
        <option value="" selected>Choose...</option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
      </select>
    </div>
    <div class="col">
      <label for="sloc">Seat Number</label>
      <input type="text" class="form-control" id="sloc" name="sloc" placeholder="Seat Number">
    </div>
  </div>
  <br>
  <button type="submit" class="btn btn-primary">Update</button>
  
</form>
</div>
<%} %>
</body>
</html>