package org.zerock.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author wayne
 * @version 1.0
 */
@Slf4j
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final String LOGIN = "login";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();

		if (session.getAttribute(LOGIN) != null) {
			log.info("clear login data before");
			session.removeAttribute(LOGIN);
		}

		return true;
	}

	// Model에 있는 user 데이터를 세션에 저장한다.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
		Object userVO = modelAndView.getModel().get("userVO");

		if (userVO != null) {
			log.info("new login success");
			session.setAttribute(LOGIN, userVO);
//			response.sendRedirect("/");
			Object dest = session.getAttribute("dest");
			response.sendRedirect(dest != null ? (String)dest : "/" );
		}
	}
}
