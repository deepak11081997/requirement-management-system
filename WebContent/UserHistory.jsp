<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="new12.DbCheck" %>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User history</title>
<script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.3.1.js"></script> 
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.2/jquery.twbsPagination.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.2/jquery.twbsPagination.min.js"></script>

</head>
<body>
<%
    if ((session.getAttribute("username") == null) || (session.getAttribute("username") == "")) {
    	response.sendRedirect("Home.jsp");

  } else{ %>
  
   <%  String Uid=(String)session.getAttribute("uno");
  String name=(String)session.getAttribute("username");
   DbCheck d=new DbCheck();%>
   
   
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





<%if(session.getAttribute("role").equals("user")){ %>
<script>  
$(document).ready(function(){
	usersearch();
	function usersearch(){
		var key=$("#search1").val();
		var check="notchecked";
		var sort=$("#arrange").val();
		console.log(sort);
		if($("#btn1").prop("checked") == true){
			check=$("#btn1").val();
        }
		$.ajax({
			type:'GET',
			url:'UserSearchCount?'+'key='+key+'&check='+check,
			dataType:'json',
			success:function(response){
				console.log("success");
				console.log(response);
				$('#pagination-demo').twbsPagination('destroy');
				$('#pagination-demo').off( "page" ).removeData( "twbs-pagination" ).empty();
				var a=response.user/parseFloat(100);
				a=Math.ceil(a);
				if(a==0)
					a=1;
				$('#pagination-demo').twbsPagination({
				    totalPages: a,
				    visiblePages: 5,
				    next: 'Next',
				    prev: 'Prev',
				    onPageClick: function (event, page) {
				         
				    	getpage(page);
				    }
				});
			},
			error:function(response){
				console.log("fail");
				console.log(response);
			}
			
		});
		function getpage(page)
		{
			$.ajax({
			type:'GET',
			url:'UserSearch?'+'key='+key+'&check='+check+'&page='+page+'&sort='+sort,
			dataType:'json',
			success:function(response){
				console.log("success");
				console.log(response);
				getcontent(page,response);
			},
			error:function(response){
				console.log("fail");
				console.log(response);
			}
			
		});
		}
		
	}
	function getcontent(page,response)
	{
		$('#page-content').text('Page ' + page) + ' content here';
		if(response.user[0].Item=="nothing present"){
			var html = '<table id="example1" class="table table-striped" style="width:100%"><thead class="thead-dark" ><tr><th scope="col" style="width:200px">Request No.</th><th scope="col" style="width:200px">Requirement</th><th scope="col" style="width:200px">Reason</th><th scope="col" style="width:200px">Status</th><th scope="col" style="width:200px">Rating</th></tr></thead><tbody><td>No record found</td><td></td><td></td><td></td><td></td></tbody></table>';
			 $("#mylocation1").html(html);
		}
		else{
			
			var html='<table id="example1" class="table table-striped" style="width:100%"><thead class="thead-dark" ><tr><th scope="col" style="width:200px">Request No.</th><th scope="col" style="width:200px">Requirement</th><th scope="col" style="width:200px">Reason</th><th scope="col" style="width:200px">Status</th><th scope="col" style="width:200px">Rating</th></tr></thead><tbody>';
				var n=((page-1)*100)+1;
             //$.each(response.user, function(i, k) {
            	for (i = 0; i<response.user.length; ++i) {
            	 html +="<tr><td id="+i+"3>"+n+"</td>";
				html +="<td>"+response.user[i].Item+"</td>";
				html +="<td>"+response.user[i].itemreason+"</td>";
				html +="<td>"+response.user[i].Status+"</td>";
				if(response.user[i].Status=='Completed' && response.user[i].Rating=='Not yet rated'){
					html+='<td id='+i+'2><select id='+i+'1 class="btn btn-secondary btn-sm dropdown-toggle" name="rating"><option selected>Rate here</option><option value="Bad">Bad</option><option value="Satisfied">Satisfied</option><option value="Good">Good</option></select><button type="button" value= '+response.user[i].id+' id='+i+' onclick="funct('+i+')" class="btn btn-primary btn-sm">Update</button></td></tr>';
				}
				else{
					html +="<td>"+response.user[i].Rating+"</td>";
				}
				n++;
             //});
             }
             html += '</table>';
             $("#mylocation1").html(html);
            
         }
	}
	
	
	$("#search1").keyup(usersearch);
	$("#btn1").click(usersearch);
	$("#arrange").change(usersearch);
});
</script>  
<script>
			function funct(val1){
				
			var id=document.getElementById(val1).value;
				var choice=document.getElementById(val1+"1").value;
			 	 var xhttp;
			 	document.getElementById(val1+"2").innerHTML =choice;
			 	xhttp = new XMLHttpRequest();
			 	
			 	 xhttp.open("GET", "UserRating?p="+id+"&q="+choice, true);
			 	 xhttp.send();
			 }
</script>
<div class="container">   
	<h3>View My History</h3><br>
	<div class="form-check form-check-inline">
  <input class="form-check-input" type="checkbox" id="btn1" value="checked">
  <label class="form-check-label" for="btn1">Request to be rated</label>
</div>
<div class="form-check form-check-inline">
  <input type="text" class="form-control" placeholder="Search here" id="search1">
</div>
<div class="form-check form-check-inline"><label for="arrange">Arrange</label></div>
<div class="form-check form-check-inline">
<div class="form-group">
    
    <select class="form-control" id="arrange">
      <option value="1">Request No</option>
      <option value="2">Requirement</option>
      <option value="3">Status</option>
      <option value="4">Rating</option>
    </select>
  </div>
</div>  
  
<br>
<br>
<div id="page-content" ></div>
<br>
<div id="pagination-demo" class="pagination-sm"></div>
	<span id="mylocation1">
	</span>
</div>

<%} %>



<%if(session.getAttribute("role").equals("admin")){ %>


			
<script>  
$(document).ready(function(){
	usersearch2();
	function usersearch2(){
		var key=$("#search2").val();
		var sort=$("#arrange2").val();
		var check1="notchecked";
		var check2="notchecked";
		var check3="notchecked";
		if($("#btn2").prop("checked") == true){
			check1=$("#btn2").val();
        }
		if($("#btn3").prop("checked") == true){
			check2=$("#btn3").val();
        }
		if($("#btn4").prop("checked") == true){
			check3=$("#btn4").val();
        }
		$.ajax({
			type:'GET',
			url:'AdminSearchCount?'+'key='+key+'&check1='+check1+'&check2='+check2+'&check3='+check3,
			dataType:'json',
			success:function(response){
				console.log("success");
				console.log(response);				
				$('#pagination-demo').twbsPagination('destroy');
				$('#pagination-demo').off( "page" ).removeData( "twbs-pagination" ).empty();
				var a=response.user/parseFloat(100);
				a=Math.ceil(a);
				if(a==0)
					a=1;
				$('#pagination-demo').twbsPagination({
				    totalPages: a,
				    visiblePages: 5,
				    next: 'Next',
				    prev: 'Prev',
				    onPageClick: function (event, page) {   
				    	getpage2(page);
				    }
				});
				//getting content from here
				
				
				//getting content from here
				
				
				
				
				
				
				
			},
			error:function(response){
				console.log("fail");
				console.log(response);
			}
		});
		function getpage2(page)
		{
			$.ajax({
				type:'GET',
				url:'AdminSearch?'+'key='+key+'&check1='+check1+'&check2='+check2+'&check3='+check3+'&page='+page+'&sort='+sort,
				dataType:'json',
				success:function(response){
					console.log("success");
					console.log(response);
					getcontent2(page,response);
				},
				error:function(response){
					console.log("fail");
					console.log(response);
				}
			});
		}
		
	}
	function getcontent2(page,response)
	{
		$('#page-content').text('Page ' + page) + ' content here';
		if(response.user2[0].user[0].Item=="nothing present"){
			var html = '<table id="example2" class="table table-striped" style="width:100%"><thead class="thead-dark" ><tr><th scope="col" >S.No.</th><th scope="col" >Request No.</th><th scope="col" >Requester</th><th scope="col" >Requirement</th><th scope="col" >Reason</th><th scope="col" >Status</th><th scope="col" >Allocate to</th><th scope="col" >Allocate by</th><th scope="col" >Rating</th></tr></thead><tbody><td>No record found</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tbody></table>';
			 $("#mylocation2").html(html);
		}
		else{
			 var html = '<table id="example2" class="table table-striped" style="width:100%"><thead class="thead-dark" ><tr><th scope="col" >S.No.</th><th scope="col" >Request No.</th><th scope="col" >Requester</th><th scope="col" >Requirement</th><th scope="col" >Reason</th><th scope="col" >Status</th><th scope="col" >Allocate to</th><th scope="col" >Allocate by</th><th scope="col" >Rating</th></tr></thead><tbody>';
				var n=((page-1)*100)+1;
				for (i = 0;  i<response.user2[0].user.length; ++i) {
             //$.each(response.user2[0].user, function(i, k) {
            	 html +="<tr><th id="+i+"3>"+n+"</th>";
            	 html +="<td>"+response.user2[0].user[i].id+"</td>";
            	 html +="<td>"+response.user2[0].user[i].Uid+"</td>";
				html +="<td>"+response.user2[0].user[i].Item+"</td>";
				html +="<td>"+response.user2[0].user[i].itemreason+"</td>";
				if(response.user2[0].user[0].Me==response.user2[0].user[i].AllocatedTo && (response.user2[0].user[i].Status=='Yet to start' || response.user2[0].user[i].Status=='Under progress')){
					html+='<td id='+i+'2><select id='+i+'1 class="btn btn-secondary btn-sm dropdown-toggle" name="rating"><option selected>Choose....</option>';
					if(response.user2[0].user[i].Status!="Under progress"){html+='<option value="Under progress">Under progress</option>';}
					html+='<option value="Completed">   Completed   </option></select><button type="button" value= '+response.user2[0].user[i].id+' id='+i+' onclick="funUpdate('+i+')" class="btn btn-primary btn-sm">Update</button></td>';
				}
				else{
					html +="<td>"+response.user2[0].user[i].Status+"</td>";
				}
				if(response.user2[0].user[i].AllocatedTo=="Not allocated"){
					var j=0;
					var t;
					html+='<td id='+i+'2><select id='+i+'1 class="btn btn-secondary btn-sm dropdown-toggle" name="rating"><option selected>Choose</option>';
					while(j<response.user2[1].user1.length){//serv.lenght
						if(response.user2[1].user1[j].server!=response.user2[0].user[i].Uid){
						html+=	'<option value="'+response.user2[1].user1[j].server+'">'+response.user2[1].user1[j].server+'</option>'
						}
					j++;	}
					html+='</select><button type="button" value= '+response.user2[0].user[i].id+' id='+i+' onclick="funAllocate('+i+')" class="btn btn-primary btn-sm">Allocate</button></td>';
				}
				else{
					html +="<td>"+response.user2[0].user[i].AllocatedTo+"</td>";
				}
				html +="<td id="+i+"5>"+response.user2[0].user[i].AllocatedBy+"</td>";
				if(response.user2[0].user[i].Status=='Completed' && response.user2[0].user[i].Rating=='Not yet rated' && response.user2[0].user[0].Me==response.user2[0].user[i].Uid){
					html+='<td id='+i+'2><select id='+i+'1 class="btn btn-secondary btn-sm dropdown-toggle" name="rating"><option selected>Choose</option><option value="Bad">Bad</option><option value="Satisfied">Satisfied</option><option value="Good">Good</option></select><button type="button" value= '+response.user2[0].user[i].id+' id='+i+' onclick="funRate('+i+')" class="btn btn-primary btn-sm">Rate</button></td></tr>';
				}
				else{
					html +="<td>"+response.user2[0].user[i].Rating+"</td>";
				}
				n++;
             //});
				}
             html += '</table>';
             $("#mylocation2").html(html);
            
		}
	}
	$("#search2").keyup(usersearch2);
	$("#btn2").click(usersearch2);
	$("#btn3").click(usersearch2);
	$("#btn4").click(usersearch2);
	$("#arrange2").change(usersearch2);
});
</script>  
<script>
			function funUpdate(val1){
				
			var id=document.getElementById(val1).value;
				var choice=document.getElementById(val1+"1").value;
			 	 var xhttp;
			 	document.getElementById(val1+"2").innerHTML =choice;
			 	xhttp = new XMLHttpRequest();
			 	
			 	 xhttp.open("GET", "ServiceUpdate?p="+id+"&q="+choice, true);
			 	 xhttp.send();
			 }
</script>
<script>
			function funAllocate(val1){
				
			var id=document.getElementById(val1).value;
				var choice=document.getElementById(val1+"1").value;
			 	 var xhttp;
			 	document.getElementById(val1+"2").innerHTML =choice;
			 	document.getElementById(val1+"5").innerHTML ="Allocated by you";
			 	xhttp = new XMLHttpRequest();
			 	
			 	 xhttp.open("POST", "AdminAssign?p="+id+"&q="+choice, true);
			 	 xhttp.send();
			 }
</script>
<script>
			function funRate(val1){
				
			var id=document.getElementById(val1).value;
				var choice=document.getElementById(val1+"1").value;
			 	 var xhttp;
			 	document.getElementById(val1+"2").innerHTML =choice;
			 	xhttp = new XMLHttpRequest();
			 	
			 	 xhttp.open("GET", "UserRating?p="+id+"&q="+choice, true);
			 	 xhttp.send();
			 }
</script>

<div class="container">
      <h2>Authenticated Jobs </h2>
	<br>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="checkbox" id="btn2"  value="checked">
  <label class="form-check-label" for="btn2">Show Me</label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="checkbox" id="btn3" value="checked">
  <label class="form-check-label" for="btn3">Request to be allocated</label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="checkbox" id="btn4" value="checked">
  <label class="form-check-label" for="btn4">Request to be served</label>
</div>
<div class="form-check form-check-inline">
  <input type="text" class="form-control" placeholder="Search here" id="search2">
</div>
<div class="form-check form-check-inline"><label for="arrange">Arrange</label></div>
<div class="form-check form-check-inline">
<div class="form-group">
    
    <select class="form-control" id="arrange2">
      <option value="1">Request No</option>
      <option value="2">Requirement</option>
      <option value="3">Allocate to</option>
      <option value="4">Allocate by</option>
    </select>
  </div>
</div> 
<br>
<br>
<div id="page-content" ></div>
<br>
<div id="pagination-demo" class="pagination-sm"></div>
<span id="mylocation2"></span>
</div>
<%} %>



<%if(session.getAttribute("role").equals("superadmin")){ %>
<script>  
$(document).ready(function(){
	usersearch3();
	function usersearch3(){
		var key=$("#search3").val();
		var sort=$("#arrange3").val();
		var check1="notchecked";
		var check2="notchecked";
		if($("#btn5").prop("checked") == true){
			check1=$("#btn5").val();
        }
		if($("#btn6").prop("checked") == true){
			check2=$("#btn6").val();
        }
		$.ajax({
			type:'GET',
			url:'SuperSearchCount?'+'key='+key+'&check1='+check1+'&check2='+check2,
			dataType:'json',
			success:function(response){
				console.log("success");
				console.log(response);
				$('#pagination-demo').twbsPagination('destroy');
				$('#pagination-demo').off( "page" ).removeData( "twbs-pagination" ).empty();
				
				var a=response.user/parseFloat(100);
				a=Math.ceil(a);
				if(a==0)
					a=1;
				$('#pagination-demo').twbsPagination({
				    totalPages: a,
				    visiblePages: 5,
				    next: 'Next',
				    prev: 'Prev',
				    onPageClick: function (event, page) {
				         
				    	getpage3(page);
				    }
				});
				
			},
			error:function(response){
				console.log("fail");
				console.log(response);
			}
		});
		function getpage3(page)
		{
			$.ajax({
				type:'GET',
				url:'SuperSearch?'+'key='+key+'&check1='+check1+'&check2='+check2+'&page='+page+'&sort='+sort,
				dataType:'json',
				success:function(response){
					console.log("success");
					console.log(response);
					getcontent3(page,response);
					//getting content from here
					
					
					//getting content from here
	                     
	                     
	                     
	                     
				},
				error:function(response){
					console.log("fail");
					console.log(response);
				}
				
			});
		}
	}
	function getcontent3(page,response)
	{
		$('#page-content').text('Page ' + page) + ' content here';
		if(response.user[0].Item=="nothing present"){
		var html = '<table id="example3" class="table table-striped" style="width:100%"><thead class="thead-dark" ><tr><th scope="col" >S.No.</th><th scope="col" >Request No.</th><th scope="col" >Requester</th><th scope="col" >Requirement</th><th scope="col" >Reason</th><th scope="col" >Verification Status</th><th scope="col" >Item Status</th><th scope="col" >Rating</th><th scope="col" >Allocate status</th><th scope="col" >Allocate by</th><th scope="col" >Allocate to</th></tr></thead><tbody><td>No record found</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tbody></table>';
		 $("#mylocation3").html(html);
	}
	else{
		 var html = '<table id="example3" class="table table-striped" style="width:100%"><thead class="thead-dark" ><tr><th scope="col" >S.No.</th><th scope="col" >Request No.</th><th scope="col" style="width:3%">Requester</th><th scope="col" style="width:5%">Requirement</th><th scope="col" >Reason</th><th scope="col" style="width:15%">Verification Status</th><th scope="col" >Item Status</th><th scope="col"  style="width:8%">Rating</th><th scope="col" >Allocate status</th><th scope="col" >Allocate by</th><th scope="col" >Allocate to</th></tr></thead><tbody>';
			var n=((page-1)*100)+1;
         $.each(response.user, function(i, k) { 
        	 html +="<tr><th id="+i+"c>"+n+"</th>";
        	 html +="<td>"+response.user[i].id+"</td>";
        	 html +="<td>"+response.user[i].Uid+"</td>";
			html +="<td>"+response.user[i].Item+"</td>";
			html +="<td>"+response.user[i].itemreason+"</td>";
			if(response.user[i].verified=="not verified"){
				html+='<td id='+i+'b><select id='+i+'a class="btn btn-secondary btn-sm dropdown-toggle" name="rating"><option selected>Choose</option><option value="verified">Accept</option><option value="rejected">Reject</option></select><button type="button" value= '+response.user[i].id+' id='+i+' onclick="funValidate('+i+')" class="btn btn-primary btn-sm">Verify</button></td>';
			}
			else{
				html +="<td>"+response.user[i].verified+"</td>";
			}
			html +="<td id="+i+"g>"+response.user[i].Status+"</td>";
			if(response.user[i].Status=='Completed' && response.user[i].Rating=='Not yet rated' && response.user[0].Me==response.user[i].Uid){
				html+='<td id='+i+'b><select id='+i+'a class="btn btn-secondary btn-sm dropdown-toggle" name="rating"><option selected>Choose</option><option value="Bad">Bad</option><option value="Satisfied">Satisfied</option><option value="Good">Good</option></select><button type="button" value= '+response.user[i].id+' id='+i+' onclick="funRate('+i+')" class="btn btn-primary btn-sm">Rate</button></td>';
			}
			else{
				html +="<td id="+i+"h>"+response.user[i].Rating+"</td>";
			}
			html +="<td id="+i+"i>"+response.user[i].allocate+"</td>";
			html +="<td id="+i+"j>"+response.user[i].allocatedby+"</td>";
			html +="<td id="+i+"k>"+response.user[i].allocatedto+"</td>";						
			n++;
         });
         html += '</table>';
         $("#mylocation3").html(html);
         
     }
	}
	$("#search3").keyup(usersearch3);
	$("#arrange3").change(usersearch3);
	$("#btn5").click(usersearch3);
	$("#btn6").click(usersearch3);
	
});
</script>  
<script>
			function funValidate(val1){		
			var id=document.getElementById(val1).value;
				var choice=document.getElementById(val1+"a").value;
			 	 var xhttp;
			 	document.getElementById(val1+"b").innerHTML =choice;
			 	document.getElementById(val1+"g").innerHTML =choice;
			 	document.getElementById(val1+"h").innerHTML =choice;
			 	document.getElementById(val1+"i").innerHTML =choice;
			 	document.getElementById(val1+"j").innerHTML =choice;
			 	document.getElementById(val1+"k").innerHTML =choice;
			 	xhttp = new XMLHttpRequest();
			 	
			 	 xhttp.open("POST", "AdminVerify?p="+id+"&q="+choice, true);
			 	 xhttp.send();
			 }
</script>
<script>
			function funRate(val1){
				
			var id=document.getElementById(val1).value;
				var choice=document.getElementById(val1+"a").value;
			 	 var xhttp;
			 	document.getElementById(val1+"b").innerHTML =choice;
			 	xhttp = new XMLHttpRequest();
			 	
			 	 xhttp.open("GET", "UserRating?p="+id+"&q="+choice, true);
			 	 xhttp.send();
			 }
</script>

<div class="container">
<h2>Request History</h2>
	<br>
	
<div class="form-check form-check-inline">
  <input class="form-check-input" type="checkbox" id="btn5"  value="checked">
  <label class="form-check-label" for="btn5">Show Me</label>
</div>

<div class="form-check form-check-inline">
  <input class="form-check-input" type="checkbox" id="btn6" value="checked" >
  <label class="form-check-label" for="btn6">Request to be verified</label>
</div>
<div class="form-check form-check-inline">
  <input type="text" class="form-control" placeholder="Search here" id="search3">
</div>
<div class="form-check form-check-inline"><label for="arrange3">Arrange</label></div>
<div class="form-check form-check-inline">
<div class="form-group">
    
    <select class="form-control" id="arrange3">
      <option value="1">Request No</option>
      <option value="2">Requirement</option>
      <option value="3">Allocate to</option>
      <option value="4">Allocate by</option>
    </select>
  </div>
</div> 
<br>
<br>
<div id="page-content" ></div>
<br>
<div id="pagination-demo" class="pagination-sm"></div>
<span id="mylocation3">

</span>
</div>
<%} %>

<%if(session.getAttribute("role").equals("service")){ %>

<script>  
$(document).ready(function(){
	usersearch4();
	function usersearch4(){
		var key=$("#search4").val();
		var sort=$("#arrange4").val();
		var check1="notchecked";
		var check2="notchecked";
		if($("#btn7").prop("checked") == true){
			check1=$("#btn7").val();
        }
		if($("#btn8").prop("checked") == true){
			check2=$("#btn8").val();
        }
		$.ajax({
			type:'GET',
			url:'ServiceSearchCount?'+'key='+key+'&check1='+check1+'&check2='+check2,
			dataType:'json',
			success:function(response){
				console.log("success");
				console.log(response);
				$('#pagination-demo').twbsPagination('destroy');
				$('#pagination-demo').off( "page" ).removeData( "twbs-pagination" ).empty();
				
				var a=response.user/parseFloat(100);
				a=Math.ceil(a);
				if(a==0)
					a=1;
				$('#pagination-demo').twbsPagination({
				    totalPages: a,
				    visiblePages: 5,
				    next: 'Next',
				    prev: 'Prev',
				    onPageClick: function (event, page) {
				         
				    	getpage4(page);
				    }
				});
				
			},
			error:function(response){
				console.log("fail");
				console.log(response);
			}
		});
		function getpage4(page)
		{
			$.ajax({
			type:'GET',
			url:'ServiceSearch?'+'key='+key+'&check1='+check1+'&check2='+check2+'&page='+page+'&sort='+sort,
			dataType:'json',
			success:function(response){
				console.log("success");
				console.log(response);
				getcontent4(page,response);
				//getting content from here
				
				
				//getting content from here
                     
                     
                     
                     
			},
			error:function(response){
				console.log("fail");
				console.log(response);
			}
			
		});
		}
	}

	function getcontent4(page,response)
	{
		if(response.user[0].Item=="nothing present"){
			var html = '<table id="example4" class="table table-striped" style="width:100%"><thead class="thead-dark" ><tr><th scope="col" >S.No.</th><th scope="col" >Request No.</th><th scope="col" >Requester</th><th scope="col" >Requirement</th><th scope="col" >Reason</th><th scope="col" >Floor </th><th scope="col" >Seat No.</th><th scope="col" >Current Status</th><th scope="col" >Rating</th><th scope="col" >Update Status</th></tr></thead><tbody><td>No record found</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tbody></table>';
			 $("#mylocation4").html(html);
		}
		else{
			 var html = '<table id="example4" class="table table-striped" style="width:100%"><thead class="thead-dark" ><tr><th scope="col" >S.No.</th><th scope="col" >Request No.</th><th scope="col" >Requester</th><th scope="col" >Requirement</th><th scope="col" >Reason</th><th scope="col" >Floor </th><th scope="col" >Seat No.</th><th scope="col" >Current Status</th><th scope="col" >Rating</th><th scope="col" >Update Status</th></tr></thead><tbody>';
			 var n=((page-1)*100)+1;
             $.each(response.user, function(i, k) { 
            	 html +="<tr><th id="+i+"c>"+n+"</th>";
            	 html +="<td>"+response.user[i].id+"</td>";
            	 html +="<td>"+response.user[i].Uid+"</td>";
				html +="<td>"+response.user[i].Item+"</td>";
				html +="<td>"+response.user[i].itemreason+"</td>";
				html +="<td>"+response.user[i].Seat+"</td>";
				html +="<td>"+response.user[i].Floor+"</td>";
				html +="<td id="+i+"d>"+response.user[i].Status+"</td>";
				if(response.user[i].Status=='Completed' && response.user[i].Rating=='Not yet rated' && response.user[0].Me==response.user[i].Uid){
					html+='<td id='+i+'b><select id='+i+'a class="btn btn-secondary btn-sm dropdown-toggle" name="rating"><option selected>Rate here</option><option value="Bad">Bad</option><option value="Satisfied">Satisfied</option><option value="Good">Good</option></select><button type="button" value= '+response.user[i].id+' id='+i+' onclick="funRate('+i+')" class="btn btn-primary btn-sm">Rate</button></td>';
				}
				else{
					html +="<td>"+response.user[i].Rating+"</td>";
				}
				
				if(response.user[i].Status=='Completed' || response.user[0].Me==response.user[i].Uid ){
					
					html +="<td>cannot be updated</td>";
				}
				else{
					html+='<td id='+i+'b><select id='+i+'a class="btn btn-secondary btn-sm dropdown-toggle" name="rating"><option selected>Update here</option>';
					if(response.user[i].Status!="Under progress"){
						html+='<option value="Under progress">Under progress</option>';
					}
					html+='<option value="Completed">Completed</option></select><button type="button" value= '+response.user[i].id+' id='+i+' onclick="funUpdate('+i+')" class="btn btn-primary btn-sm">Update</button></td></tr>';
					
				}
				n++;
             });
             html += '</table>';
             $("#mylocation4").html(html);
         }
	}
	$("#search4").keyup(usersearch4);
	$("#arrange4").change(usersearch4);
	$("#btn7").click(usersearch4);
	$("#btn8").click(usersearch4);
	
});
</script>  
<script>
function funUpdate(val1){
	
	var id=document.getElementById(val1).value;
		var choice=document.getElementById(val1+"a").value;
	 	 var xhttp;
	 	 console.log("this is called with:"+id+" "+choice);
	 	document.getElementById(val1+"d").innerHTML =choice;
	 	//if(choice=="Completed")
	 		document.getElementById(val1+"b").innerHTML ="Request updated";
	 	xhttp = new XMLHttpRequest();
	 	
	 	 xhttp.open("GET", "ServiceUpdate?p="+id+"&q="+choice, true);
	 	 xhttp.send();
	 }
</script>
<script>
			function funRate(val1){
				
			var id=document.getElementById(val1).value;
				var choice=document.getElementById(val1+"a").value;
			 	 var xhttp;
			 	document.getElementById(val1+"b").innerHTML =choice;
			 	xhttp = new XMLHttpRequest();
			 	
			 	 xhttp.open("GET", "UserRating?p="+id+"&q="+choice, true);
			 	 xhttp.send();
			 }
</script>


<div class="container">

<h2>Jobs History</h2>
	<br>
	
<div class="form-check form-check-inline">
  <input class="form-check-input" type="checkbox" id="btn7"  value="checked">
  <label class="form-check-label" for="btn7">Show Me</label>
</div>

<div class="form-check form-check-inline">
  <input class="form-check-input" type="checkbox" id="btn8" value="checked">
  <label class="form-check-label" for="btn8">Request to be updated</label>
</div>
<div class="form-check form-check-inline">
  <input type="text" class="form-control" placeholder="Search here" id="search4">
</div>
<div class="form-check form-check-inline"><label for="arrange">Arrange</label></div>
<div class="form-check form-check-inline">
<div class="form-group">
    
    <select class="form-control" id="arrange4">
      <option value="1">Request No</option>
      <option value="2">Requester</option>
      <option value="3">Requirement</option>
    </select>
  </div>
</div> 
<br>
<br>
<div id="page-content" ></div>
<br>
<div id="pagination-demo" class="pagination-sm"></div>
<span id="mylocation4">
</span>
</div>
<%} %>

 <%} %>
</body>
</html>