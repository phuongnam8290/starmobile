package com.namdp.starmobile.utils;

import java.sql.SQLException;

/**
 * Functional interface that throw SQLException, mainly use in database methods to bubble exception to caller.
 * @param <T> The type of the input to the function
 * @param <R> The type of the result of the function
 */
@FunctionalInterface
public interface SQLCheckedFunction<T, R> {
  R apply(T t) throws SQLException;
}
