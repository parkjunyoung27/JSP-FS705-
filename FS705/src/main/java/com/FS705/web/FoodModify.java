package com.FS705.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FS705.dao.FoodBoardDAO;
import com.FS705.dto.FoodBoardDTO;
import com.FS705.util.Util;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/foodModify")
public class FoodModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FoodModify() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	if(request.getParameter("bno") != null && Util.str2Int(request.getParameter("bno")) != 0) {
		//&& request.getSession().getAttribute("id")){
		// id = (String) request.getSession().getAttribute("id");
		String id = "an";
		FoodBoardDTO modifyImport = FoodBoardDAO.getInstance().modifyImport(Util.str2Int(request.getParameter("bno")), id);		
		RequestDispatcher rd = request.getRequestDispatcher("./foodModify.jsp");
		request.setAttribute("modifyImport", modifyImport);
		rd.forward(request, response);
	}else {
		response.sendRedirect("./error?code=foodModifyError1");
	}
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path = request.getServletContext().getRealPath("/");
		String savePath = path + "foodUpload/"; // food 파일 저장 경로
		int maxSize = 1024 * 1024 * 10; //파일 업로드 사이즈 설정
		
		MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		if(multi.getParameter("bno") != null && Util.str2Int(multi.getParameter("bno")) != 0) {
			//&& request.getSession().getAttribute("id")){
			// id = (String) request.getSession().getAttribute("id");
			String id = "an";
			int result = 0;
			int bno = Util.str2Int(multi.getParameter("bno"));
			String title = Util.replace(multi.getParameter("title"));
			String content = Util.replace(multi.getParameter("content"));
			String subCategory = multi.getParameter("subCategory");
//			String saveFile = null;
//			String thumbnail = null;
//			if(multi.getOriginalFileName("file1") != null && multi.getOriginalFileName("file1") != multi.getOriginalFileName("file2")) {
//				saveFile = multi.getFilesystemName("file1"); // 파일 저장 이름
//				
//				thumbnail = path + "foodThumbnail/";
//				BufferedImage inputImg = ImageIO.read(new File(savePath + saveFile));
//				
//				int width = 240;
//				int height = 180;
//				
//				String[] imgs = {"png", "gif", "jpg", "jpeg"};
//				for (String format : imgs) {
//					BufferedImage outputImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//					
//					Graphics2D gr2d = outputImg.createGraphics();
//					gr2d.drawImage(inputImg, 0, 0, width, height, null);
//					
//					//파일쓰기
//					File thumb = new File(thumbnail + saveFile);
//					FileOutputStream fos = new FileOutputStream(thumb);
//					ImageIO.write(outputImg, format, thumb);
//					fos.close();					
//				}
//			}
			FoodBoardDTO dto = new FoodBoardDTO();
			dto.setBno(bno);
			dto.setBtitle(title);
			dto.setBcontent(content);
			dto.setSubCategory(subCategory);
			dto.setId(id);			
			
			result = FoodBoardDAO.getInstance().modifyContent(dto);
			if(result == 1) {
//			RequestDispatcher rd = request.getRequestDispatcher("./foodDetail?bno="+bno);
//			rd.forward(request, response);
				response.sendRedirect("./foodDetail?bno="+bno);
			}else {
				response.sendRedirect("./error?code=foodModifyError2");
			}
		}else {
			response.sendRedirect("./error?code=foodModifyError3");
		}	
	}
}
