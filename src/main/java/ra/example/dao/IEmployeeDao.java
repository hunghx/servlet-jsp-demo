package ra.example.dao;

import ra.example.dto.request.EmployeeRequest;
import ra.example.model.Employee;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.List;

public interface IEmployeeDao {
    List<Employee> findAll();
    Employee findById(Integer id);
    void save(Employee employee);
    void deleteById(Integer id);
}
