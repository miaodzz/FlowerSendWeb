package servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.Config;
import dao.IProductDAO;
import dao.impl.ProductDAOImpl;
import entity.Product;

/**
 * Servlet implementation class ProductPictureUpdate
 */
public class ProductPictureUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductPictureUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		IProductDAO dao=new ProductDAOImpl();
		Product pro=dao.findByID(Integer.valueOf(request.getParameter("pid")));
		String picurl=pro.getProductPicture();
		picurl=picurl.split("/")[2];
		request.setAttribute("Picurl", picurl);
		request.setAttribute("pid", String.valueOf(pro.getProductId()));
		request.getRequestDispatcher("uploadProductPicture.jsp?Picurl=" + picurl + "&step=3").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
