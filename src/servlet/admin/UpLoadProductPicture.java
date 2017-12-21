package servlet.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.Config;
import jspsmart.upload.File;
import jspsmart.upload.SmartUpload;

public class UpLoadProductPicture extends HttpServlet {
	private ServletConfig config = null;
	private String FileName = null;
	private String sPath = null;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid;
		SmartUpload mySmartUpload = new SmartUpload();
		mySmartUpload.initialize(config, request, response);

		
		mySmartUpload.setMaxFileSize(2048 * 1024);
		mySmartUpload.setAllowedFilesList("jpg,gif,png,jpeg,bmp");
		try {
			sPath = request.getSession().getServletContext().getRealPath("/");
			mySmartUpload.upload();
			File myFile = mySmartUpload.getFiles().getFile(0);
			if (!myFile.isMissing()) {

				Date currTime = new Date();

				SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddhhmmssS", Locale.US);
				FileName = new String((formatter2.format(currTime)).getBytes("utf-8"));
				String ext = myFile.getFileExt();
				FileName = FileName + "." + ext;

				myFile.saveAs(sPath + "UploadPhoto" + "/" + FileName);
				myFile.saveAs(sPath + "Product/ProductPicture" + "/" + FileName);
				myFile.saveAs(Config.UPLOAD_PHOTO_PATH + java.io.File.separator + FileName);
				myFile.saveAs(Config.UPLOAD_PRODUCT_PATH + java.io.File.separator + FileName);
			}
			pid = mySmartUpload.getRequest().getParameter("pid");System.out.println(pid);
			request.setAttribute("pid",pid);
			System.out.println("我跑完UpLoadProductPicture啦");
			request.getRequestDispatcher("uploadProductPicture.jsp?Picurl=" + FileName + "&step=2").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

}
