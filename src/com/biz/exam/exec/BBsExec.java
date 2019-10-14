package com.biz.exam.exec;

import java.util.List;
import java.util.Scanner;

import com.biz.exam.domain.BBsVO;
import com.biz.exam.service.BBsReadService;
import com.biz.exam.service.BBsReadServiceImp;
import com.biz.exam.service.BBsViewService;
import com.biz.exam.service.BBsViewServiceImp;

public class BBsExec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String bbsFile="src/com/biz/exam/bbs.txt";
		BBsReadService bRead=new BBsReadServiceImp();
		BBsViewService bView=new BBsViewServiceImp();
		Scanner scanner=new Scanner(System.in);
		
		//파일로부터 값을 읽기위한 method 호출
		bRead.readBBS(bbsFile);

		//bRead 객체 안의 있는 bbsList를 읽어옴
		List<BBsVO> bbsList=bRead.getBBsList();
		
		//읽어온 bbsList를 bView안의 있는 리스트에 주입
		bView.setBBsList(bbsList);
		
		header();
		String strMenu="";
		while(true) {
			menu();
			strMenu=scanner.nextLine();
			
			if(strMenu.equalsIgnoreCase("1")) {
				//전체조회
				bView.viewBBS();
			}
			else if(strMenu.equalsIgnoreCase("2")) {
				//제목으로 조회
				System.out.print("검색하고 싶으신 제목 입력>> ");
				String subject=scanner.nextLine();
				bView.viewBBS(subject);
			}
			else if(strMenu.equalsIgnoreCase("3")) {
				//시작, 끝 날짜로 조회
				System.out.print("검색하고 싶은 시작 날짜 >>");
				String sDate=scanner.nextLine();
				System.out.print("검색하고 싶은 마지막 날짜 >>");
				String eDate=scanner.nextLine();
				bView.viewBBS(Integer.valueOf(sDate), Integer.valueOf(eDate));
			}
			else if(strMenu.equalsIgnoreCase("4")) {
				System.out.print("오름차순 조회수 정렬: true입력, 내림차순 조회수 정렬"
						+ ": false입력 >> ");
				String strSort=scanner.nextLine();
				if(strSort.equalsIgnoreCase("true") | strSort.equalsIgnoreCase("false")) {
					boolean sort=Boolean.valueOf(strSort);
					bView.viewBBS(sort);
					bView.viewBBS();
				}
				else {
					System.out.println("잘못된 문자를 입력하셨습니다.");
				}
			}
			else if(strMenu.equalsIgnoreCase("0")) {
				System.out.println("프로그램 종료.");
				break;
			}
			else {
				System.out.println("잘못된 키를 입력하셨습니다!");
			}
		}
	}
	private static void header() {
		System.out.println("==============================");
		System.out.println("게시판 관리 시스템 v1");
		System.out.println("=================================");
	}
	private static void menu() {
		System.out.println("1.전체\t2.제목\t3.날짜\t4.조회순으로 정렬\t0.종료");
		System.out.print("업무선택 >>");
	}
}
;