package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import config.Config;
import dao.IUserDAO;
import dao.impl.UserDAOImpl;
import entity.User;
import util.MD5Util;
import util.Stringutil;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tel = request.getParameter("tel");
		IUserDAO dao = new UserDAOImpl();
		String info = null;
		if (tel == null || tel.equals("") || !Stringutil.checkTelFormat(tel)) {
			info = "请检查输入是否为空或电话号码格式错误（必须为11位数字）";
			request.setAttribute("info", info);
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		User user = dao.findByTel(tel);
		if (user != null) {// 用户存在，判断是否已有unregister用户
			if (!user.getRole().equals("unregistered")) {// 是正式用户，本次注册无效
				info = "注册失败！一个电话号码只能注册一个用户！请勿重复注册！默认密码为电话号码";
				request.setAttribute("info", info);
				request.getRequestDispatcher("/register.jsp").forward(request, response);
				return;
			}

			// 是未注册用户，但已经有了相关信息表。

		} else {
			// 真正的无记录未注册用户
			user = new User();
			StringBuffer sb = new StringBuffer();
			SimpleDateFormat df = new SimpleDateFormat("mmHH");
			sb.append(df.format(new Date()));
			Random random = new Random();
			sb.append("" + random.nextInt(10)+""+random.nextInt(10)+""+random.nextInt(10)+""+random.nextInt(10));

			String userId = new String(sb);
			user.setUserId(userId);
			user.setTelephone(tel);
			user.setNickname(tel);
			user.setUserPasswd(MD5Util.getStringMD5(tel));
			user.setAddress("地址信息");
			user.setRole("unregistered");
			while(!dao.createUnregistered(user)) {
				df = new SimpleDateFormat("mmHH");
				sb.append(df.format(new Date()));
				sb.append("" + random.nextInt(10)+""+random.nextInt(10)+""+random.nextInt(10)+""+random.nextInt(10));

				userId = new String(sb);
				user.setUserId(userId);
			};

			user = dao.findByTel(tel);
		}
		HttpSession session = request.getSession();
		session.setAttribute(Config.USER_ID, user.getUserId());
		session.setAttribute(Config.ROLE, user.getRole());
		session.setAttribute(Config.LOGIN_SUCCESS, Config.LOGIN_SUCCESS_VALUE);
		User nu = dao.findByTel(tel);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/registerStep2.jsp").forward(request, response);
		// 跳到注册第二步
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
