package org.zerock.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.zerock.configuration.servlet.ServletContextConfiguration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * 이 클래스는 web.xml 의 역할을 대신하거나 보충한다.
 * WebApplicationInitializer 를 상속하면, 서블릿 컨테이너가 실행될 때 onStartup() 메소드가 자동으로 호출된다.
 * Servlet API 3.0 이상이 필요하다.
 *
 * @author wayne
 * @version 1.0
 */
public class Initializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		this.addRootContextLoaderListener(servletContext);
		this.addDispatcherServlet(servletContext);
		this.addUTF8CharacterEncodingFilter(servletContext);
	}

	/**
	 * RootContext 를 로딩하는 리스너를 등록한다
	 *
	 * @param servletContext 초기화할 ServletContext
	 */
	private void addRootContextLoaderListener(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(RootContextConfiguration.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));
	}

	/**
	 * DispatcherServlet 을 추가한다
	 *
	 * @param servletContext 초기화할 ServletContext
	 */
	private void addDispatcherServlet(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext servletApplicationContext = new AnnotationConfigWebApplicationContext();
		servletApplicationContext.register(ServletContextConfiguration.class);

		DispatcherServlet dispatcherServlet = new DispatcherServlet(servletApplicationContext);

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("appServlet", dispatcherServlet);
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

	/**
	 * UTF-8 CharacterEncodingFilter 를 추가한다
	 *
	 * @param servletContext 초기화할 ServletContext
	 */
	private void addUTF8CharacterEncodingFilter(ServletContext servletContext) {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		FilterRegistration.Dynamic filter = servletContext.addFilter("encoding", characterEncodingFilter);
		filter.addMappingForUrlPatterns(null, false, "/*");
	}
}
