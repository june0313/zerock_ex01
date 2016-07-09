package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by wayne on 2016. 7. 9..
 *
 */
@Getter
@Setter
@ToString
public class SearchCriteria extends Criteria{
	private String searchType;
	private String keyword;
}
