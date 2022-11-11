package com.namdp.starmobile.dao;

import com.namdp.starmobile.db.DBUtils;
import com.namdp.starmobile.entities.Order;
import com.namdp.starmobile.entities.OrderDetail;
import com.namdp.starmobile.entities.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO {
  // Add new order details
  public static OrderDetail insertOrderDetail(Order order, Product product, int quantity) {
    String query = "INSERT INTO order_detail(product_id, order_id, quantity, price) " +
        "VALUES (?, ?, ?, ?)";
    return DBUtils.updateData(query,
        statement -> {
          statement.setInt(1, product.getId());
          statement.setInt(2, order.getId());
          statement.setDouble(3, quantity);
          statement.setDouble(4, product.getPrice() * quantity);
        },
        statement -> {
          ResultSet rs = statement.getGeneratedKeys();
          if (rs.next()) {
            int id = rs.getInt(1);
            return OrderDetailDAO.getOrderDetailsById(id);
          }

          return null;
        });
  }

  // Remove product from order

  // Update order details
  public static OrderDetail updateOrderDetail(int id, Product product, int quantity) {
    String query = "UPDATE order_detail od " +
        "SET od.quantity = ?, od.price = ? " +
        "WHERE od.id = ?";

    return DBUtils.updateData(query,
        statement -> {
          statement.setInt(1, quantity);
          statement.setDouble(2, product.getPrice() * quantity);
          statement.setInt(3, id);
        },
        statement -> {
          return OrderDetailDAO.getOrderDetailsById(id);
        }
    );
  }

  public static OrderDetail getOrderDetailsById(int id) {
    String query = "SELECT * FROM order_detail od " +
        "WHERE od.id = ?";

    return DBUtils.retrieveData(query,
        statement -> statement.setInt(1, id),
        resultSet -> {
          if (resultSet.next()) {
            return convertResultSetToOrderDetail(resultSet);
          }

          return null;
        }
    );
  }

  /**
   * Fetch all order details by order id
   *
   * @param orderId The order id that need to fetch order details
   * @return List of order details of order
   */
  public static List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
    String query =
        "SELECT od.id, od.product_id, od.order_id, od.quantity, od.price " +
            "FROM order_detail od " +
            "WHERE od.order_id = ?";

    return DBUtils.retrieveData(query,
        statement -> statement.setInt(1, orderId),
        resultSet -> {
          List<OrderDetail> orderDetails = new ArrayList<>();

          while (resultSet.next()) {
            OrderDetail orderDetail = convertResultSetToOrderDetail(resultSet);
            orderDetails.add(orderDetail);
          }

          return orderDetails;
        });
  }

  private static OrderDetail convertResultSetToOrderDetail(ResultSet resultSet) throws SQLException {
    int id = resultSet.getInt("id");
    int quantity = resultSet.getInt("quantity");
    double price = resultSet.getDouble("price");
    Product product = ProductDAO.getProductById(resultSet.getInt("product_id"));
    int orderId = resultSet.getInt("order_id");

    return new OrderDetail(id, product, orderId, quantity, price);
  }
}
