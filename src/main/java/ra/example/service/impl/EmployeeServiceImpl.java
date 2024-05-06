package ra.example.service.impl;

import ra.example.dao.IEmployeeDao;
import ra.example.dao.impl.EmployeeDaoImpl;
import ra.example.dto.request.EmployeeRequest;
import ra.example.model.Employee;
import ra.example.service.IEmployeeService;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmployeeServiceImpl implements IEmployeeService {
    private static final String uploadFolder = "C:\\Users\\AD\\IdeaProjects\\Servlet\\src\\main\\webapp\\uploads";
    private static final IEmployeeDao employeeDao = new EmployeeDaoImpl();
    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Employee findById(Integer id) {
        return employeeDao.findById(id);
    }

    @Override
    public void save(EmployeeRequest request, ServletContext context) throws IOException {
        // chuyển đổi từ dto -> model
        Employee model= new Employee(request.getId(),request.getName(),request.getDob(),request.getSex(),null);
        // upload anhr
        String path = context.getRealPath("/uploads");
        File fileUpload = new File(path);
        if (!fileUpload.exists()){
            fileUpload.mkdir();
        }
        if (request.getId()!=null){
            // cap nhat
            // lấy ra ảnh cũ , nếu có ảnh gửi lên thì cập nhật lại anh mơi
        }else {
            // them moi
//            Employee newEmployee = new Employee(getNewId(),request.getName(),request.getDob(),request.getSex(),"https://bookvexe.vn/wp-content/uploads/2023/04/chon-loc-25-avatar-facebook-mac-dinh-chat-nhat_3.jpg");
            model.setAvatar("https://bookvexe.vn/wp-content/uploads/2023/04/chon-loc-25-avatar-facebook-mac-dinh-chat-nhat_3.jpg");

        }
        Part file = request.getFile();
        if (file!=null && file.getSize()!=0){
            // uploads
            model.setAvatar("/uploads/"+file.getSubmittedFileName());
            file.write(path+ File.separator+file.getSubmittedFileName());
            file.write(uploadFolder+ File.separator+file.getSubmittedFileName());
        }
        employeeDao.save(model);
    }

    @Override
    public void deleteById(Integer id) {
        employeeDao.deleteById(id);
    }

}
