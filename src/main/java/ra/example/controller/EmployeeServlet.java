package ra.example.controller;

import ra.example.dto.request.EmployeeRequest;
import ra.example.service.IEmployeeService;
import ra.example.service.impl.EmployeeServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "EmployeeServlet", value = "/EmployeeServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB - kích thước bộ nhớ tạm
        maxFileSize = 1024 * 1024 * 5,   // 5MB
        maxRequestSize = 1024 * 1024 * 5 * 10 // 50MB
)
public class EmployeeServlet extends HttpServlet {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final IEmployeeService employeeService = new EmployeeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action!=null){
            switch (action){
                case "LIST" :
                    request.setAttribute("list",employeeService.findAll());
                    request.getRequestDispatcher("/employee/list.jsp").forward(request,response);
                    break;
                case "ADD":
                    // dieu huong sang trang add.jsp
                    request.getRequestDispatcher("/employee/add.jsp").forward(request,response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action!=null){
            switch (action){
                case "ADD":
                 // laays cac du lieu tu request ra
                    String name = request.getParameter("name");
                    String dob = request.getParameter("dob");
                    String sex = request.getParameter("sex");
                    Part file = request.getPart("file");
                    try {
                        EmployeeRequest employeeRequest = new EmployeeRequest(null,name,sdf.parse(dob),Boolean.valueOf(sex),file);
                        employeeService.save(employeeRequest,getServletContext());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    response.sendRedirect("/EmployeeServlet?action=LIST");
                    break;
            }
        }
    }
}
 