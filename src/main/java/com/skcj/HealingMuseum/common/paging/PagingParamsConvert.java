package com.skcj.HealingMuseum.common.paging;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.skcj.HealingMuseum.common.TSData;
import com.skcj.HealingMuseum.common.paging.PaginationInfo;

@Component
public class PagingParamsConvert {
	private static final Logger logger = LoggerFactory.getLogger(PagingParamsConvert.class);
	
	private PaginationInfo pageInfo;

	private int pageIndex = 1;
	private int pageUnit = 10;
	private int pageSize = 10;

	private static int configPageUnit = 0;
	private static int configPageSize = 0;

	@Value("#{config['pagination.pageUnit']}")
	public void setPrivatePageUnit(String val)
	{
		configPageUnit = Integer.parseInt(val);
	}

	@Value("#{config['pagination.pageSize']}")
	public void setPrivatePageSize(String val)
	{
		configPageSize = Integer.parseInt(val);
	}

	public PagingParamsConvert(){
		this.pageInfo = new PaginationInfo();
	}

	public TSData convertPagingParams(TSData reqParams){

		if(StringUtils.isBlank(reqParams.getString("pageIndex"))) {
			reqParams.set("pageIndex", pageIndex);
		}

		if(StringUtils.isBlank(reqParams.getString("pageUnit"))) {
			if(configPageUnit <= pageUnit) {
				reqParams.set("pageUnit", pageUnit);
			}
			else {
				reqParams.set("pageUnit", configPageUnit);
			}
		}

		if(StringUtils.isBlank(reqParams.getString("pageSize"))) {
			if(configPageSize <= pageSize) {
				reqParams.set("pageSize", pageSize);
			}
			else {
				reqParams.set("pageSize", configPageSize);
			}
		}

		pageInfo.setCurrentPageNo(reqParams.getInt("pageIndex"));
		pageInfo.setRecordCountPerPage(reqParams.getInt("pageUnit"));
		pageInfo.setPageSize(reqParams.getInt("pageSize"));
		
		reqParams.set("firstIndex", Integer.toString(pageInfo.getFirstRecordIndex()));
		reqParams.set("lastIndex", Integer.toString(pageInfo.getLastRecordIndex()));
		reqParams.set("recordCountPerPage", Integer.toString(pageInfo.getRecordCountPerPage()));	

		logger.debug("================ PagingParams ========================");
		logger.debug("" + reqParams);
		
		return reqParams;
	}

	public PaginationInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PaginationInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
