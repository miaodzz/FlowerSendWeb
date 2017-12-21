package servlet.my;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IUserPictureDAO;
import dao.impl.UserPictureDAOImpl;
import entity.UserPicture;

/**
 * Servlet implementation class Gallary
 */
public class Gallery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Gallery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IUserPictureDAO dao=new UserPictureDAOImpl();
		List<UserPicture> list=dao.findAll("user_id",(String) request.getSession().getAttribute(config.Config.USER_ID));
		request.setAttribute("pictureList", list);
		request.getRequestDispatcher("/my/gallery.jsp").forward(request, response);return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
