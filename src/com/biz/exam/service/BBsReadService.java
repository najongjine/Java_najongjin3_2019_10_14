package com.biz.exam.service;

import java.util.List;

import com.biz.exam.domain.BBsVO;

public interface BBsReadService {
	public void readBBS(String bbsFile);
	public List<BBsVO> getBBsList();
}
