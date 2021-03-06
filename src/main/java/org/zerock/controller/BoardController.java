package org.zerock.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;
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

	@RequestMapping(value = "/listCri", method = RequestMethod.GET)
	public void listAll(Criteria criteria, Model model) throws Exception {
		log.info("show list page with criteria ..... ");
		model.addAttribute("list", boardService.listCriteria(criteria));
	}

	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPage(Criteria criteria, Model model) throws Exception {
		log.info(criteria.toString());

		model.addAttribute("list", boardService.listCriteria(criteria));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(criteria);
		pageMaker.setTotalCount(boardService.listCountCriteria(criteria));

		model.addAttribute("pageMaker", pageMaker);
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception {
		model.addAttribute(boardService.read(bno));
	}

	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		model.addAttribute(boardService.read(bno));
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes attributes) throws Exception {
		boardService.remove(bno);

		attributes.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/listAll";
	}

	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, Criteria cri, RedirectAttributes rttr) throws Exception {
		boardService.remove(bno);

		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/listPage";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(int bno, Model model) throws Exception {
		model.addAttribute(boardService.read(bno));
	}

	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyPagingGET(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		model.addAttribute(boardService.read(bno));
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardVO board, RedirectAttributes attributes) throws Exception {
		log.info("mod post ..... ");

		boardService.modify(board);
		attributes.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/listAll";
	}

	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagingPOST(BoardVO board, Criteria cri, RedirectAttributes rttr) throws Exception {
		boardService.modify(board);

		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/listPage";
	}
}