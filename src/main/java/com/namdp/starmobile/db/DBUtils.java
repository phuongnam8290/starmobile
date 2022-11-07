package com.namdp.starmobile.db;

import com.namdp.starmobile.entities.Role;
import com.namdp.starmobile.utils.SQLCheckedConsumer;
import com.namdp.starmobile.utils.SQLCheckedFunction;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
  private static final String JNDIName = "java:/comp/env/jdbc/mobile";

  public static <T> List<T> retrieveData(String sql,
                                         SQLCheckedConsumer<PreparedStatement> setStatementParameters,
                                         SQLCheckedFunction<ResultSet, List<T>> convertResultSetToList) {
    List<T> result = null;

    Context ctx = null;
    Connection con = null;
    PreparedStatement statement = null;
    ResultSet rs = null;

    try {
      ctx = new InitialContext();
      DataSource ds = (DataSource) ctx.lookup(JNDIName);

      con = ds.getConnection();
      statement = con.prepareStatement(sql);
      setStatementParameters.accept(statement);

      rs = statement.executeQuery();
      result = convertResultSetToList.apply(rs);
    } catch (NamingException | SQLException ex) {
      ex.printStackTrace();
    } finally {
      try {
        if (rs != null) rs.close();
        if (statement != null) statement.close();
        if (con != null) con.close();
        if (ctx != null) ctx.close();
      } catch (NamingException | SQLException ex) {
        ex.printStackTrace();
      }
    }

    return result;
  }

//  public static void test() {
//    List<Role> result = retrieveData("SELECT * FROM role where role_id < ?",
//        statement -> {
//          statement.setInt(1, 3);
//        },
//        (resultSet) -> {
//          List<Role> list = new ArrayList<>();
//          while (resultSet.next()) {
//            Role role = new Role(resultSet.getInt("role_id"), resultSet.getString("role_name"));
//            list.add(role);
//          }
//
//          return list;
//        }
//    );
//
//    System.out.println(result);
//  }
}
