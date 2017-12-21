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

/**
 * Servlet implementation class MyMoney
 */
public class MyMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyMoney() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IMoneyDAO dao=new MoneyDAOImpl();
		//System.out.println("userid="+(String) request.getSession().getAttribute(config.Config.USER_ID));
		Money money=dao.findByID((String) request.getSession().getAttribute(config.Config.USER_ID));
		 
		
		request.setAttribute("money", money);
		request.getRequestDispatcher("/my/myMoney.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
