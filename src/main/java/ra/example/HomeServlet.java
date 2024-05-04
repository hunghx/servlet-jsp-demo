package ra.example;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "HomeServlet", value = "/HomeServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB - kích thước bộ nhớ tạm
        maxFileSize = 1024 * 1024 * 5,   // 5MB
        maxRequestSize = 1024 * 1024 * 5 * 10 // 50MB
)
public class HomeServlet extends HttpServlet {
    private static boolean status = false;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // lấy ra tham số gửi theo get
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        status = !status;
        request.setAttribute("status", status);
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.getRequestDispatcher("/public.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // lấy ra tham số gửi theo
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Part file = request.getPart("file");
        String uploadPath = "C:\\Users\\AD\\IdeaProjects\\Servlet\\src\\main\\webapp\\uploads";
        String name = file.getSubmittedFileName();
        // copy file đến vị trí chỉ định
        file.write(uploadPath + File.separator + name );
        request.setAttribute("username", username);
        request.setAttribute("url", name);
        request.setAttribute("password", password);
        request.getRequestDispatcher("/public.jsp").forward(request, response);

    }
}
 