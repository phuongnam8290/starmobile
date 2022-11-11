package com.namdp.starmobile.dao;

import com.namdp.starmobile.db.DBUtils;
import com.namdp.starmobile.entities.Brand;
import com.namdp.starmobile.entities.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
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
  public static List<Product> getPaginatedProducts(int page, int productPerPage) {
    int offset = (page - 1) * productPerPage;
    String query =
        "SELECT p.product_id, p.product_name, p.product_spec, p.product_price, p.product_img, p.product_rating, " +
            "b.brand_id, b.brand_name, b.brand_img " +
            "FROM product p " +
            "LEFT JOIN brand b ON p.product_brand = b.brand_id " +
            "LIMIT ?, ?";

    return DBUtils.retrieveData(query,
        statement -> {
          statement.setInt(1, offset);
          statement.setInt(2, productPerPage);
        },
        resultSet -> {
          List<Product> list = new ArrayList<>();

          while (resultSet.next()) {
            Product product = convertResultSetToProduct(resultSet);
            list.add(product);
          }

          return list;
        }
    );
  }

  /**
   * Count total number of products
   *
   * @return The number of products in db
   */
  public static int countProducts() {
    String query = "SELECT COUNT(*) FROM product";

    return DBUtils.retrieveData(query,
        statement -> {
        },
        resultSet -> {
          if (resultSet.next()) {
            return resultSet.getInt(1);
          }
          return 0;
        }
    );
  }

  /**
   * Get a single product by using product's id.
   *
   * @param productId Product's id
   * @return The product return from db
   */
  public static Product getProductById(int productId) {
    String query =
        "SELECT p.product_id, p.product_name,p.product_spec, p.product_price, p.product_img, p.product_rating," +
            "b.brand_id, b.brand_name, b.brand_img " +
            "FROM product p " +
            "LEFT JOIN brand b ON p.product_brand = b.brand_id " +
            "WHERE p.product_id = ?";

    return DBUtils.retrieveData(query,
        statement -> statement.setInt(1, productId),
        resultSet -> {
          if (resultSet.next()) {
            return convertResultSetToProduct(resultSet);
          }

          return null;
        }
    );
  }

  /**
   * Get all the products in the db with product's name contain the criteria string (case-insensitive)
   *
   * @param criteria The criteria for searching
   * @return List of all product satisfy the criteria
   */
  public static List<Product> getProductsByName(String criteria) {
    String query =
        "SELECT p.product_id, p.product_name,p.product_spec, p.product_price, p.product_img, p.product_rating," +
            "b.brand_id, b.brand_name, b.brand_img " +
            "FROM product p " +
            "LEFT JOIN brand b ON p.product_brand = b.brand_id " +
            "WHERE LOWER(p.product_name) LIKE ?";

    return DBUtils.retrieveData(query,
        statement -> {
          statement.setString(1, "%" + criteria.toLowerCase() + "%");
        },
        resultSet -> {
          List<Product> list = new ArrayList<>();

          while (resultSet.next()) {
            Product product = convertResultSetToProduct(resultSet);
            list.add(product);
          }

          return list;
        }
    );
  }

  /**
   * Convert a given ResultSet row to a Product object.
   *
   * @param resultSet The ResultSet to convert, must move cursor at least to the first row
   * @return The converted Product object
   * @throws SQLException
   */
  private static Product convertResultSetToProduct(ResultSet resultSet) throws SQLException {
    int id = resultSet.getInt("product_id");
    String name = resultSet.getString("product_name");
    String spec = resultSet.getString("product_spec");
    double price = resultSet.getDouble("product_price");
    String img = resultSet.getString("product_img");
    double rating = resultSet.getDouble("product_rating");
    int brandId = resultSet.getInt("brand_id");
    String brandName = resultSet.getString("brand_name");
    String brandImg = resultSet.getString("brand_img");

    Brand brand = new Brand(brandId, brandName, brandImg);
    return new Product(id, name, spec, price, img, rating, brand);
  }
}
