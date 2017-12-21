package servlet.my;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IHuayaPurchaseDAO;
import dao.IMoneyDAO;
import dao.impl.HuayaPurchaseDAOImpl;
import dao.impl.MoneyDAOImpl;
import entity.HuayaPurchase;
import entity.Money;
import util.Stringutil;

/**
 * Servlet implementation class HuayaIn
 */
public class HuayaIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HuayaIn() {
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
		String huaguanInput = request.getParameter("ya");
		IMoneyDAO dao = new MoneyDAOImpl();
		Money money = dao.findByID(config.Config.USER_ID);
		if (huaguanInput == null || huaguanInput.equals("") || !Stringutil.isInteger(huaguanInput)) {
			info = "充值失败！请检查输入的值是否为正整数！";

			request.setAttribute("money", money);
			request.setAttribute("info", info);
			request.getRequestDispatcher("/my/myMoney.jsp").forward(request, response);
			return;
		}
		int HuayaNum = Integer.valueOf(huaguanInput)*10;
		if (HuayaNum <= 0) {
			info = "充值失败！请检查输入的值是否为正整数！";

			request.setAttribute("money", money);
			request.setAttribute("info", info);
			request.getRequestDispatcher("/my/myMoney.jsp").forward(request, response);return;
		}
		
		money=dao.findByID((String) request.getSession().getAttribute(config.Config.USER_ID));
		money.setHuayaNum(money.getHuayaNum() + HuayaNum);
		if (dao.updateByID(money) == 1) {
			info="充值成功";
			IHuayaPurchaseDAO hd=new HuayaPurchaseDAOImpl();
			HuayaPurchase h=new HuayaPurchase();
			h.setAmount(HuayaNum);
			h.setMoney(HuayaNum*5);
			h.setTime(new java.sql.Timestamp(System.currentTimeMillis()));
			h.setUserId((String) request.getSession().getAttribute(config.Config.USER_ID));
			hd.create(h);
			request.setAttribute("money", money);
			request.setAttribute("info", info);
			request.getRequestDispatcher("/my/myMoney.jsp").forward(request, response);return;
		}

	}

}
