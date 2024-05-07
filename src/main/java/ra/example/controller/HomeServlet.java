package ra.example.controller;

import ra.example.util.ConnectDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = ConnectDB.getConnection(); // m kết nôi
        try {
           conn.setAutoCommit(false);
            CallableStatement call1 = conn.prepareCall("{call change_balance(?,?)}");
            call1.setInt(1,3);
            call1.setInt(2,1000);
            call1.executeUpdate();
            conn.commit();
            CallableStatement call2 = conn.prepareCall("{call change_balance(?,?)}");
            call1.setInt(1,1);
            call1.setInt(2,-1000);
            call1.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        ConnectDB.closeConnection(conn);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
 