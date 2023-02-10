<#ftl auto_esc=false>
<!DOCTYPE html>
<html> 
<head>
	<!-- META tags should always be at top inside the head tag. -->
	<meta http-equiv="X-UA-Compatible" content="IE=11"/>
	<meta name="application-name" content="uCern Engage"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
	<title>myTime</title>
</head>
<body>
	
	<div id="main-container">
		Welcome to the HOME page. You're logged in as: ${auth.name} with authorities: ${auth.authorities?join(" ")}
	 </div>
</body>
</html>