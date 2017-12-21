package servlet.my;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.Config;
import dao.IProductDAO;
import dao.IProductorderDAO;
import dao.ISendInfoDAO;
import dao.IUserDAO;
import dao.impl.ProductDAOImpl;
import dao.impl.ProductorderDAOImpl;
import dao.impl.SendInfoDAOImpl;
import dao.impl.UserDAOImpl;
import entity.Product;
import entity.Productorder;
import entity.SendInfo;
import entity.User;
import util.MD5Util;

/**
 * Servlet implementation class sendGift
 */
public class SendGift extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendGift() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		IProductDAO dao=new ProductDAOImpl();
		List<Product> list=dao.findAll();
		request.setAttribute("productList", list);
		String info=request.getParameter("info");
		if(info!=null)
			request.setAttribute("info", info);
		request.getRequestDispatcher("/my/sendGift.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset = utf-8");
	    request.setCharacterEncoding("utf-8");
		int pro=Integer.valueOf(request.getParameter("pro"));
		String totel=request.getParameter("ordertotel");
		String by=(String) request.getSession().getAttribute(Config.USER_ID);
		int daynum=Integer.valueOf(request.getParameter("daynum"));
		int days=Integer.valueOf(request.getParameter("days"));
		String address=request.getParameter("address");
		//String message=request.getParameter("message");
		IProductDAO daa=new ProductDAOImpl();
		int price=daa.findByID(pro).getProductPrice();

		IUserDAO udao=new UserDAOImpl();
		User u=udao.findByTel(totel);
		if(u==null) {
			u=new User();
			u.setAddress(address);
			u.setUserPasswd(MD5Util.getStringMD5(totel));
			u.setUserPasswd(totel);
			u.setNickname(totel);
			u.setTelephone(totel);
			Date currTime = new Date();
			SimpleDateFormat formatter2 = new SimpleDateFormat("hhmmsSdd", Locale.US);
			String id = new String((formatter2.format(currTime)).getBytes("utf-8")).substring(0, 8);
			u.setUserId(id);
			while(!udao.createUnregistered(u)) {
				formatter2 = new SimpleDateFormat("hhmmsSdd", Locale.US);
				id = new String((formatter2.format(currTime)).getBytes("utf-8")).substring(0, 8);
				u.setUserId(id);
			};
			//注册新用户
		}
		String to=udao.findByTel(totel).getUserId();
		IProductorderDAO dao=new ProductorderDAOImpl();
		Productorder p=new Productorder();
		p.setEverydayNumber(daynum);
		p.setOrderTo(to);
		p.setOrderBy(by);
		p.setProductId(pro);
		p.setSendback(0);
		p.setSendDays(days);
		p.setPurchaseTime(new java.sql.Timestamp(System.currentTimeMillis()));
		p.setCountPrice(days*daynum*price);
		String info=null;
		long curtime=System.currentTimeMillis();
		if(dao.create(p)==true) {
			//生成派送信息
			ISendInfoDAO d=new SendInfoDAOImpl();
			SendInfo si=new SendInfo();
			si.setProductOrderId(dao.findLastId());
			si.setSendman("无");
			si.setSendState("未派送");
			for(int i=0;i<days;i++) {
				si.setSendTime(new java.sql.Timestamp(curtime+i*(24*60*60*1000)));
				String message=request.getParameter("message"+i);
				si.setMessage(message);
				d.create(si);
				
			}
			info="下单成功！";
		}else {
			info="下单失败！请检查账户余额！";
		}
		request.setAttribute("info", info);
		request.getRequestDispatcher("/my/orderByMe").forward(request, response);
	}

}
