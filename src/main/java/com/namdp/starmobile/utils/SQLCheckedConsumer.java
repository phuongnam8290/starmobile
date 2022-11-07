package com.namdp.starmobile.utils;

import java.sql.SQLException;

/**
 * Functional interface that throw SQLException, mainly use in database methods to bubble exception to caller.
 * @param <T> The type of the input to the operation
 */
@FunctionalInterface
public interface SQLCheckedConsumer<T> {
  void accept(T t) throws SQLException;
}
