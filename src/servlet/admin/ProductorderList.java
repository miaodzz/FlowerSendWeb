package servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IProductorderDAO;
import dao.IUserDAO;
import dao.impl.ProductorderDAOImpl;
import dao.impl.UserDAOImpl;
import entity.Productorder;
import entity.User;


public class ProductorderList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductorderList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setContentType("text/html; charset = utf-8");
	    request.setCharacterEncoding("utf-8");
		 
        
		IProductorderDAO sqlHelper = new ProductorderDAOImpl();
		List<Productorder> list =null;
		list=sqlHelper.findAll();                  
		int rows=sqlHelper.findCount();
		 
		request.setAttribute("productOrderList", list);
		request.setAttribute("rows", rows); 
		/*for(User user:list) 
		{  
			System.out.println(user.getNickname());
		} */  
		request.getRequestDispatcher("/admin/productOrderList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
