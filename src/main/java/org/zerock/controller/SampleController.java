package org.zerock.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j2;

@RestController //rest방식의 컨트롤러로 동작함(get/post/put/delete)
@RequestMapping("/rest") //http://localhost:80/rest
@Log4j2
public class SampleController {
	// RestController 는 jsp와 달리 순수한 데이터를 반환하는 형태
	// 다양한 형태의 포맷으로 전송할 수 있다. (주로 json, xml)
	
	@GetMapping(value = "/getText", produces="text/plain; charset=UTF-8") // uri : rest/getText
	public String getText() {
		log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세요";
	}
	
	@GetMapping(value = "/getSample", 
			produces = {MediaType.APPLICATION_PROBLEM_JSON_VALUE,
						MediaType.APPLICATION_PROBLEM_XML_VALUE})
	public SampleVO getSample() {
		//getSample 메서드가 실행되면 객체가 리턴됨
		return new SampleVO(112, "김", "수영");
	}
	
	@GetMapping(value = "/getSample2")
	public SampleVO getSample2() {
		//getSample 메서드가 실행되면 객체가 리턴됨
		return new SampleVO(113, "김", "수영");
	}
	
	@GetMapping(value = "/getList") //uri : rest/getList
	public List<SampleVO> getList() {
		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i+"First", i+"Last")).collect(Collectors.toList());
	} //1~10까지 반복하며 객체를 생성하는데 컬렉션 list에 넣는다.
	
	@GetMapping(value = "/check", params = {"height", "weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight) {
		
		SampleVO vo = new SampleVO(0, ""+height, ""+weight);
		ResponseEntity<SampleVO> result = null;
		if(height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
			// height < 150 이면 true 일때 처리 (BAD_GATEWAY : 500에러)
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
			//정상(200)
		}
		return result;
	}
	
	@GetMapping("/product/{cat}/{pid}") // uri : /product/bags/1234
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") Integer pid) {
													//bags                               1234
		return new String[] {"category : " + cat, "productID : " + pid};
	}
	
	@PostMapping("/ticket") //post 사용 이유! : 모든 api가 body를 통해 값이 들어오기 때문에!
	public Ticket convert(@RequestBody Ticket ticket) {
		log.info("conver 메서드 실행중... ticket : " + ticket);
		return ticket; //외부에서 body로 들어온 값을 내가 가진 객체로 return 한다.
		//테스트는 test영역에서 보냄
	}
	
	
	
	
}
