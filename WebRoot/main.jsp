<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
</head>
<frameset rows="88,*,31" cols="*" frameborder="no" border="0"
	framespacing="0">
	<frame src="top.jsp" name="topFrame" scrolling="No" noresize="noresize"
		id="topFrame" title="topFrame" />
	<frameset cols="187,*" frameborder="no" border="0" framespacing="0">
		<frame src="left.jsp" name="leftFrame" scrolling="No"
			noresize="noresize" id="leftFrame" title="leftFrame" />
		<frame src="index.jsp" name="rightFrame" id="rightFrame"
			title="rightFrame" />
	</frameset>
	<frame src="footer.jsp" name="bottomFrame" scrolling="No"
		noresize="noresize" id="bottomFrame" title="bottomFrame" />
</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>

