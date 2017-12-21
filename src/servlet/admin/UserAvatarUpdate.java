package servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.Config;
import dao.IUserDAO;
import dao.impl.UserDAOImpl;
import entity.User;

/**
 * Servlet implementation class UserAvatarUpdate
 */
public class UserAvatarUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAvatarUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/** 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		IUserDAO dao=new UserDAOImpl();
		User user=dao.findByID(request.getParameter("uid"));
		String picurl=user.getAvatar();
		picurl=picurl.split("/")[2];
		request.setAttribute("Picurl", picurl);
		request.setAttribute("uid", user.getUserId());
		request.getRequestDispatcher("uploadimage.jsp?Picurl=" + picurl + "&step=3").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
