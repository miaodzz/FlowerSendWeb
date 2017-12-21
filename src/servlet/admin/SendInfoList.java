package servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ISendInfoDAO;
import dao.impl.SendInfoDAOImpl;
import entity.SendInfo;

/**
 * Servlet implementation class SendInfo
 */

public class SendInfoList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendInfoList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setContentType("text/html; charset = utf-8");
		request.setCharacterEncoding("utf-8");
		int rows = 0;

		ISendInfoDAO sqlHelper = new SendInfoDAOImpl();
		List<SendInfo> list = null;
		String kw=(String) request.getParameter("kw");
		if(kw==null||kw.equals("")) {
			list=sqlHelper.findAll();
			rows=sqlHelper.findCount();
		}
		else{
			
			list = sqlHelper.findPOID(Integer.valueOf(kw));
			rows=sqlHelper.countPOID(Integer.valueOf(kw));
		}

		request.setAttribute("sendInfoList", list);
		request.setAttribute("rows", rows);
		/*
		 * for(User user:list) { System.out.println(user.getNickname()); }
		 */
		request.getRequestDispatcher("/admin/sendInfoList.jsp").forward(request, response);
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
