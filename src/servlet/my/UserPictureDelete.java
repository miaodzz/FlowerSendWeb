package servlet.my;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.Config;
import dao.impl.UserDAOImpl;
import dao.impl.UserPictureDAOImpl;

/**
 * Servlet implementation class UserPictureDelete
 */
public class UserPictureDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPictureDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String UserPic=request.getParameter("pic");
		String uid=(String)request.getSession().getAttribute(Config.USER_ID);
		if((new UserPictureDAOImpl()).deleteByID(uid,UserPic)==1) {

			request.setAttribute("info", "刪除成功");
		}else {

			request.setAttribute("info", "刪除失敗");
		}

		request.getRequestDispatcher("/my/gallery").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
