package servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IUserDAO;
import dao.impl.UserDAOImpl;
import entity.User;

/**
 * Servlet implementation class UserList
 */

public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
    /**
     * @see HttpServlet#HttpServlet()
     */ 
    public UserList() {
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
		
        int p=1;
        String pstr=(request.getParameter("p"));
		if(null!=pstr&&!"".equals(pstr))
		{
			p=Integer.valueOf(pstr);
			if(p<0) {p=1;}
		}
		System.out.println("p="+p);
		
		IUserDAO sqlHelper = new UserDAOImpl();
		List<User> list =null;
		
		String role=(String)request.getParameter("r");//为何是null呢！！！！
		if(role==null||"".equals(role))
		{
			role="all";
		}
		
		int rows=0;
		System.out.println(role);
		String kw=request.getParameter("kw");
		if(null==kw||"".equals(kw))
		{
			list = sqlHelper.findRole(role,p,10);
			rows=sqlHelper.findCountRole(role,null);
			
		}else
			{list = sqlHelper.findRole(role,p,10,kw);rows=sqlHelper.findCountRole(role,kw);}
		System.out.println("kw="+kw);
		//获取总的记录数
		
		//获取分类信息的数据
		
		request.setAttribute("r",role);
		request.setAttribute("kw", kw);
		request.setAttribute("p", p);
		request.setAttribute("userList", list);
		request.setAttribute("rows", rows); 
		/*for(User user:list) 
		{  
			System.out.println(user.getNickname());
		} */  
		request.getRequestDispatcher("userList.jsp").forward(request, response);
	}
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
