package com.biz.exam.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.biz.exam.domain.BBsVO;

public class BBsViewServiceImp implements BBsViewService {
	List<BBsVO> bbsList=null;
	
	@Override
	public void setBBsList(List<BBsVO> bbsList) {
		// bbsList를 외부로부터 주입받음.
		this.bbsList=bbsList;
	}

	@Override
	public void viewBBS() {
		// bbsList에 값이 주입 안됬으면 메세지 출력.
		if(isBBsListNull()) {
			System.out.println("bbsList가 초기화 되지 않았습니다!");
			return;
		}
		System.out.println("----------------------------------------------");
		System.out.println("순번\t작성자\t작성일자\t제목\t내용\t조회수");
		System.out.println("--------------------------------------------");
		for(BBsVO bbsVO:bbsList) {
			//bbsList안의 객체를 bbsVO에 담아서 출력
			System.out.print(bbsVO.getSeq()+"\t");
			System.out.print(bbsVO.getAuth()+"\t");
			System.out.print(bbsVO.getDate()+"\t");
			System.out.print(bbsVO.getSubject()+"\t");
			System.out.print(bbsVO.getText()+"\t");
			System.out.print(bbsVO.getCount()+"\n");
		}
		System.out.println("--------------------------------------------");
	}// viewBBS end

	@Override
	public void viewBBS(String subject) {
		// // bbsList에 값이 주입 안됬으면 메세지 출력.
		if(isBBsListNull()) {
			System.out.println("bbsList가 초기화 되지 않았습니다!");
			return;
		}
		System.out.println("------------제목검색: "+subject+"-------------------------");
		System.out.println("순번\t작성자\t작성일자\t제목\t내용\t조회수");
		System.out.println("--------------------------------------------");
		for(BBsVO bbsVO:bbsList) {
			//bbsList안의 객체를 bbsVO에 담음
			
			if(bbsVO.getSubject().contains(subject)) {
				//bbsVO 안의 subject 문자열에 매게변수로 받은 subject
				//문자열이 포함되 있으면 해당 vo의 값들을 출력
				System.out.print(bbsVO.getSeq()+"\t");
				System.out.print(bbsVO.getAuth()+"\t");
				System.out.print(bbsVO.getDate()+"\t");
				System.out.print(bbsVO.getSubject()+"\t");
				System.out.print(bbsVO.getText()+"\t");
				System.out.print(bbsVO.getCount()+"\n");
			}
		}
		System.out.println("--------------------------------------------");
	}

	@Override
	public void viewBBS(int sDate, int eDate) {
		// bbsList에 값이 주입 안됬으면 메세지 출력.
		if(isBBsListNull()) {
			System.out.println("bbsList가 초기화 되지 않았습니다!");
			return;
		}
		System.out.println("------------날짜: "+sDate+"~"+eDate+" 검색---------------------");
		System.out.println("순번\t작성자\t작성일자\t제목\t내용\t조회수");
		System.out.println("--------------------------------------------");
		for(BBsVO bbsVO:bbsList) {
			//bbsList안의 객체를 bbsVO에 담음
			
			if(bbsVO.getDate()>=sDate && bbsVO.getDate()<=eDate) {
				//bbsVO 안의 date 정수를 기준으로 매게변수로 받은 sDate 과 eDate
				//의 값 사이에 있거나 값이 일치하면 해당 vo의 값들을 출력
				System.out.print(bbsVO.getSeq()+"\t");
				System.out.print(bbsVO.getAuth()+"\t");
				System.out.print(bbsVO.getDate()+"\t");
				System.out.print(bbsVO.getSubject()+"\t");
				System.out.print(bbsVO.getText()+"\t");
				System.out.print(bbsVO.getCount()+"\n");
			}
		}
		System.out.println("--------------------------------------------");
	}//viewBBs end

	@Override
	public void viewBBS(boolean sort) {
		// bbsList에 값이 주입 안됬으면 메세지 출력.
		if(isBBsListNull()) {
			System.out.println("bbsList가 초기화 되지 않았습니다!");
			return;
		}
		if(sort) {
			//sort method 내에 정렬하고자 하는 list를 매개변수로 주고,
			//Comparator inner class를 사용.
			Collections.sort(bbsList, new Comparator<BBsVO>() {

				@Override
				//정렬하고자 하는 기준 객체 type을 지정.
				public int compare(BBsVO o1, BBsVO o2) {
					// 정렬하고자 하는 객체의 기준값을 지정.
					return o1.getCount()-o2.getCount();
				}
			});
		} else {
			Collections.sort(bbsList, new Comparator<BBsVO>() {

				@Override
				public int compare(BBsVO o1, BBsVO o2) {
					// TODO Auto-generated method stub
					return o2.getCount()-o1.getCount();
				}
			});
		}

	}//sort end
	private boolean isBBsListNull() {
		//bbsList가 값이 잘 주입 되었는지 검사.
		if(bbsList==null) return true;
		else return false;
	}
}
