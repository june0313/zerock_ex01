package org.zerock.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

/**
 * Created by wayne on 2016. 7. 4..
 *
 */
@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(BoardVO board, Model model) {
		log.info("register get ..... ");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO board, RedirectAttributes attributes) throws Exception {
		log.info("register post ..... ");
		log.info(board.toString());

		boardService.regist(board);

		// Spring의 RedirectAttributes 객체는 리다이렉트 시점에 한 번만 사용되는 데이터를 전송할 수 있는
		// addFlashAttributes() 라는 메소드를 지원한다.
		attributes.addFlashAttribute("msg", "SUCCESS");

		// return "/board/success";
		return "redirect:/board/listAll";
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		log.info("show all list ..... ");
		model.addAttribute("list", boardService.listAll());
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception {
		model.addAttribute(boardService.read(bno));
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes attributes) throws Exception {
		boardService.remove(bno);

		attributes.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/listAll";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(int bno, Model model) throws Exception {
		model.addAttribute(boardService.read(bno));
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardVO board, RedirectAttributes attributes) throws Exception {
		log.info("mod post ..... ");

		boardService.modify(board);
		attributes.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/listAll";
	}
}