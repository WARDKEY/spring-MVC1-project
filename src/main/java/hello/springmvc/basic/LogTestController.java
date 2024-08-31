package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

// @Slf4j
// 그냥 @Controller로 하면 View 이름이 반환되지만
// @RestController를 사용하면 rest-api를 사용하며 String을 반환할 수 있다.
@RestController
public class LogTestController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@RequestMapping("/log-test")
	public String logTest(){
		String name = "spring";

		System.out.println("name = " + name);
		log.trace(" trace log={}", name);
		log.debug(" debug log={}", name);
		log.info(" info log={}", name);
		log.warn(" warn log={}", name);
		log.error(" error log={} ", name);

		return "ok";
	}
}
