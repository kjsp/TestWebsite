package model;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Database Connection Pool: dbcp 는 미리 컨넥션을 생성해 빌려주고 반납받는 형식으로 
 * 							 시스템 성능을 향상
 * JNDI: lookup() -> WAS에 dbcp를 시작시 미리 로딩해놓고 
 * 					 dbcp의 특정이름(jdbc/myoracle)을 통해 검색 후 반환받아 사용 
 * 					 WAS의 설정 정보(XML)에 특정 DBCP 정보를 저장함으로 유지보수성 향상
 * @author KOSTA-00-13-014-USER
 *
 */
public class DataSourceManager {
	private static DataSourceManager instance = new DataSourceManager();
	private DataSource dataSource;
	private DataSourceManager() {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
			System.out.println("dbcp lookup:"+dataSource);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static DataSourceManager getInstance(){
		return instance;
	}
	
	
	public DataSource getDataSource() {
		return dataSource;
	}
}
