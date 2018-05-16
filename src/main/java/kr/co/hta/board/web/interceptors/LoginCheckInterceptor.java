package kr.co.hta.board.web.interceptors;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// HandlerInterceptor에는 preHandle, postHandle, afterCompletion 꼭 있어야한다.
// preHandle은 컨트롤러 실행 전
// postHandle은 컨트롤러 실행 후 
// afterCompletion은 항상실행
public class LoginCheckInterceptor implements HandlerInterceptor{

	private Set<String> urls;
	public void setUrls(Set<String> urls) {
		this.urls = urls;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String requestURI = request.getRequestURI();

		if (urls.contains(requestURI)) {
			return true;
		}
		
		HttpSession session = request.getSession();
		if (session.getAttribute("LOGIN_USER") != null) {
			return true;
		}
		response.sendRedirect("/user/login.do?err=deny");	
		return false;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
	
	// afterCompletion 우리는 거의 사용안한다.
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
}
