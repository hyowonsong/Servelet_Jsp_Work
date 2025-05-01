package ex0417.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogTest {
	// log 사용하는 친구 누군지
	Log log = LogFactory.getLog(super.getClass());
	
	public static void main(String[] args) {
		System.out.println("LogTest 시작");
		
		new LogTest().test();
		
		System.out.println("LogTest 시작");
	}
	
	public void test() {
		log.trace("trace관련 로그");
		log.debug("debug관련 로그");
		log.info("info관련 로그");
		log.warn("warn관련 로그");
		log.error("error관련 로그");
		log.fatal("fatal관련 로그");
		
	}
}
