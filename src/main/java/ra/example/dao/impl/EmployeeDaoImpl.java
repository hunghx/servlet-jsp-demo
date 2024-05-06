package ra.example.dao.impl;

import ra.example.dao.IEmployeeDao;
import ra.example.model.Employee;
import ra.example.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements IEmployeeDao {
    @Override
    public List<Employee> findAll() {
        Connection conn = ConnectDB.getConnection();
        List<Employee> list = new ArrayList<>();
        try {
            PreparedStatement pre = conn.prepareStatement("Select * from Employee");
            ResultSet rs = pre.executeQuery();
            while (rs.next()){
                Employee e = new Employee();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("name"));
                e.setDob(rs.getDate("dob"));
                e.setSex(rs.getBoolean("sex"));
                e.setAvatar(rs.getString("avatar"));
               list.add(e);
            }
            return list;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public Employee findById(Integer id) {
        return null;
    }

    @Override
    public void save(Employee employee) {
        Connection conn = ConnectDB.getConnection();
        PreparedStatement pre = null;
        try{
          if (employee.getId()==null){
              // them moi
              pre = conn.prepareStatement("insert into employee(name, dob, sex, avatar) value (?,?,?,?)");
              pre.setString(1,employee.getName());
              pre.setDate(2,new java.sql.Date(employee.getDob().getTime()));
              pre.setBoolean(3,employee.getSex());
              pre.setString(4, employee.getAvatar());
          }else {
              // chua lam update
          }
          pre.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public void deleteById(Integer id) {

    }
}
