package servlet.admin;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.Config;
import dao.ISendInfoDAO;
import dao.ISendInfoDAO;
import dao.impl.SendInfoDAOImpl;
import dao.impl.UserDAOImpl;
import dao.impl.SendInfoDAOImpl;
import entity.SendInfo;
import util.Stringutil;
import entity.SendInfo;

/**
 * Servlet implementation class SendInfoUpdate
 */
public class SendInfoUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendInfoUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid=request.getParameter("sid");
		if(sid==null||sid==""||!Stringutil.isInteger(sid)) {
				request.setAttribute("info", "请输入合法派送信息");
				request.getRequestDispatcher("/admin/sendInfoList").forward(request, response);
				return;
		}
		SendInfo sendinfo=((new SendInfoDAOImpl()).findByID(Integer.valueOf(sid)));
		if(sendinfo==null) {
			request.setAttribute("info", "不存在该派送信息");
			request.getRequestDispatcher("/admin/sendInfoList").forward(request, response);
			return;
		}
		request.setAttribute("sendinfo", sendinfo);
		request.getRequestDispatcher("/admin/sendInfoUpdate.jsp").forward(request, response);
	
	
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post方法");  
		response.setContentType("text/html; charset = utf-8");
	    request.setCharacterEncoding("utf-8");
		SendInfo sendinfo=new SendInfo();
		//缺改日期
		String s=request.getParameter("sid");
		String sendman= (new UserDAOImpl()).findByID((String)request.getSession().getAttribute(Config.USER_ID)).getNickname();
		String sendState=request.getParameter("state");
		sendinfo.setSendId(Integer.valueOf(s));
		sendinfo.setSendman(sendman);
		sendinfo.setSendState(sendState);
		sendinfo.setSendTime(new Timestamp(System.currentTimeMillis()));
		ISendInfoDAO dao =new SendInfoDAOImpl(); 
		if(dao.updateNormalByID(sendinfo)>0) {
			request.setAttribute("info", "信息修改成功");
		}else{
			request.setAttribute("info", "信息修改失败！");
		}
		
		sendinfo=dao.findByID(sendinfo.getSendId());
		request.setAttribute("sendInfo",sendinfo);
		request.getRequestDispatcher("/admin/sendInfoList?pid="+sendinfo.getProductOrderId()).forward(request, response);
	
	}
	
}
	
	
	
