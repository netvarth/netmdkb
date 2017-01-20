package com.nv.netmdkb.rs;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.nv.netmdkb.bl.service.SecurityService;
import com.nv.netmdkb.exception.ErrorCodeEnum;
import com.nv.netmdkb.exception.ServiceExceptionHandler;
import com.nv.netmdkb.rs.dto.Response;
import com.nv.netmdkb.rs.dto.User;



@Controller
@RequestMapping("login")
public class LoginResource extends ServiceExceptionHandler {
	
	 private SecurityService securityService;
	
	@RequestMapping("/")
    public String login(){
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		
		if (securityService.isLoggedIn(req.getSession().getId())){	
			return "organisationIndex";
		}	
	   return "organisationLoginPage";
	}

	@RequestMapping(value="/logout")
	@ResponseBody
    public Response logout(){
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		Response response=new Response();
		securityService.logout(req);
		response.setStatusCode(ErrorCodeEnum.SUCCESS.toString());
		return response;
	
		
	}
	
	
	@RequestMapping(value="/signin",method=RequestMethod.POST)
	@ResponseBody
    public Response signin(@RequestBody User user){
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		Response response=new Response();
		securityService.login(user,req);
		response.setStatusCode(ErrorCodeEnum.SUCCESS.toString());
		return response;
	
		
	}
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	@ResponseBody
    public User getUser(){
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		String sessionId =req.getSession().getId();
		User user=securityService.getUser(sessionId);
		return user;
	
		
	}
	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	
	
	

}
