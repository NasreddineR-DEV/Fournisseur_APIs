package ma.oncf.digital.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CORSFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
        	HttpServletResponse httpResponse = (HttpServletResponse) response;
	        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
	        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        	httpResponse.setHeader("Access-Control-Allow-Headers", "X-Auth-Token, Content-Type,Cache-Control,Authorization");
	        httpResponse.setHeader("Access-Control-Expose-Headers", "custom-header1, custom-header2");
        	httpResponse.setHeader("Access-Control-Allow-Credentials", "false");
	        httpResponse.setHeader("Access-Control-Max-Age", "4800");
	        chain.doFilter(request, response);
	}
	@Override
	public void init(FilterConfig filterConfig) {
	}
	@Override
	public void destroy() {
	}
} 