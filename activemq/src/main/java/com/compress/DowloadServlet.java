package com.compress;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件下载
 */
@WebServlet("/dowloadServlet")
public class DowloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获取文件名
		String filename = request.getParameter("name");
		// 防止读取name名乱码
		// filename = new String(filename.getBytes("iso-8859-1"), "utf-8");
		// 在控制台打印文件名
		System.out.println("文件名：" + filename);
//		response.setCharacterEncoding("UTF-8");
//		// 设置文件MIME类型
//		response.setContentType(getServletContext().getMimeType(filename));
//		// 设置Content-Disposition
//		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + URLEncoder.encode(filename,"UTF-8"));
		response.setContentType("application/octet-stream");
		response.setCharacterEncoding("UTF-8");
		
		
		// 获取要下载的文件绝对路径，我的文件都放到WebRoot/download目录下
		ServletContext context = this.getServletContext();
		String fullFileName = context.getRealPath("/upload/" + filename);

		// 输入流为项目文件，输出流指向浏览器
		InputStream is = new FileInputStream(fullFileName);
		ServletOutputStream os = response.getOutputStream();

		/*
		 * 设置缓冲区 is.read(b)当文件读完时返回-1
		 */
		int len = -1;
		byte[] b = new byte[1024];
		while ((len = is.read(b)) != -1) {
			os.write(b, 0, len);
		}
		// 关闭流
		is.close();
		os.close();
	}

}
