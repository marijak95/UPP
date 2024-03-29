package upp.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;

public class Snippet {
	@Bean
	public Filter myFilter() {
	    return new Filter() {
	        @Override
	        public void init(FilterConfig filterConfig) throws ServletException {
	
	        }
	
	        @Override
	        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	            final HttpServletResponse res = (HttpServletResponse) servletResponse;
	            res.addHeader("Access-Control-Allow-Origin", "*");
	
	            filterChain.doFilter(servletRequest, servletResponse);
	        }
	
	        @Override
	        public void destroy() {
	
	        }
	    };
	}
}

