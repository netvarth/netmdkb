/**
 * SessionInterceptor.java
 * @author Luciya Jos
 */
package com.nv.netmdkb.security.Interceptor;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nv.netmdkb.bl.service.SecurityService;



public class SessionInterceptor extends HandlerInterceptorAdapter {
	
	private List<Pattern> byPassAuthentication;
	private SecurityService securityService;

	private boolean bypassAuthentication(String requestUri)	{
		return RegexUtil.match(byPassAuthentication, requestUri);
	}

	
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String requestUri = request.getRequestURI() ;
		
		if (bypassAuthentication(requestUri)){
			return true;
		}
	    
		if (securityService.isLoggedIn(request.getSession().getId())){
		   return true;	
		}
		
	return false;
	}



	/**
	 * @param byPassAuthentication the byPassAuthentication to set
	 */
	public void setByPassAuthentication(List<Pattern> byPassAuthentication) {
		this.byPassAuthentication = byPassAuthentication;
	}



	/**
	 * @return the securityService
	 */



	/**
	 * @param securityService the securityService to set
	 */
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	
	
	
	
}
