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

public class DBUtils {
  private static final String JNDIName = "java:/comp/env/jdbc/Starmobile";
  public static final int PRODUCTS_PER_PAGE = 8;

  /**
   * Method used for connect & retrieve data from database
   *
   * @param sql                    SQL query
   * @param setStatementParameters Lambda function to set query's parameter
   * @param returnResult           Lambda function to convert result set to appropriate returned object
   * @param <T>                    Class parameter represent entity object(s)
   * @return Entity object(s) return by the query
   */
  public static <T> T retrieveData(String sql,
                                   SQLCheckedConsumer<PreparedStatement> setStatementParameters,
                                   SQLCheckedFunction<ResultSet, T> returnResult) {
    T result = null;

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
      result = returnResult.apply(rs);
    } catch (NamingException | SQLException ex) {
      ex.printStackTrace();
    } finally {
      closeResources(rs, statement, con, ctx);
    }

    return result;
  }

  /**
   * Method used for connect & insert / update / delete data in database
   *
   * @param sql                    SQL Query
   * @param setStatementParameters Lambda function to set query's parameter
   * @param returnResult           Lambda function to convert result set to appropriate returned object
   * @return The entity object(s) after being inserted / updated / delete
   */
  public static <T> T updateData(String sql,
                                 SQLCheckedConsumer<PreparedStatement> setStatementParameters,
                                 SQLCheckedFunction<PreparedStatement, T> returnResult) {
    T result = null;

    Context ctx = null;
    Connection con = null;
    PreparedStatement statement = null;

    try {
      // Lookup datasource using JNDI name
      ctx = new InitialContext();
      DataSource ds = (DataSource) ctx.lookup(JNDIName);

      // Create prepared statement & set statement's parameter using provided function.
      con = ds.getConnection();

      // Notify JDBC to return the key when insert
      statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
      setStatementParameters.accept(statement);

      // Update data in database
      int affectedRow = statement.executeUpdate();

      if(affectedRow != 0) {
        // Convert to returned object
        result = returnResult.apply(statement);
      }

    } catch (NamingException | SQLException ex) {
      ex.printStackTrace();
    } finally {
      closeResources(null, statement, con, ctx);
    }

    return result;
  }

  // Closing resource opened by other method
  private static void closeResources(ResultSet rs, PreparedStatement statement, Connection con, Context ctx) {
    try {
      if (rs != null) rs.close();
      if (statement != null) statement.close();
      if (con != null) con.close();
      if (ctx != null) ctx.close();
    } catch (NamingException | SQLException ex) {
      ex.printStackTrace();
    }
  }
}
