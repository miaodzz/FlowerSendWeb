package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.Config;
import dao.IFriendsDAO;
import dao.IProductDAO;
import dao.IProductorderDAO;
import dao.IUserDAO;
import dao.IUserPictureDAO;
import dao.impl.FriendsDAOImpl;
import dao.impl.ProductDAOImpl;
import dao.impl.ProductorderDAOImpl;
import dao.impl.SendInfoDAOImpl;
import dao.impl.UserDAOImpl;
import dao.impl.UserPictureDAOImpl;
import entity.Product;
import entity.Productorder;
import entity.SendInfo;
import entity.User;
import entity.UserPicture;

/**
 * Servlet implementation class UserPersonShow
 */
public class UserPersonShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserPersonShow() {
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
		String id = request.getParameter("uid");
		IUserDAO daou = new UserDAOImpl();
		User user = daou.findByID(id);
		String tel = null;
		System.out.println("被访问者" + user.getNickname());
		if (user != null) {
			tel = user.getTelephone();
			IFriendsDAO daof = new FriendsDAOImpl();
			if (!id.equals( (String) request.getSession().getAttribute(Config.USER_ID))
					&& daof.findByUF(id, (String) request.getSession().getAttribute(Config.USER_ID)) == null) {
				user.setAddress("**");
				user.setAvatar("User/UserHeadImage/20171212030838515.png");
				user.setTelephone("**");
				user.setTruename("**");
				user.setNickname("你们还不是好友哦，送个小礼物吧~");
			}
			IUserPictureDAO dao = new UserPictureDAOImpl();
			List<UserPicture> list = dao.findAll("user_id", id);

			IProductDAO dao2 = new ProductDAOImpl();
			List<Product> list2 = dao2.findAll();
			request.setAttribute("productList", list2);
			request.setAttribute("tel", tel);
			request.setAttribute("user", user);
			request.setAttribute("pictureList", list);

			IProductorderDAO dao1 = new ProductorderDAOImpl();
			// 我送出的
			List<Productorder> list1 = dao1.findAll("order_by",
					id);
			Map<Productorder, List<SendInfo>> map = new HashMap<Productorder, List<SendInfo>>();

			SendInfoDAOImpl sdao = new SendInfoDAOImpl();
			IProductDAO daop = new ProductDAOImpl();
			for (Productorder p : list1) {
				p.setToNickname("**");
				p.setProductPic(daop.findByID(p.getProductId()).getProductPicture());
				map.put(p, sdao.findPOID(p.getProductOrderId()));
			}
			request.setAttribute("productordermap1", map);

			// 送给我的
			List<Productorder> list11 = dao1.findAll("order_to",id);
			Map<Productorder, List<SendInfo>> map1 = new HashMap<Productorder, List<SendInfo>>();

			for (Productorder p : list11) {
				p.setByNickname("**");
				p.setProductPic(daop.findByID(p.getProductId()).getProductPicture());
				map1.put(p, sdao.findPOID(p.getProductOrderId()));
			}
			request.setAttribute("productordermap2", map1);

			request.getRequestDispatcher("/userPersonShow.jsp").forward(request, response);
			return;
		}
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
