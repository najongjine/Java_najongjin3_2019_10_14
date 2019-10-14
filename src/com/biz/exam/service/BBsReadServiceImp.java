package com.biz.exam.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.biz.exam.domain.BBsVO;
import com.biz.exam.util.BBsUtil;

public class BBsReadServiceImp implements BBsReadService {
	//BBsVO 를 담을 리스트 선언
	List<BBsVO> bbsList=null;
	
	@Override
	public void readBBS(String bbsFile) {
		//bbsList를 사용할수 있게 인스턴스 화
		bbsList=new ArrayList<BBsVO>();
		
		//파일을 읽기위한 파일 리더와 버퍼 선언
		FileReader fr=null;
		BufferedReader buffer=null;
		
		try {
			//파일리더와 버퍼를 사용하기위해 인스턴스 화
			fr=new FileReader(bbsFile);
			buffer=new BufferedReader(fr);
			
			while(true) {
				//파일안의 내용이 없을때까지 루프 돌음
				
				//버퍼로 파일의 내용을 한줄씩 읽은후 reader 변수에 복사
				String reader=buffer.readLine();
				if(reader==null)break;
				if(reader.length()<1) continue;
				
				//읽은 문자열형 자료를 : 로 구분하여 나눈후, 배열에 담음
				String[] _str=reader.split(":");
				
				//bbsVO에 값을 입력하기위해 bbsVO 객체 생성과 초기화.
				BBsVO bbsVO=new BBsVO();
				//객체안의 필드 변수에 값들을 설정.
				bbsVO.setAuth(_str[BBsUtil.AUTH]);
				bbsVO.setCount(Integer.valueOf(_str[BBsUtil.COUNT]));
				bbsVO.setDate(Integer.valueOf(_str[BBsUtil.DATE]));
				bbsVO.setSeq(Integer.valueOf(_str[BBsUtil.SEQ]));
				bbsVO.setSubject(_str[BBsUtil.SUBJECT]);
				bbsVO.setText(_str[BBsUtil.TEXT]);
				
				//생성되고 값이 설정된 bbsVO를 리스트에 추가
				bbsList.add(bbsVO);
			}
			
			buffer.close();
			fr.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<BBsVO> getBBsList() {
		// 만약 리스트가 생성이 안됬으면 메세지 출력.
		if(bbsList==null) {
			System.out.println("bbsList에 자료가 하나도 없습니다!");
			return bbsList;
		}
		return bbsList;
	}

}
