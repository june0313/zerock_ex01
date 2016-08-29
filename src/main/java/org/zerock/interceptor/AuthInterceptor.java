package org.zerock.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;
import org.zerock.domain.UserVO;
import org.zerock.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author wayne
 * @version 1.0
 */
@Slf4j
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private UserService service;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();

		// 로그인 안한 사용자는 로그인 페이지로 이동시킨다.
		if (null == session.getAttribute("login")) {
			log.info("current user is not logined..............");

			saveDest(request);

			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");

			if (loginCookie != null) {
				UserVO userVO = service.checkLoginBefore(loginCookie.getValue());
				log.info("USERVO : " + userVO);

				if (userVO != null) {
					session.setAttribute("login", userVO);
					return true;
				}
			}

			response.sendRedirect("/user/login");
			return false;
		}

		return true;
	}

	private void saveDest(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String query = request.getQueryString();

		if (null == query || "null".equals(query)) {
			query = "";
		} else {
			query = "?" + query;
		}

		if ("GET".equals(request.getMethod())) {
			log.info("dest : " + (uri + query));
			request.getSession().setAttribute("dest", uri + query);
		}
	}
}
