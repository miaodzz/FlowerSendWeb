package servlet.my;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IHuaguanCashOutDAO;
import dao.impl.HuaguanCashOutDAOImpl;
import entity.HuaguanCashOut;

/**
 * Servlet implementation class HuaguanOutList
 */
@WebServlet("/HuaguanOutList")
public class HuaguanOutList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HuaguanOutList() {
        super();
        // TODO Auto-generated constructor stub 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IHuaguanCashOutDAO dao = new HuaguanCashOutDAOImpl();
		List<HuaguanCashOut> list = dao.findByUser((String) request.getSession().getAttribute(config.Config.USER_ID));
		int rows = dao.findCountUser((String) request.getSession().getAttribute(config.Config.USER_ID));
		request.setAttribute("huaguanOutList", list);
		request.setAttribute("rows", rows);
		String info=(String)request.getAttribute("info");
		if(info!=null) {
			request.setAttribute("info", info);
		}
		
		  /*for (HuaguanCashOut hp : list) {
		  
		  System.out.print(hp.getNickname()); System.out.print(hp.getUserId());
		  System.out.print(hp.getAmount()); System.out.print(hp.getMoney());
		  System.out.print(hp.getTime()); System.out.print("\n"); }
		 */
		request.getRequestDispatcher("/my/huaguanOutList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
