package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by Wayne on 2016. 7. 6..
 */
@Getter
@ToString
public class PageMaker {
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private Criteria cri;

	private static final int DISPLAY_PAGE_NUM = 10;

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;

		calcData();
	}

	public String makeQuery(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
			.queryParam("page", page)
			.queryParam("perPageNum", cri.getPerPageNum())
			.build();

		return uriComponents.toString();
	}

	public String makeSearch(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
			.queryParam("page", page)
			.queryParam("perPageNum", cri.getPerPageNum())
			.queryParam("searchType", ((SearchCriteria)cri).getSearchType())
			.queryParam("keyword", ((SearchCriteria)cri).getKeyword())
			.build();

		return uriComponents.toString();
	}

	private void calcData() {
		endPage = (int) (Math.ceil(cri.getPage() / (double) DISPLAY_PAGE_NUM) * DISPLAY_PAGE_NUM);
		startPage = endPage - DISPLAY_PAGE_NUM + 1;

		int tempEndPage = (int) Math.ceil(totalCount / (double) cri.getPerPageNum());
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}

		prev = startPage != 1;
		next = (endPage * cri.getPerPageNum()) < totalCount;
	}
}
