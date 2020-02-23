<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>startPage</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".click").click(function() {
			$(".tip").fadeIn(200);
		});

		$(".tiptop a").click(function() {
			$(".tip").fadeOut(200);
		});

		$(".sure").click(function() {
			$(".tip").fadeOut(100);
		});

		$(".cancel").click(function() {
			$(".tip").fadeOut(100);
		});
	});

	//删除操作 单条
	function deleteStudent(stuid) {
		//alert(stuid);
		var bool = confirm("你是否要删除本条信息");
		//alert(bool);
		if (bool) {
			//发送ajax
			$.post("UserServlet", {
				"stuid" : stuid,
				"operation" : "deletestudent"
			}, function(data) {
				//alert(data);
				//判断
				if (data == "success") {
					//刷新数据
					alert("删除成功" + data);
					window.location.href = "UserServlet?operation=selectall";
				} else {
					alert("删除失败" + data);
				}
			});
		}
	}
	//删除操作
	function deleteStudents() {
		//声明一个字符串
		var varstudentids = "";
		//获取所有被选中的复选框
		var varcheckall = document.getElementsByName("checkname");
		//循环判断哪个被选中
		for (var i = 0; i < varcheckall.length; i++) {
			//判断
			if (varcheckall[i].checked == true) {
				//获取当前被选中的id studentid
				//alert(varcheckall[i].value);
				varstudentids += varcheckall[i].value + ",";
			}
		}
		//alert(varstudentids);
		//判断
		if (varstudentids == "") {
			alert("请选择要删除的选项");
			return;//结束程序
		}
		var bool = confirm("你是否要删除这些信息");
		//如果有值  发生ajax传输id进行删除操作
		//alert(varstudentids);
		if (bool) {
			//alert("before" + varstudentids.toString());
			varstudentids = varstudentids
					.substring(0, varstudentids.length - 1);
			//alert("after" + varstudentids.toString());
			$.post("UserServlet", {
				"stuids" : varstudentids,
				"operation" : "deletestudents"
			}, function(data) {
				//alert(data);
				//判断
				//alert(data);
				if (data == "success") {
					alert("删除成功" + data);
					//刷新数据
					window.location.href = "UserServlet?operation=selectall";
				} else {
					alert("删除失败" + data);
				}
			});
		}
	}
	//执行选择操作
	function stucheck(str) {//先要获取所有复选框
		var varcheckall = document.getElementsByName("checkname");
		//判断
		if (str == "all") {
			//循环
			for (var i = 0; i < varcheckall.length; i++) {
				varcheckall[i].checked = true;//选中
			}
		} else if (str == "notall") {
			//循环
			for (var i = 0; i < varcheckall.length; i++) {
				varcheckall[i].checked = false;//选中
			}
		} else if (str == "negtive") {
			//循环
			for (var i = 0; i < varcheckall.length; i++) {
				varcheckall[i].checked = !varcheckall[i].checked;//反选
			}
		}

	}
</script>

</head>


<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">数据表</a></li>
			<li><a href="#">基本内容</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="tools">

			<ul class="toolbar">
				<li class="click"><span><img src="images/t01.png" /></span><a
					href="addStudent.jsp">添加</a></li>
				<li class="click"><span><img src="images/t02.png" /></span>修改</li>
				<li><span><img src="images/t03.png" /></span><a
					href="javascirpt:" onclick="deleteStudents()">刪除</a></li>
				<li><span><img src="images/t04.png" /></span><a
					href="javascirpt:" onclick="stucheck('all')">全选</a></li>
				<li><span><img src="images/t04.png" /></span><a
					href="javascirpt:" onclick="stucheck('negtive')">反选</a></li>
				<li><span><img src="images/t04.png" /></span><a
					href="javascirpt:" onclick="stucheck('notall')">全否</a></li>
			</ul>


			<ul class="toolbar1">
				<li><span><img src="images/t05.png" /></span>设置</li>
			</ul>

		</div>


		<table class="tablelist">
			<thead>
				<tr>
					<th><input name="" type="checkbox" value="" /></th>
					<th>编号<i class="sort"><img src="images/px.gif" /></i></th>
					<th>姓名</th>
					<th>年龄</th>
					<th>性别</th>
					<th>入学日期时间</th>
					<th>班级名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${studentlist}" var="each" varStatus="vars">
					<tr>
						<td><input name="checkname" type="checkbox"
							value="${each[0]}" /></td>
						<td>${vars.count}</td>
						<td>${each[1]}</td>
						<td>${each[3]}</td>
						<td><c:if test="${each[4]==0}">女</c:if> <c:if
								test="${each[4]==1}">男</c:if></td>
						<td>${each[5]}</td>
						<td>${each[8]}</td>
						<td><a href="#" class="tablelink">修改</a> <a
							href="javascript:" class="tablelink"
							onclick="deleteStudent('${each[0]}')">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagin">
			<div class="message">
				共<i class="blue">${page.total}</i>条记录，当前显示第&nbsp;<i class="blue">${page.num}&nbsp;</i>页
			</div>
			<ul class="paginList">
				<c:if test="${page.hasPrevious}">
					<li class="paginItem"><a
						href="UserServlet?operation=selectall&num=${page.first}">首页</a></li>
					<li class="paginItem"><a
						href="UserServlet?operation=selectall&num=${page.previous}"><span
							class="pagepre"></span></a></li>
				</c:if>
				<c:forEach begin="${page.everyBegin}" end="${page.everyEnd}"
					var="vars">
					<c:if test="${page.num==vars}">
						<li class="paginItem current"><a
							href="UserServlet?operation=selectall&num=${vars}">${vars}</a></li>
					</c:if>
					<c:if test="${page.num!=vars}">
						<li class="paginItem"><a
							href="UserServlet?operation=selectall&num=${vars}">${vars}</a></li>
					</c:if>
				</c:forEach>
				<!-- <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
        <li class="paginItem"><a href="javascript:;">1</a></li>
        <li class="paginItem current"><a href="javascript:;">2</a></li>
        <li class="paginItem"><a href="javascript:;">3</a></li>
        <li class="paginItem"><a href="javascript:;">4</a></li>
        <li class="paginItem"><a href="javascript:;">5</a></li>
        <li class="paginItem more"><a href="javascript:;">...</a></li>
        <li class="paginItem"><a href="javascript:;">10</a></li>
        <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>-->
				<c:if test="${page.hasNext}">
					<li class="paginItem"><a
						href="UserServlet?operation=selectall&num=${page.next}"><span
							class="pagenxt"></span></a></li>
					<li class="paginItem"><a
						href="UserServlet?operation=selectall&num=${page.last}">尾页</a></li>
				</c:if>
			</ul>

		</div>


		<div class="tip">
			<div class="tiptop">
				<span>提示信息</span><a></a>
			</div>

			<div class="tipinfo">
				<span><img src="images/ticon.png" /></span>
				<div class="tipright">
					<p>是否确认对信息的修改 ？</p>
					<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
				</div>
			</div>

			<div class="tipbtn">
				<input name="" type="button" class="sure" value="确定" />&nbsp; <input
					name="" type="button" class="cancel" value="取消" />
			</div>

		</div>
	</div>

	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>