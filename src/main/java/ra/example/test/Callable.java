package ra.example.test;

import ra.example.util.ConnectDB;

import java.sql.*;

public class Callable {
    public static void main(String[] args) throws SQLException {
        Connection conn = ConnectDB.getConnection(); // m kết nôi
//        CallableStatement callSt = conn.prepareCall("{call insert_em(?,?,?,?)}");
//        callSt.setString(1,"hung");
//        callSt.setDate(2,new Date(new java.util.Date().getTime()));
//        callSt.setBoolean(3,true);
//        callSt.setString(4,"xxxxxxx");
//
//        CallableStatement call = conn.prepareCall("{call count_employee(?)}");
//        // Đăng kí kiểu tham số nhận về
//        call.registerOutParameter(1, Types.INTEGER);
//        call.execute();
//        int count = call.getInt(1);
//        System.out.println(count);
//        ConnectDB.closeConnection(conn);

        // chuyển tiền
        // Công tiền
        try {
            conn.setAutoCommit(false);
            CallableStatement call1 = conn.prepareCall("{call change_balance(?,?)}");
            call1.setInt(1,3);
            call1.setInt(2,1000);
            call1.executeUpdate();
            CallableStatement call2 = conn.prepareCall("{call change_balance(?,?)}");
            call1.setInt(1,1);
            call1.setInt(2,-1000);
            call1.executeUpdate();
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            conn.rollback();
        }

        ConnectDB.closeConnection(conn);
    }
}
