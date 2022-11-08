package com.namdp.starmobile.db;

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
import java.util.List;

public class DBUtils {
  private static final String JNDIName = "java:/comp/env/jdbc/Starmobile";
  public static final int PRODUCTS_PER_PAGE = 8;

  /**
   * Method used for connect & retrieve data from database
   *
   * @param sql                    SQL query
   * @param setStatementParameters Lambda function to set query's parameter
   * @param convertResultSetToList Lambda function to convert result set to appropriate entity
   * @param <T>                    Class parameter represent entity class
   * @return List of entity return by the query
   */
  public static <T> List<T> retrieveData(String sql,
                                         SQLCheckedConsumer<PreparedStatement> setStatementParameters,
                                         SQLCheckedFunction<ResultSet, List<T>> convertResultSetToList) {
    List<T> result = null;

    Context ctx = null;
    Connection con = null;
    PreparedStatement statement = null;
    ResultSet rs = null;

    try {
      // Lookup datasource using JNDI name
      ctx = new InitialContext();
      DataSource ds = (DataSource) ctx.lookup(JNDIName);

      // Create prepared statement & set statement's parameter using provided function.
      con = ds.getConnection();
      statement = con.prepareStatement(sql);
      setStatementParameters.accept(statement);

      // Retrieve data & convert to appropriate entity list using provided function.
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

  /**
   * Method use to retrieve a single value (usually the result of SUM, AVG, MIN, MAX) or a single row from db
   *
   * @param sql                    SQL query
   * @param setStatementParameters Lambda function to set query's parameter
   * @param getValue Lambda function to convert result set to value
   * @return Converted value return by the query
   */
  public static Object retrieveSingularValue(String sql,
                                             SQLCheckedConsumer<PreparedStatement> setStatementParameters,
                                             SQLCheckedFunction<ResultSet, Object> getValue) {
    Object result = null;

    Context ctx = null;
    Connection con = null;
    PreparedStatement statement = null;
    ResultSet rs = null;

    try {
      // Lookup datasource using JNDI name
      ctx = new InitialContext();
      DataSource ds = (DataSource) ctx.lookup(JNDIName);

      // Create prepared statement & set statement's parameter using provided function.
      con = ds.getConnection();
      statement = con.prepareStatement(sql);
      setStatementParameters.accept(statement);

      // Retrieve data & convert to appropriate value using provided function
      rs = statement.executeQuery();
      result = getValue.apply(rs);
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
}
