package ra.example.controller;

import ra.example.service.UploadService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UploadServlet", value = "/UploadServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB - kích thước bộ nhớ tạm
        maxFileSize = 1024 * 1024 * 5,   // 5MB
        maxRequestSize = 1024 * 1024 * 5 * 10 // 50MB
)
public class UploadServlet extends HttpServlet {
    private  static  final UploadService upload = new UploadService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("file");
        String url = null;
        if (part.getSize()!=0){
          url = upload.uploadFileToServer(part,getServletContext());
        }
        System.out.println(url);
        request.setAttribute("url",url);
        request.getRequestDispatcher("/image.jsp").forward(request,response);
    }
}
 