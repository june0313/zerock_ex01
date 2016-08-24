package org.zerock.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
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
public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();

		// 로그인 안한 사용자는 로그인 페이지로 이동시킨다.
		if (null == session.getAttribute("login")) {
			log.info("current user is not logined..............");

			saveDest(request);

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
