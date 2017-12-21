package servlet;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import config.Config;
import dao.impl.*;
import dao.*;
import entity.*;

public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	/**
	 * Constructor of the object.
	 */
	public Login() {
		super();
	}  

	/** 
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String info = null;
		
		response.setContentType("text/html");
		response.setContentType("text/html; charset = utf-8");
	    request.setCharacterEncoding("utf-8");
	    
		String tel = request.getParameter("tel");
		String pwd = request.getParameter("pwd");
		System.out.println(tel + pwd + "正在登录...");
		if (null == tel || "".equals(pwd) || null == pwd || "".equals(pwd)) {
			info = "电话号码或密码不能为空";
		} else {
			User u = new User();
			u.setTelephone(tel);
			u.setUserPasswd(pwd);
			IUserDAO ud = new UserDAOImpl();
			User user = ud.findByTelPwd(u);
			if (user == null) {
				info = "电话号码或密码输入错误";
			} else {
				
				HttpSession session = request.getSession();
				session.setAttribute(Config.USER_ID, user.getUserId());
				session.setAttribute(Config.ROLE, user.getRole());
				session.setAttribute(Config.LOGIN_SUCCESS, Config.LOGIN_SUCCESS_VALUE);
				request.setAttribute("user", user);
				if (session.getAttribute(Config.ROLE).equals("manager"))
				{
					response.sendRedirect("admin/index.jsp");
					return;
				}
				if (session.getAttribute(Config.ROLE).equals("unregistered"))
				{
					request.getRequestDispatcher("registerStep2.jsp").forward(request, response);
					return;
				}else//普通用户
					response.sendRedirect("userPersonShow?uid="+(String)request.getSession().getAttribute(Config.USER_ID));
					return;
			}
			
		}
		request.setAttribute("info", info);
		request.getRequestDispatcher("login.jsp").forward(request, response);
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
