package servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IHuaguanCashOutDAO;
import dao.IHuayaPurchaseDAO;
import dao.IUserDAO;
import dao.impl.HuaguanCashOutDAOImpl;
import dao.impl.HuayaPurchaseDAOImpl;
import dao.impl.UserDAOImpl;
import entity.HuaguanCashOut;
import entity.HuayaPurchase;
import entity.User;
import util.Stringutil;

/**
 * Servlet implementation class HuaguanOutUpdate
 */
public class HuaguanOutUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HuaguanOutUpdate() { 
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
				String sid=request.getParameter("sid");
				IHuaguanCashOutDAO dao =new HuaguanCashOutDAOImpl();
				HuaguanCashOut hg=dao.findByID(Integer.valueOf(sid));
				if(hg==null) { 
					request.setAttribute("info", "该用户不存在");
					request.getRequestDispatcher("/admin/huaguanOutList").forward(request, response);
					return;
				}
				System.out.println(hg.getNickname());
				request.setAttribute("hg", hg);
				request.getRequestDispatcher("/admin/huaguanOutUpdate.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int serialnumber = Integer.valueOf(request.getParameter("sid"));//处理号
		String userId = request.getParameter("uid");//用户id 
		int money=Integer.valueOf(request.getParameter("money"));//金额
		//Timestamp ts=new Timestamp(request.getParameter("time"));//时间
		int amount=Integer.valueOf(request.getParameter("amount"));//花芽数
		
		HuaguanCashOut hy = new HuaguanCashOut();
		hy.setSerialnumber(serialnumber);
		hy.setAmount(amount);
		hy.setMoney(money);
		//hy.setTime(ts);
		hy.setUserId(userId);
		String info=null;
		if(	hy.getAmount()<0 || hy.getSerialnumber()<0||hy.getUserId()==null
				||hy.getMoney()<0||hy.getUserId().equals("")){
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			info="任何一项不能为空！";
		}else{
			IHuaguanCashOutDAO dao=new HuaguanCashOutDAOImpl();
			if(dao.updateByID(hy)==1){
				info="修改提现记录成功！";
			}else info="提现记录修改失败";
		}
		request.setAttribute("info",info);
		request.getRequestDispatcher("/admin/huaguanOutUpdate").forward(request, response);
	}


}
