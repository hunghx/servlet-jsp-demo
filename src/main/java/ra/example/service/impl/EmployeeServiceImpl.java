package ra.example.service.impl;

import ra.example.dto.request.EmployeeRequest;
import ra.example.model.Employee;
import ra.example.service.IEmployeeService;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmployeeServiceImpl implements IEmployeeService {
    private static final String uploadPath = "C:\\Users\\AD\\IdeaProjects\\Servlet\\src\\main\\webapp\\uploads";
    private static final List<Employee> employees = new ArrayList<>();
    @Override
    public List<Employee> findAll() {
        return employees;
    }

    @Override
    public Employee findById(Integer id) {
        return employees.stream()
                .filter(em-> Objects.equals(em.getId(),id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(EmployeeRequest request) throws IOException {
        if (request.getId()!=null){
            // cap nhat
            Employee employee = findById(request.getId());
            if (!request.getName().isEmpty()){
                employee.setName(request.getName());
            }
            employee.setSex(request.getSex());
            employee.setDob(request.getDob());
            Part file = request.getFile();
            if (file!=null && file.getSize()!=0){
                // uploads
                employee.setAvatar("/uploads/"+file.getSubmittedFileName());
                file.write(uploadPath+ File.separator+file.getSubmittedFileName());
            }
            employees.set(employees.indexOf(findById(request.getId())), employee); // cos the thua

        }else {
            // them moi
            // chuyen tu dto -> model
            // upload file
            Employee newEmployee = new Employee(getNewId(),request.getName(),request.getDob(),request.getSex(),"https://bookvexe.vn/wp-content/uploads/2023/04/chon-loc-25-avatar-facebook-mac-dinh-chat-nhat_3.jpg");
            Part file = request.getFile();
            if (file!=null && file.getSize()!=0){
                // uploads
                newEmployee.setAvatar("/uploads/"+file.getSubmittedFileName());
                file.write(uploadPath+ File.separator+file.getSubmittedFileName());
            }
            employees.add(newEmployee);
        }
    }

    @Override
    public void deleteById(Integer id) {
        employees.remove(findById(id));
    }
    private Integer getNewId(){
        return employees.stream().map(Employee::getId).max(Integer::compareTo).orElse(0)+1;
    }
}
