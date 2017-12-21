package servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IHuayaPurchaseDAO;
import dao.IProductDAO;
import dao.impl.HuayaPurchaseDAOImpl;
import dao.impl.ProductDAOImpl;

/**
 * Servlet implementation class productDelete
 */
public class ProductDelete extends HttpServlet { 
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				IProductDAO dao=new ProductDAOImpl();
				String id =request.getParameter("pid");
				String msg=null;
				if(id.equals("2")||id.equals("1")) {
					msg="含羞草或向阳花不能删除！";
					request.setAttribute("info",msg);
					request.getRequestDispatcher("my/productList").forward(request, response);
					return;
				}
				if(dao.deleteByID(Integer.valueOf(id))!=1){
					msg="删除失败";
				}else {
					msg="删除成功";
				}
				request.setAttribute("info",msg);
				request.getRequestDispatcher("productList").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
