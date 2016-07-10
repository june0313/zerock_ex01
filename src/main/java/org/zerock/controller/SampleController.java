package org.zerock.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;

import java.util.List;
import java.util.Map;

/**
 * RestController 테스트
 * @author wayne
 * @version 1.0
 */
@RestController
@RequestMapping("/sample")
public class SampleController {

	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello world";
	}

	@RequestMapping("/sendVo")
	public SampleVO sendVO() {
		SampleVO sampleVO = new SampleVO();
		sampleVO.setFirstName("홍길동");
		sampleVO.setLastName("hong");
		sampleVO.setMno(123);
		return sampleVO;
	}

	@RequestMapping("/sendList")
	public List<SampleVO> sendList() {
		List<SampleVO> list = Lists.newArrayList();
		for (int i = 0; i < 10; i++) {
			SampleVO vo = new SampleVO();
			vo.setFirstName("wayne");
			vo.setLastName("bruce");
			vo.setMno(i);
			list.add(vo);
		}
		return list;
	}

	@RequestMapping("/sendMap")
	public Map<Integer, SampleVO> sendMap() {
		Map<Integer, SampleVO> map = Maps.newHashMap();
		for (int i = 0; i < 10; i++) {
			SampleVO vo = new SampleVO();
			vo.setFirstName("wayne");
			vo.setLastName("bruce");
			vo.setMno(i);
			map.put(i, vo);
		}
		return map;
	}

	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendListAuth() {
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping("/sendErrorNot")
	public ResponseEntity<List<SampleVO>> sendListNot() {
		return new ResponseEntity<>(sendList(), HttpStatus.NOT_FOUND);
	}

}
