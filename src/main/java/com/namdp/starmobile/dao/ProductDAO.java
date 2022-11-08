package com.namdp.starmobile.dao;

import com.namdp.starmobile.db.DBUtils;
import com.namdp.starmobile.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

  /**
   * Get paginated products from db.
   *
   * @param page           Product offset
   * @param productPerPage Number of products per page
   * @return List of products offset by pagination.
   */
  public static List<Product> getProducts(int page, int productPerPage) {
    int offset = (page - 1) * productPerPage;
    String query =
        "SELECT p.product_id, p.product_name, p.product_spec, p.product_price, p.product_img, p.product_rating\n" +
            "FROM product p " +
            "LIMIT ?, ?";

    List<Product> productList = DBUtils.retrieveData(query,
        statement -> {
          statement.setInt(1, offset);
          statement.setInt(2, productPerPage);
        },
        resultSet -> {
          List<Product> list = new ArrayList<>();

          while (resultSet.next()) {
            int id = resultSet.getInt("product_id");
            String name = resultSet.getString("product_name");
            String specs = resultSet.getString("product_spec");
            double price = resultSet.getDouble("product_price");
            String img = resultSet.getString("product_img");
            double rating = resultSet.getDouble("product_rating");

            Product product = new Product(id, name, specs, price, img, rating);
            list.add(product);
          }

          return list;
        }
    );

    return productList;
  }

  /**
   * Count total number of products
   *
   * @return The number of products in db
   */
  public static int countProducts() {
    String query = "SELECT COUNT(*) FROM product";
    Integer quantity = (Integer) DBUtils.retrieveSingularValue(query,
        statement -> {
        },
        resultSet -> {
          resultSet.next();
          return resultSet.getInt(1);
        }
    );

    return quantity;
  }
}
