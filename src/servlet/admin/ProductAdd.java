package servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IProductDAO;
import dao.impl.ProductDAOImpl;
import entity.Product;

/**
 * Servlet implementation class ProductAdd
 */
public class ProductAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductAdd() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Product product = new Product();
		product.setProductMean("意思");
		product.setProductName("名字");
		product.setProductPrice(200);
		product.setProductType("virtual");
		request.setAttribute("product", product);
		request.getRequestDispatcher("/admin/productAdd.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset = utf-8");
		request.setCharacterEncoding("utf-8");
		String info = null;
		Product pro = new Product();
		String name = request.getParameter("name");
		String mean = request.getParameter("mean");
		String price = request.getParameter("price");
		String type = request.getParameter("type");
		if (null == name || null == mean || null == type || null == price || "".equals(name)
				|| "".equals(mean) || "".equals(price) || "".equals(type)) {
			info = "信息修改或添加失败，任何项不能为空值";
			request.getRequestDispatcher("/admin/productAdd").forward(request, response);
			return;
		}
		if (Integer.valueOf(price) < 0 ) {
			info = "请检查信息输入是否符合标准";
			request.getRequestDispatcher("/admin/productAdd").forward(request, response);
			return;
		}
		IProductDAO dao = new ProductDAOImpl();
		pro.setProductMean(mean);
		pro.setProductName(name);
		pro.setProductType(type);
		pro.setProductPrice(Integer.valueOf(price));
		if (dao.create(pro)) {
			request.setAttribute("info", "创建成功");
		} else {
			request.setAttribute("info", "创建失败");
		}
		request.getRequestDispatcher("/admin/productList").forward(request, response);
	}

}
