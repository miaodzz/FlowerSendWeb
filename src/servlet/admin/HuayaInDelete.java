package servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IHuayaPurchaseDAO;
import dao.impl.HuayaPurchaseDAOImpl;

/**
 * Servlet implementation class HuayaInDelete
 */

public class HuayaInDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HuayaInDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		IHuayaPurchaseDAO dao=new HuayaPurchaseDAOImpl();
		String id =request.getParameter("sid");
		String msg=null;
		if(dao.deleteByID(Integer.valueOf(id))!=1){
			msg="删除失败";
		}else {
			msg="删除成功";
		}
		request.setAttribute("info",msg);
		request.getRequestDispatcher("huayaInList").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
