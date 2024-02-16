package com.hanul.finalb.common;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PageVO {


	private int pageList = 10;
	private int blockPage = 10;
	private int totalList;
	private int totalPage;
	private int totalBlock;
	private int beginList, endList;
	private int curBlock, curPage = 1;
	private int beginPage, endPage;
	private List<Object> list;
	private String search="all", keyword="";
	
	public void setTotalList(int totalList) {
		this.totalList = totalList;

		totalPage = totalList / pageList;
		if( totalList % pageList > 0 ) ++totalPage;
		

		totalBlock = totalPage / blockPage;
		if ( totalPage % blockPage > 0 ) ++totalBlock;
		
		

		endList = totalList - (curPage-1) * pageList;
		beginList = endList - (pageList-1);
		

		curBlock = curPage / blockPage;
		if ( curPage % blockPage > 0 ) ++curBlock;

		endPage = curBlock * blockPage;
		beginPage = endPage - (blockPage-1);
		

		if( totalPage < endPage ) endPage = totalPage;
		
		
	}

	
}
