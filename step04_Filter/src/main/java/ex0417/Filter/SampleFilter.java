package ex0417.Filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class SampleFilter implements Filter {
	
	public SampleFilter() {
		System.out.println("SampleFilter constructor call");
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("SampleFilter init call");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// 사전처리
		System.out.println("SampleFilter의 사전 처리 중입니다.");
		
		// 다음 target 대상을 호출(filter or servlet or jsp);
		chain.doFilter(req, res);
		
		// 사후처리
		System.out.println("SampleFilter의 사후 처리 중입니다.");
	}
	
	@Override
	public void destroy() {
		System.out.println("SampleFilter destroy call");
	}
}
