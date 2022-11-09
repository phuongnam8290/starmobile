package com.namdp.starmobile.dao;

import com.namdp.starmobile.db.DBUtils;
import com.namdp.starmobile.entities.Role;
import com.namdp.starmobile.entities.User;

public class UserDAO {
  /** Get user by email & password.
   *
   * @param email The user email
   * @param password The user password
   * @return The user with email & password or return null if not exists.
   */
  public static User getUserByAuth(String email, String password) {
    String query = "SELECT u.user_mail, u.user_name, u.user_address, u.user_phone, r.role_name " +
        "FROM user u LEFT JOIN role r " +
        "ON u.user_role = r.role_id " +
        "WHERE u.user_mail=? " +
        "AND u.user_password=?";

    User result = (User) DBUtils.retrieveSingularValue(query,
        statement -> {
          statement.setString(1, email);
          statement.setString(2, password);
        },
        resultSet -> {
          User user = null;
          while (resultSet.next()) {
            String user_mail = resultSet.getString("user_mail");
            String user_name = resultSet.getString("user_name");
            String user_address = resultSet.getString("user_address");
            String user_phone = resultSet.getString("user_phone");
            String role_name = resultSet.getString("role_name");

            Role role = new Role(role_name);
            user = new User(user_mail, user_name, user_address, user_phone, role);
          }

          return user;
        }
    );

    return result;
  }
}
