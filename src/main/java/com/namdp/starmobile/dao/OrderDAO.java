package com.namdp.starmobile.dao;

import com.namdp.starmobile.db.DBUtils;
import com.namdp.starmobile.entities.Order;
import com.namdp.starmobile.entities.OrderDetail;
import com.namdp.starmobile.entities.OrderStatus;
import com.namdp.starmobile.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrderDAO {
  /**
   * Fetch pending order of the user, or return null if not exist.
   *
   * @param email The email of user that need to fetch pending order
   * @return The pending order of user or null if not exist
   */
  public static Order getPendingOrderByUser(String email) {
    // Fetch pending order for user
    String query = "SELECT o.* " +
        "FROM `order` o INNER JOIN order_status os " +
        "ON o.order_status = os.order_status_id " +
        "WHERE o.user_mail = ? " +
        "AND os.order_status_name = 'pending'";

    return DBUtils.retrieveData(query,
        statement -> statement.setString(1, email),
        resultSet -> {
          if (resultSet.next()) {
            return convertResultSetToOrder(resultSet);
          }

          return null;
        }
    );
  }

  public static Order getOrderById(int id) {
    String query = "SELECT * FROM `order` o " +
        "WHERE o.order_id = ?";

    return DBUtils.retrieveData(query,
        statement -> statement.setInt(1, id),
        resultSet -> {
          if (resultSet.next()) {
            return convertResultSetToOrder(resultSet);
          }

          return null;
        }
    );
  }

  // Create new pending order for user
  public static Order createPendingOrder(User user) {
    String query = "INSERT INTO `order`(user_mail, order_address) " +
        "VALUES (?, ?)";
    return DBUtils.updateData(query,
        statement -> {
          statement.setString(1, user.getEmail());
          statement.setString(2, user.getAddress());
        },
        statement -> {
          // Get the id of inserted order
          ResultSet rs = statement.getGeneratedKeys();

          if (rs.next()) {
            int orderId = rs.getInt(1);
            return OrderDAO.getOrderById(orderId);
          }

          return null;
        }
    );
  }

  // Delete order
  public static Order deleteOrder(Order order) {
    String query = "DELETE FROM `order` " +
        "WHERE order_id=?";

    return DBUtils.updateData(query,
        statement -> statement.setInt(1, order.getId()),
        statement -> order);
  }

  // Update order status to check out

  /**
   * Convert a given ResultSet row to a Order object.
   *
   * @param resultSet The ResultSet to convert, must move cursor at least to the first row
   * @return The converted Order object
   * @throws SQLException
   */
  private static Order convertResultSetToOrder(ResultSet resultSet) throws SQLException {
    int orderId = resultSet.getInt("order_id");
    String userMail = resultSet.getString("user_mail");
    Date orderDate = resultSet.getDate("order_date");
    double orderDiscount = resultSet.getDouble("order_discount");
    String orderAddress = resultSet.getString("order_address");
    double orderShipmentCost = resultSet.getDouble("order_shipment_cost");
    OrderStatus orderStatus = OrderStatusDAO.getOrderStatusById(resultSet.getInt("order_status"));
    List<OrderDetail> orderDetails = OrderDetailDAO.getOrderDetailsByOrderId(orderId);

    return new Order(orderId, userMail, orderStatus, orderDate, orderDiscount, orderAddress, orderShipmentCost,
        orderDetails);
  }
}
