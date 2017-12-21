package servlet.my;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.Config;
import dao.IFriendsDAO;
import dao.IProductorderDAO;
import dao.IUserDAO;
import dao.impl.FriendsDAOImpl;
import dao.impl.ProductorderDAOImpl;
import dao.impl.UserDAOImpl;
import entity.Friends;
import entity.Productorder;

/**
 * Servlet implementation class sendBackUpdate1
 */
public class SendBackUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendBackUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IProductorderDAO dao=new ProductorderDAOImpl();
		Productorder p=dao.findByID(Integer.valueOf(request.getParameter("pid")));
		int cmd=0;
		if((cmd=Integer.valueOf(request.getParameter("cmd")))==1) {
			IFriendsDAO fdao=new FriendsDAOImpl();
			Friends f=new Friends();
			f.setUserId((String)request.getSession().getAttribute(Config.USER_ID));
			f.setFriendId(p.getOrderBy());
			fdao.create(f);
			f.exchange();
			fdao.create(f);
		}
		p.setSendback(Integer.valueOf(request.getParameter("cmd")));
		dao.updateSendbackByID(p);
		List<Productorder> list=dao.findAll("order_to", (String) request.getSession().getAttribute(config.Config.USER_ID));
		
		//System.out.println("list大小"+list.size());
		request.setAttribute("info", "回赠成功");
		request.getRequestDispatcher("/my/orderToMe").forward(request, response);return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
