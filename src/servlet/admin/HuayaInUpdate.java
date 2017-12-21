package servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IHuayaPurchaseDAO;
import dao.impl.HuayaPurchaseDAOImpl;
import entity.HuayaPurchase;
import util.Stringutil;

/**
 * Servlet implementation class HuayaPurchaseUpdate
 */

public class HuayaInUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HuayaInUpdate() {
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

		response.setContentType("text/html");
		response.setContentType("text/html; charset = utf-8");
		request.setCharacterEncoding("utf-8");

		// System.out.println("get方法");
		String sid = (String) request.getParameter("sid");
		IHuayaPurchaseDAO dao = new HuayaPurchaseDAOImpl();
		HuayaPurchase hy = dao.findByID(Integer.valueOf(sid));
		if (hy == null) {
			request.setAttribute("info", "该用户不存在");
			request.getRequestDispatcher("/admin/huayaInList").forward(request, response);
			return;
		}
		System.out.println(hy.getNickname());
		request.setAttribute("hy", hy);
		request.getRequestDispatcher("/admin/huayaInUpdate.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int serialnumber = Integer.valueOf(request.getParameter("sid"));// 处理号
		String userId = request.getParameter("uid");// 用户id
		int money = Integer.valueOf(request.getParameter("money"));// 金额
		//Timestamp ts = Stringutil.String2Timestamp(request.getParameter("time"));// 时间
		int amount = Integer.valueOf(request.getParameter("amount"));// 花芽数

		HuayaPurchase hy = new HuayaPurchase();
		hy.setSerialnumber(serialnumber);
		hy.setAmount(amount);
		hy.setMoney(money);
		//hy.setTime(ts);
		hy.setUserId(userId);
		String msg = null;
		if (hy.getAmount() < 0 || hy.getSerialnumber() < 0 || hy.getUserId() == null
				|| hy.getMoney() < 0 || hy.getUserId().equals("")) {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			msg = "任何一项不能为空";
		} else {
			IHuayaPurchaseDAO dao = new HuayaPurchaseDAOImpl();
			if (dao.updateByID(hy) == 1) {
				msg = "修改成功";
			} else
				msg = "修改失败";
		}
		request.setAttribute("info", msg);
		request.getRequestDispatcher("/admin/huayaInList").forward(request, response);
	}

}
