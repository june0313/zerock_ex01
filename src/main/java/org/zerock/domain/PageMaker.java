package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

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
