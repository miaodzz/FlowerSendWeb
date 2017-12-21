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
import entity.User;

/**
 * Servlet implementation class ProductUpdate
 */
public class ProductUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p=request.getParameter("pid");
		if(p==null||"".equals(p)) {
			response.sendRedirect("/admin/404.jsp");
			return;
		}
		Integer pid=Integer.valueOf(p);
		if(pid<=0) {
			response.sendRedirect("/admin/404.jsp");
			return;
		}
		IProductDAO dao=new ProductDAOImpl();
		Product pro=dao.findByID(pid);
		if(pro==null) {
			response.sendRedirect("/admin/404.jsp");
			return;
		}
		request.setAttribute("product", pro);
		request.getRequestDispatcher("/admin/productUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset = utf-8");
		request.setCharacterEncoding("utf-8");
		String info=null;
		Product pro=new Product();
		String name=request.getParameter("name");
		String mean=request.getParameter("mean");
		String price=request.getParameter("price");
		String id=request.getParameter("pid");
		String type=request.getParameter("type");
		if(null==name||null==mean||null==type||null==price||null==id||"".equals(name)||"".equals(mean)||"".equals(price)||"".equals(id)||"".equals(type)) {
			info="信息修改或添加失败，任何项不能为空值";
			request.getRequestDispatcher("/admin/productUpdate?pid="+pro.getProductId()).forward(request, response);
			return;
		}
		if(Integer.valueOf(price)<0||Integer.valueOf(id)<=0) {
			info="请检查信息输入是否符合标准";
			request.getRequestDispatcher("/admin/productUpdate?pid="+pro.getProductId()).forward(request, response);
			return;
		}
		IProductDAO dao=new ProductDAOImpl();
		pro.setProductId(Integer.valueOf(id));
		pro.setProductMean(mean);
		pro.setProductName(name);
		pro.setProductType(type);
		pro.setProductPrice(Integer.valueOf(price));
		if(dao.updateByID(pro)==1) {
			info="商品信息更新成功";
			request.getRequestDispatcher("/admin/productList").forward(request, response);
		};
		
	}

}
