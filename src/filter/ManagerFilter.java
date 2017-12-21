package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import config.Config;

/**
 * Servlet Filter implementation class RoleFilter
 */
public class ManagerFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ManagerFilter() {
    
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();

		String path =req.getContextPath();
		if(session.getAttribute(Config.USER_ID)==null 
				|| "".equals(session.getAttribute(Config.USER_ID).toString())
				|| session.getAttribute(Config.LOGIN_SUCCESS)==null
				|| !Config.LOGIN_SUCCESS_VALUE.equals(session.getAttribute(Config.LOGIN_SUCCESS).toString())
		){

			res.sendRedirect(path+"/login.jsp");
			return;
		}
		if(session.getAttribute(Config.ROLE)==null||!session.getAttribute(Config.ROLE).equals("manager")) {

			res.sendRedirect(path+"/userPersonShow?uid="+req.getSession().getAttribute(Config.USER_ID));
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
