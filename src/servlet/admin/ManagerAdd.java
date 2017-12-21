package servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.*;
import dao.impl.*;
import dao.*;

public class ManagerAdd extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ManagerAdd() {
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

		doPost(request, response);
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
		String User_id = request.getParameter("uid");
		String info = null;
		User user = new User();
		user.setUserId(User_id);
		user.setTelephone(User_id);
		if (user.getUserId() == null || "".equals(user.getUserId())) {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			info = "不能输入空值！";
		} else {
			IUserDAO sqlHelper = new UserDAOImpl();
			user = sqlHelper.findByID(user.getUserId());
			if (user == null) {
				user = sqlHelper.findByTel(User_id);
				if (user == null) {
					response.setCharacterEncoding("utf-8");
					response.setContentType("text/html");
					info = "该用户不存在！";
				}
				if (user != null) {
					if (user.getRole().equals("manager")) {
						info = "要添加的用户已经是管理员！";
					} else {
						int b = sqlHelper.updateRole(user.getUserId(), "manager");
						if (b == 1) {
							info = "设置" + user.getUserId() + "为管理员成功";
						} else
							info = "设置失败请重试";
					}
				}
			}
		}
		request.setAttribute("info", info);
		request.getRequestDispatcher("managerAdd.jsp").forward(request, response);
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
