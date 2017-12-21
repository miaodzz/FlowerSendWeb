package servlet;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.Config;
import dao.IUserDAO;
import dao.impl.UserDAOImpl;
import entity.User;
import util.*;

public class ZoomImage extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int imageWidth = Integer.parseInt(request.getParameter("txt_width"));
		int imageHeight = Integer.parseInt(request.getParameter("txt_height"));
		int cutTop = Integer.parseInt(request.getParameter("txt_top"));
		int cutLeft = Integer.parseInt(request.getParameter("txt_left"));
		int dropWidth = Integer.parseInt(request.getParameter("txt_DropWidth"));
		int dropHeight = Integer.parseInt(request.getParameter("txt_DropHeight"));
		float imageZoom = Float.parseFloat(request.getParameter("txt_Zoom"));
		String picture = request.getParameter("picture");// 这里没有带左斜线符号的！！！！！
		/*
		 * System.out.println("imageWidth : "+imageWidth);
		 * System.out.println("imageHeight : "+imageHeight);
		 * System.out.println("cutTop : "+cutTop);
		 * System.out.println("cutLeft : "+cutLeft);
		 * System.out.println("dropWidth : "+dropWidth);
		 * System.out.println("dropHeight : "+dropHeight);
		 * System.out.println("imageZoom : "+imageZoom);
		 * System.out.println("picture : "+picture);
		 * System.out.println("url :"+request.getRealPath("")+"/UploadPhoto/"+picture);
		 */
		Rectangle rec = new Rectangle(cutLeft, cutTop, dropWidth, dropHeight);
		File file1 = new File(request.getRealPath("") + File.separator + "User" + File.separator + "UserHeadImage"
				+ File.separator + picture);
		// BufferedImage image = ImageIO.read(new
		// File(request.getRealPath("")+"/UploadPhoto/"+picture));
		File file2 = new File(config.Config.UPLOAD_HEAD_PATH + File.separator + picture);
		// 保存到真实项目
		System.out.println("file1 is saving");
		saveSubImage(new File(request.getRealPath("") + File.separator + "UploadPhoto" + File.separator + picture),
				file1, imageWidth, imageHeight, rec);
		// 保存到eclipse底下的
		System.out.println("file2 is saving");
		saveSubImage(new File(request.getRealPath("") + File.separator + "UploadPhoto" + File.separator + picture),
				file2, imageWidth, imageHeight, rec);

		System.out
				.println("上传头像" + request.getRealPath("") + File.separator + "UploadPhoto" + File.separator + picture);
		// 头像地址存数据库
		User user = new User();
		user.setAvatar("User/UserHeadImage/" + picture);
		String uid=request.getParameter("uid");
		user.setUserId(uid);

		IUserDAO dao = new UserDAOImpl();
		String info = null;
		if (dao.updateAvatarByID(user) == 1) {
			info = "修改头像成功";
		} else {
			info = "修改头像失败，请稍后重试";
		}
		request.setAttribute("info", info);
		request.setAttribute("uid", uid);
		request.getRequestDispatcher("uploadimage.jsp?Picurl=" + picture + "&step=3").forward(request, response);
	}

	private static void saveSubImage(File srcImageFile, File descDir, int width, int height, Rectangle rect)
			throws IOException {

		ImageHepler.cut(srcImageFile, descDir, width, height, rect);
	}

}
