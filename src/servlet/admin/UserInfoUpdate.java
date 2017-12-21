package servlet.admin;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IUserDAO;
import dao.impl.UserDAOImpl;
import entity.User;

/**
 * Servlet implementation class UserInfoUpdate
 */
public class UserInfoUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//在UserInfoUpdate.jsp前执行
		response.setContentType("text/html");
		response.setContentType("text/html; charset = utf-8");
	    request.setCharacterEncoding("utf-8");
		
		//System.out.println("get方法");
		String uid=(String)request.getParameter("uid");
		IUserDAO dao =new UserDAOImpl();
		User user=dao.findByID(uid);
		System.out.println(user.getNickname());
		request.setAttribute("user", user);
		request.getRequestDispatcher("/admin/userInfoUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//判断最后返回值是否为true,如果为true,提示修改成功，否则提示修改失败
		System.out.println("post方法");  
		response.setContentType("text/html; charset = utf-8");
	    request.setCharacterEncoding("utf-8");
		User user=new User();
		//缺改日期
		
		user.setUserId((String)request.getParameter("uid"));
		user.setAddress((String)request.getParameter("address"));
		user.setBirthday(util.Stringutil.String2Date((String)request.getParameter("birthday")));
		//System.out.println(user.getBirthday());
		user.setNickname((String)request.getParameter("nickname"));
		user.setSex((String)request.getParameter("sex"));
		user.setSignature((String)request.getParameter("signature"));
		user.setTelephone((String)request.getParameter("telephone"));
		user.setTruename((String)request.getParameter("truename"));
		//System.out.print(user.getNickname());
		IUserDAO dao =new UserDAOImpl(); 
		if(dao.updateNormalByID(user)>0) {
			request.setAttribute("info", "信息修改成功");
		}else{
			request.setAttribute("info", "信息修改失败,一个电话号码不能对应多位用户！请检查是否有输入错误！");
		}
		
		user=dao.findByID(user.getUserId());
		request.setAttribute("user",user);
		request.getRequestDispatcher("/admin/userInfoUpdate.jsp").forward(request, response);
		
	}

}
