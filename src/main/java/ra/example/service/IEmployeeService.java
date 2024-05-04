package ra.example.service;

import ra.example.dto.request.EmployeeRequest;
import ra.example.model.Employee;

import java.io.IOException;
import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();
    Employee findById(Integer id);
    void save(EmployeeRequest request) throws IOException;
    void deleteById(Integer id);
}
