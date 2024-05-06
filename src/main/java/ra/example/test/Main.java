package ra.example.test;

import ra.example.model.Employee;

import java.sql.*;
import java.util.Date;

public class Main {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_mysql";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "hung18061999";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            // Đăng kí Driver
            Class.forName(DRIVER);
            // Taoj connection
            Connection conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println(conn);
            // Thuc thi cau lenh
//            Statement statement = conn.createStatement();
//            PreparedStatement prepare = conn.prepareStatement("select * from employee where id = ?");
           CallableStatement call = conn.prepareCall("{call insert_em(?,?,?,?)}");
            // truyền đối số
//            prepare.setInt(1,100);
            Date b = new Date();
            call.setString(1,"anh tôn");
            call.setDate(2,new java.sql.Date(b.getTime()));
            call.setBoolean(3,true);
            call.setString(4, null);
            int count = call.executeUpdate();

            // execute , executeQuery (SELECT) , executeUpdate(UPDATE/ INSERT / DELETE)
            // ResultSet rs = statement.executeQuery("select * from employee");

//            ResultSet rs = prepare.executeQuery();
//            if (rs.next()){
//                Employee e = new Employee();
//                e.setId(rs.getInt("id"));
//                e.setName(rs.getString("name"));
//                e.setDob(rs.getDate("dob"));
//                e.setSex(rs.getBoolean("sex"));
//                e.setAvatar(rs.getString("avatar"));
//                System.out.println(e);
//            }
//            int count = statement.executeUpdate("insert into employee (name,dob,sex,avatar) values ('Khanh sky','1999-10-5',1,null)");
            System.out.println(count);
            // Đóng kêt nối , tránh  rò rỉ bộ nhớ
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
