package com.sz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RespectBinding;

import com.sz.entiy.Student;
import com.sz.service.UserService;
import com.sz.service.impl.UserServiceImpl;
import com.sz.util.SxtPageUtil;

public class UserServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserServlet() {
		super();
	}

	private void userselectall(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UserService userService = new UserServiceImpl();
		// 获取当前页码
		String strnum = req.getParameter("num");
		// 获取每页显示多少条
		String size = req.getParameter("size");
		// 获取总记录数 查询总记录数
		int total = userService.selectcountService();
		System.out.println("UserServlet.userscountService()" + total);
		// 创建分页对象
		SxtPageUtil sxtPageUtil = new SxtPageUtil<>(strnum, size, total);
		List list = userService.selectAllService(sxtPageUtil.getStart(),
				sxtPageUtil.getSize());
		System.out.println("UserServelt.userselectall()" + list);
		// 把数据存到req作用域
		req.setAttribute("studentlist", list);
		req.setAttribute("page", sxtPageUtil);
		// 请求转发到
		try {
			req.getRequestDispatcher("/studentlist.jsp").forward(req, resp);
			// return ;
		} catch (ServletException e) {
			e.printStackTrace();
		}

	}

	private void userdelete(HttpServletRequest req, HttpServletResponse reqs)
			throws IOException {
		// 根据学生的id进行删除信息
		String studentid = req.getParameter("stuid");
		// 调用业务层
		UserService userService = new UserServiceImpl();
		int n = userService.deleteStudentService(studentid);
		System.out.println("单次删除传回UserServlet的n的值=="+n);
		// 判断
		if (n > 0) {
			System.out.println("UserServlet.userdelewte(删除成功)");
			// 必须相应给出ajax 修改部分内容
			reqs.getWriter().print("success");
		} else {
			System.out.println("UserServlet.userdelewte(删除失败)");
			// 必须相应给出ajax
			reqs.getWriter().print("false");
		}
	}

	private void userlogin(HttpServletRequest req, HttpServletResponse reqs) {
		// 接受客户端发送过来的用户名和密码
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		System.out.println("UserServlet.service()" + uname + "====" + pwd);
		// 连接数据库，判断是否有这个用户，如果有跳转到main.jsp如果没有停留在login
		// 创建业务层对象
		UserService userService = new UserServiceImpl();
		Student student = userService.userlogin(uname, pwd);
		if (student != null) {
			// 有值存放到session作用域
			req.getSession().setAttribute("Student", student);
			// 请求转发main.jsp
			try {
				req.getRequestDispatcher("/main.jsp").forward(req, reqs);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("UserServlet.userlogin(登录失败)");
			req.setAttribute("msg", "用户名密码错误");// req作用域里面的信息不能使重定向在页面获取
			try {
				req.getRequestDispatcher("/404.jsp").forward(req, reqs);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod().toLowerCase();
		String operation = req.getParameter("operation");
		System.out.println(operation + method);
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
//		String uname = req.getParameter("uname");
//		String pwd = req.getParameter("pwd");
//      resp.getWriter().print("欢迎"+uname+"登录");
		if ("login".equals(operation) && "post".equals(method)) {
			userlogin(req, resp);
		} else if ("selectall".equals(operation) && "get".equals(method)) {
			userselectall(req, resp);
		}else if("deletestudent".equals(operation)&&"post".equals(method))
		{
			userdelete(req, resp);
		}else if("deletestudents".equals(operation)&&"post".equals(method))
		{
			usersdelete(req, resp);
		}
		System.out.println("执行service");
		//super.service(req, resp);
	}

	private void usersdelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 根据学生的id进行删除信息
				String studentids = req.getParameter("stuids");
				System.out.println("stuids===="+studentids.toString());
				// 调用业务层
				UserService userService = new UserServiceImpl();
				int n = userService.deleteStudentsService(studentids);
				System.out.println("批量删除传回UserServlet的n的值=="+n);
				// 判断
				if (n > 0) {
					System.out.println("UserServlet.userdelewte(删除成功)");
					// 必须相应给出ajax 修改部分内容
					resp.getWriter().print("success");	
				} else {
					System.out.println("UserServlet.userdelewte(删除失败)");
					// 必须相应给出ajax
					resp.getWriter().print("false");
				}
			
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		super.doDelete(req, resp);
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/jsp");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/jsp");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
