package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by Wayne on 2016. 7. 6..
 * 리스트 검색 기준에 대한 정보를 갖고있는 클래스
 */
@Getter
@ToString
public class Criteria {
	private int page;
	private int perPageNum;

	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}

	public void setPage(int page) {
		if (page < 0) {
			this.page = 1;
			return;
		}

		this.page = page;
	}

	public void setPerPageNum(int perPageNum) {
		if (perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}

		this.perPageNum = perPageNum;
	}

	// method for Mybatis SQL Mapper
	public int getPageStart() {
		return (this.page - 1) * perPageNum;
	}

}
