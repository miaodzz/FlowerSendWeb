package servlet.my;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IMoneyDAO;
import dao.impl.MoneyDAOImpl;
import entity.Money;
import util.Stringutil;

/**
 * Servlet implementation class Huaya2Huaguan 
 */
public class Huaya2Huaguan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Huaya2Huaguan() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
 
	/** 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String info = null;
		String huayaInput = request.getParameter("ya2guan");
		IMoneyDAO dao = new MoneyDAOImpl();
		Money money = dao.findByID(config.Config.USER_ID);
		if (huayaInput == null || huayaInput.equals("") || !Stringutil.isInteger(huayaInput)) {
			info = "兑换失败！请检查输入的值是否为正整数！";

			request.setAttribute("money", money);
			request.setAttribute("info", info);
			request.getRequestDispatcher("/my/myMoney.jsp").forward(request, response);
			return;
		}
		int HuayaNum = Integer.valueOf(huayaInput) * 1500;
		if (HuayaNum <= 0) {
			info = "兑换失败！请检查输入的值是否为正整数！";

			request.setAttribute("money", money);
			request.setAttribute("info", info);
			request.getRequestDispatcher("/my/myMoney.jsp").forward(request, response);return;
		}
		
		money=dao.findByID((String) request.getSession().getAttribute(config.Config.USER_ID));
		if (money.getHuayaNum() < HuayaNum) {
			info = "账户花芽数不足！兑换失败";
			request.setAttribute("money", money);
			request.setAttribute("info", info);
			request.getRequestDispatcher("/my/myMoney.jsp").forward(request, response);return;
		}

		money.setHuayaNum(money.getHuayaNum() - HuayaNum);
		money.setHuaguanNum(money.getHuaguanNum() + HuayaNum / 1500);
		if (dao.updateByID(money) == 1) {
			info="兑换成功";
			request.setAttribute("money", money);
			request.setAttribute("info", info);
			request.getRequestDispatcher("/my/myMoney.jsp").forward(request, response);return;
		}

	}

}
