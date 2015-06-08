package model;
/**
 * 페이징 처리를 위한 비즈니스 로직을 처리하는 객체
 * @author kosta-00-khj
 *
 */
public class PagingBean {
	/**
	 * 현재 페이지정보
	 */
	private int nowPage=1;
	/**
	 * 한 페이지에서 보여줄 게시물 수
	 */
	private int numberOfContentsPerPage=CommonConstants.CONTENT_NUMBER_PER_PAGE; //5
	/**
	 * 페이지 그룹당 페이지 수
	 */
	private int numberOfPageGroup=CommonConstants.PAGEGROUP_NUMBER_PER_PAGE;//4
	/**
	 * db 에 저장된 총 게시물수 :  db 에서 실시간 조회해야 한다.
	 */
	private int totalContents;
	/**
	 * 모델의 서비스 계층 즉 BookService 에서 호출해 생성한다.
	 * Dao 를 이용해 db에서 조회한 현재 총 게시물 수와 현재 페이지 정보를
	 * 클라이언트로부터 받아와 생성한다.
	 * @param nowPage
	 * @param totalContetnts
	 */
	public PagingBean(int nowPage, int totalContents) {
		super();
		this.nowPage = nowPage;
		this.totalContents = totalContents;
	}
	public int getNowPage(){
		return nowPage;
	}
	/**
	 * 총페이지수 리턴한다.
	 * 1. 전체 게시물 수 % 한페이지에서 보여질 게시물수 => 0이면
	 * 	둘을 나눈 값이 총페이지수
	 * 2. 전체 게시물 수 % 한페이지에서 보여질 게시물수 => 0보다 크면
	 * 	둘을 나눈 값에 + 1 을 한 값이 총 페이지 수
	 * 	게시물 수 1 2 3 4 5 6 7 8 9 10 11 12 13
	 * 	1페이지 1 2 3 
	 * 	2페이지 4 5 6 
	 * 	3페이지 7 8 9 
	 * 	4페이지 10 11 12
	 * 	5페이지 13
	 * @return
	 */
	public int getTotalPage(){
		int num=totalContents%numberOfContentsPerPage;
		int totalPage=0;
		if(num==0){
			totalPage=totalContents/numberOfContentsPerPage;
		}else{
			totalPage=totalContents/numberOfContentsPerPage+1;
		}
		return totalPage;
	}
	/**
	 * 총 페이지 그룹 수를 리턴
	 * 1. 총페이지수 % PageGroup 당 페이지수 =>0
	 * 	두 값을 나눈 결과가 총 페이지 그룹 수
	 * 2. 총페이지수 % PageGroup 당 페이지수 =>0 보다 크면
	 * 	두 값을 나눈 결과가 총 페이지 그룹 수 +1
	 * ex)
	 * 		게시물 13개 
	 * 		페이지(3개 기준) 1 2 3 4 5
	 * 		페이지그룹(4페이지 기준)	1그룹(1 ,2 ,3 ,4) 2그룹(5)
	 * 
	 * 		게시물수 29개
	 * 		페이지수 10 페이지
	 * @return
	 */
	public int getTotalPageGroup(){
		int num=getTotalPage()%numberOfPageGroup;
		int totalPageGroup=0;
		if(num==0)
			totalPageGroup=getTotalPage()/numberOfPageGroup;
		else if(num>0)
			totalPageGroup=getTotalPage()/numberOfPageGroup+1;
		
		return totalPageGroup;
	}
	/**
	 * 현재 페이지가 속한 페이지 그룹번호(몇번째 페이지 그룹인지)를 반환
	 * 1. 현재페이지% PageGroup 당 페이지수 => 0 이면
	 * 	나눈 값이 현재 페이지 그룹
	 * 2. 현재페이지% PageGroup 당 페이지수 => 0 보다 크면
	 * 	나눈 값+1이 현재 페이지 그룹
	 * 
	 * 총게시물수 28 개 
	 * 총페이지수 10페이지
	 * 총페이지 그룹수 3그룹
	 * @return
	 */
	public int getNowPageGroup(){
		int nowPageGroup=0;
		int num=nowPage%numberOfPageGroup;
		if(num==0)
			nowPageGroup=nowPage/numberOfPageGroup;
		else if(num>0)
			nowPageGroup=nowPage/numberOfPageGroup+1;
		return nowPageGroup;
	}
	/**
	 * 현재 페이지가 속한 페이지 그룹의 시작 페이지 번호를 반환
	 * 1. PageGroup 당 페이지수 (4) *(현재페이지그룹-1) + 1 결과가 첫 시작페이지 번호
	 * 	(참고 4*2 =8 2번째 그룹의 마지막 페이지 번호를 8이고 여기서 +1 하면 3그룹의 시작번호
	 * 2. 위 계산결과 0인 경우 1을 리턴한다.
	 * @return
	 */
	public int getStartPageOfPageGroup(){
		return this.numberOfPageGroup*(this.getNowPageGroup()-1)+1;
	}
	/**
	 * 현재 페이지가 속한 페이지 그룹의 마지막 페이지 번호를 반환
	 * 1. 현재 페이지가 속한 페이지 그룹 * 페이지 그룹당 페이지 수(4)
	 * 2. 만약 위 연산 결과가 총페이지 수보다 클 경우
	 * 	총 페이지 수가 마지막 번호가 된다.
	 * 	page 1 2 3 4| 5 6 7
	 * 				1그룹	2그룹
	 * @return
	 */
	public int getEndPageOfPageGroup(){
		int num=this.getNowPageGroup()*this.numberOfPageGroup;
		if(this.getTotalPage()<num){//위 연산 겨로가가 총페이지 수보다 크면
			num=this.getTotalPage();// 총페이지수가 마지막 번호가 된다.
		}
		return num;
	}
	public boolean isPreviousPageGroup(){
		boolean result=false;
		if(this.getNowPageGroup()>1){
			result=true;
		}
		return result;
	}
	public boolean isNextPageGroup(){
		boolean result=false;
		if(this.getNowPageGroup()<this.getTotalPageGroup()){
			result=true;
		}
		return result;
	}
/*public static void main(String args[]){
		PagingBean pb=new PagingBean(7,29);
		System.out.println("총페이지수:"+pb.getTotalPage());
		System.out.println("총페이지그룹수:"+pb.getTotalPageGroup());
		System.out.println("현재페이지의 그룹번호:"+pb.getNowPageGroup());
		System.out.println("현재페이지 그룹의 시작페이지 번호:"+pb.getStartPageOfPageGroup());
		System.out.println("현재페이지 그룹의 마지막 페이지 번호:" +pb.getEndPageOfPageGroup());
		System.out.println("이전 페이지그룹의 존재여부:"+pb.isPreviousPageGroup());
		System.out.println("다음 페이지그룹의 존재여부:"+pb.isNextPageGroup());
	
}*/
}
