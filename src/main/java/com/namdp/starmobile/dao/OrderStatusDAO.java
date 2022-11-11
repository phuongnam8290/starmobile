package com.namdp.starmobile.dao;

import com.namdp.starmobile.db.DBUtils;
import com.namdp.starmobile.entities.OrderStatus;

public class OrderStatusDAO {
  public static OrderStatus getOrderStatusById(int id) {
    String query = "SELECT * FROM order_status os " +
        "WHERE os.order_status_id = ?";

    return DBUtils.retrieveData(query,
        statement -> statement.setInt(1, id),
        resultSet -> {
          if (resultSet.next()) {
            int orderStatusId = resultSet.getInt("order_status_id");
            String orderStatusName = resultSet.getString("order_status_name");

            return new OrderStatus(orderStatusId, orderStatusName);
          }

          return null;
        }
    );
  }
}
