package servlet.my;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IHuaguanCashOutDAO;
import dao.IMoneyDAO;
import dao.impl.HuaguanCashOutDAOImpl;
import dao.impl.MoneyDAOImpl;
import entity.HuaguanCashOut;
import entity.Money;
import util.Stringutil;

/**
 * Servlet implementation class HuaguanOut
 */
public class HuaguanOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /** 
     * @see HttpServlet#HttpServlet()
     */
    public HuaguanOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String info = null;
		String huaguanInput = request.getParameter("guan");
		IMoneyDAO dao = new MoneyDAOImpl();
		Money money = dao.findByID(config.Config.USER_ID);
		if (huaguanInput == null || huaguanInput.equals("") || !Stringutil.isInteger(huaguanInput)) {
			info = "提现失败！请检查输入的值是否为正整数！";

			request.setAttribute("money", money);
			request.setAttribute("info", info);
			request.getRequestDispatcher("/my/myMoney.jsp").forward(request, response);
			return;
		}
		int HuaguanNum = Integer.valueOf(huaguanInput);
		if (HuaguanNum <= 0) {
			info = "提现失败！请检查输入的值是否为正整数！";

			request.setAttribute("money", money);
			request.setAttribute("info", info);
			request.getRequestDispatcher("/my/myMoney.jsp").forward(request, response);return;
		}
		
		money=dao.findByID((String) request.getSession().getAttribute(config.Config.USER_ID));
		if (money.getHuaguanNum() < HuaguanNum) {
			info = "账户花冠数不足！提现失败";
			request.setAttribute("money", money);
			request.setAttribute("info", info);
			request.getRequestDispatcher("/my/myMoney.jsp").forward(request, response);return;
		}

		money.setHuaguanNum(money.getHuaguanNum() - HuaguanNum);
		if (dao.updateByID(money) == 1) {
			info="提现成功";
			IHuaguanCashOutDAO hd=new HuaguanCashOutDAOImpl();
			HuaguanCashOut h=new HuaguanCashOut();
			h.setAmount(HuaguanNum);
			h.setMoney(HuaguanNum*5);
			h.setTime(new java.sql.Timestamp(System.currentTimeMillis()));
			h.setUserId((String) request.getSession().getAttribute(config.Config.USER_ID));
			hd.create(h);
			request.setAttribute("money", money);
			request.setAttribute("info", info);
			request.getRequestDispatcher("/my/myMoney.jsp").forward(request, response);return;
		}

	}

}
