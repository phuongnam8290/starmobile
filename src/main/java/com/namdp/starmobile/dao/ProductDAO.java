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
  public static List<Product> getPaginatedProducts(int page, int productPerPage) {
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

  /**
   * Get a single product by using product's id.
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

    Product result = (Product) DBUtils.retrieveSingularValue(query,
        statement -> {
          statement.setInt(1, productId);
        },
        resultSet -> {
          Product product = null;

          while (resultSet.next()) {
            int id = resultSet.getInt("product_id");
            String name = resultSet.getString("product_name");
            String spec = resultSet.getString("product_spec");
            double price = resultSet.getDouble("product_price");
            String img = resultSet.getString("product_img");
            double rating = resultSet.getDouble("product_rating");
            int brandId = resultSet.getInt("brand_id");
            String brandName = resultSet.getString("brand_name");
            String brandImg = resultSet.getString("brand_img");

            product = new Product(id, name, spec, price, img, rating, brandId, brandName, brandImg);
          }

          return product;
        }
    );

    return result;
  }
}
