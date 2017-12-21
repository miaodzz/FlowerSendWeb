package servlet.my;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IProductDAO;
import dao.IProductorderDAO;
import dao.impl.ProductDAOImpl;
import dao.impl.ProductorderDAOImpl;
import dao.impl.SendInfoDAOImpl;
import entity.Productorder;
import entity.SendInfo;

/**
 * Servlet implementation class Order
 */
public class OrderToMe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderToMe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IProductorderDAO dao=new ProductorderDAOImpl();
		String r= request.getParameter("r");
		String key=request.getParameter("key");
		if(r==null) r="desc";
		if(key==null) key="purchase_time";
		List<Productorder> list=dao.findAll("order_to", (String) request.getSession().getAttribute(config.Config.USER_ID),key,r);
		Map<Productorder, List<SendInfo>> map = new LinkedHashMap<Productorder,List<SendInfo>>();
		
		SendInfoDAOImpl sdao=new SendInfoDAOImpl();
		System.out.println("list大小"+list.size());
		request.setAttribute("productorderList", list);
 
		IProductDAO daop=new ProductDAOImpl();
		for(Productorder p:list) {
			if(p.getSendback()!=1) {
				p.setByNickname("**");
			}
			p.setProductPic(daop.findByID(p.getProductId()).getProductPicture());
			
			map.put(p, sdao.findPOID(p.getProductOrderId()));
		}

		request.setAttribute("productordermap", map);
		request.getRequestDispatcher("/my/orderToMe.jsp").forward(request, response);return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
