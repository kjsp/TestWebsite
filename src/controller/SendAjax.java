package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendAjax {
	public static void  printAjax(HttpServletRequest request,
			HttpServletResponse response, Object json) throws IOException {
		// 응답할 문서 타입을 xml로 지정
		response.setContentType("text/html;charset=utf-8");
		// 브라우저 캐쉬 기능을 끄게 한다.
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
	}
}
