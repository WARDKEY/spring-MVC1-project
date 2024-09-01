package hello.springmvc.basic.request;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RequestBodyStringController {

	/**
	 * InputStream(Reader): HTTP 요청 메시지 바디의 내용을 직접 조회
	 * * OutputStream(Writer): HTTP 응답 메시지의 바디에 직접 결과 출력
	 */
	@PostMapping("/request-body-string-v1")
	public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 스트림은 항상 바이트코드이며 바이트코드로 문자를 받을 경우 어떤 인코디으로 문자를 받을지 지정해줘야 함
		ServletInputStream inputStream = request.getInputStream();
		String messageBody  = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

		log.info("messageBody={}", messageBody);

		response.getWriter().write("ok");
	}

	/**
	 * HttpEntity: HTTP header, body 정보를 편리하게 조회
	 * - 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
	 * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용 *
	 * 응답에서도 HttpEntity 사용 가능
	 * - 메시지 바디 정보 직접 반환(view 조회X)
	 * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
	 * */
	@PostMapping("/request-body-string-v2")
	public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

		String messageBody  = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		log.info("messageBody={}", messageBody);
		responseWriter.write("ok");
	}

	@PostMapping("/request-body-string-v3")
	public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
		// HttpMessageConverter가 동작하여 httpBody에 있는 것을  String으로 바꿔줌

		// 변환된 httpBody의 값을 꺼냄
		String messageBody = httpEntity.getBody();

		log.info("messageBody={}",messageBody);

		// 반환으로 HttpEntity의 값을 반환 받는다.r
		return new HttpEntity<>("ok");
	}

	/**
	 * @RequestBody
	 * - 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
	 * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용 *
	 * @ResponseBody
	 * - 메시지 바디 정보 직접 반환(view 조회X)
	 * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
	 * */
	@ResponseBody
	@PostMapping("/request-body-string-v4")
	public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {
		log.info("messageBody={}",messageBody);

		return "ok";
	}

}
