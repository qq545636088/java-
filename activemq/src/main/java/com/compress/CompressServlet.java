package com.compress;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 图片压缩servlet
 */
@SuppressWarnings("serial")
@WebServlet("/compressServlet")
public class CompressServlet extends HttpServlet {

	// 上传文件存储目录
	private static final String UPLOAD_DIRECTORY = "upload";

	// 上传配置
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	public CompressServlet() {
		super();
		System.out.println("已经进入图片压缩Servlet");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// 设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// 设置最大请求值 (包含文件和表单数据)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// 中文处理
		upload.setHeaderEncoding("UTF-8");

		// 构造临时路径来存储上传的文件
		// 这个路径相对当前应用的目录
		String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;

		// 如果目录不存在则创建
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			// 解析请求的内容提取文件数据
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);
			String newFilePath=null;
			String filePath=null;
			DecimalFormat df = new DecimalFormat("#.##");
			String width = null;
			String height= null;
			if (formItems != null && formItems.size() > 0) {
				// 迭代表单数据
				for (FileItem item : formItems) {
					// 处理不在表单中的字段
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						 filePath = uploadPath + File.separator + fileName;
						File storeFile = new File(filePath);
						// 在控制台输出文件的上传路径
						System.out.println(filePath);
						// 保存文件到硬盘
						item.write(storeFile);
						request.setAttribute("message", "文件上传成功!");
						request.setAttribute("filename", fileName);
						request.setAttribute("fileSize", df.format((double) storeFile.length() / 1024));
						request.setAttribute("fileUrl", uploadPath + "/" + fileName);
						newFilePath = uploadPath + File.separator
								+ fileName.substring(0, fileName.lastIndexOf(".")) + "mark"
								+ fileName.substring(fileName.lastIndexOf("."), fileName.length());
					}else if("width".equals(item.getFieldName())){
						width = new String(item.getString("UTF-8"));
						if (width == null || "".equals(width)) {
							width= "100";
						}
					}else if ("height".equals(item.getFieldName())){
						height = new String(item.getString("UTF-8"));
						if (height == null || "".equals(height)) {
							height="100";
						}
					}
				}
			}
			System.out.println("开始压缩文件。。。");
			CompressUtil.reduceImg(filePath, newFilePath, Integer.valueOf(width),Integer.valueOf(height), null);
			File distfile = new File(newFilePath);
			request.setAttribute("newFileName", distfile.getName());
			request.setAttribute("newFileSize", df.format((double) distfile.length() / 1024));
			request.setAttribute("newFileUrl", uploadPath + "/" + distfile.getName());
			System.out.println("压缩文件完成！");
		} catch (Exception ex) {
			ex.printStackTrace();
			request.setAttribute("message", "错误信息: " + ex.getMessage());
		}
		
		// 跳转到 message.jsp
		request.getServletContext().getRequestDispatcher("/uploadsuccess.jsp").forward(request, response);
	}
}
