package controller;
/**
 * 컨트롤러 객체 생성을 전담하는 클래스
 * 사용 계층(DispatcherServlet)과 컨트롤러 로직 구현 계층(Controller)의 결합도를 낮추는 역활
 * @author KOSTA-00-13-014-USER
 *
 */
public class HandlerMapping {
	private static HandlerMapping instance = new HandlerMapping();
	
	private HandlerMapping(){}
	
	public static HandlerMapping getInstance(){
		return instance;
	}
	
	public Controller create(String command){
		Controller controller = null;
		try {
			String controllerName = "controller."+command.substring(0, 1).toUpperCase()+command.substring(1)+"Controller";
			controller = (Controller) Class.forName(controllerName).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			System.out.println("메뉴가 존재하지 않습니다.");
			e.printStackTrace();
		}
		
		
		return controller;
	}
}
