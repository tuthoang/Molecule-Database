/*
 * Copyright 2004-2019 H2 Group. Multiple-Licensed under the MPL 2.0,
 * and the EPL 1.0 (https://h2database.com/html/license.html).
 * Initial Developer: H2 Group
 */
package org.h2.jdbc;

import java.sql.SQLException;

/**
 * Allows us to compile on older platforms, while still implementing the methods
 * from the newer JDBC API.
 */
public interface JdbcStatementBackwardsCompat {

    // compatibility interface

    // JDBC 4.2

    /**
     * Returns the last update count of this statement.
     *
     * @return the update count (number of row affected by an insert, update or
     *         delete, or 0 if no rows or the statement was a create, drop,
     *         commit or rollback; -1 if the statement was a select).
     * @throws SQLException if this object is closed or invalid
     */
    long getLargeUpdateCount() throws SQLException;

    /**
     * Gets the maximum number of rows for a ResultSet.
     *
     * @param max the number of rows where 0 means no limit
     * @throws SQLException if this object is closed
     */
    void setLargeMaxRows(long max) throws SQLException;

    /**
     * Gets the maximum number of rows for a ResultSet.
     *
     * @return the number of rows where 0 means no limit
     * @throws SQLException if this object is closed
     */
    long getLargeMaxRows() throws SQLException;

    /**
     * Executes the batch.
     * If one of the batched statements fails, this database will continue.
     *
     * @return the array of update counts
     */
    long[] executeLargeBatch() throws SQLException;

    /**
     * Executes a statement (insert, update, delete, create, drop)
     * and returns the update count.
     * If another result set exists for this statement, this will be closed
     * (even if this statement fails).
     *
     * If auto commit is on, this statement will be committed.
     * If the statement is a DDL statement (create, drop, alter) and does not
     * throw an exception, the current transaction (if any) is committed after
     * executing the statement.
     *
     * @param sql the SQL statement
     * @return the update count (number of row affected by an insert,
     *         update or delete, or 0 if no rows or the statement was a
     *         create, drop, commit or rollback)
     * @throws SQLException if a database error occurred or a
     *         select statement was executed
     */
    long executeLargeUpdate(String sql) throws SQLException;

    /**
     * Executes a statement and returns the update count.
     * This method just calls executeUpdate(String sql) internally.
     * The method getGeneratedKeys supports at most one columns and row.
     *
     * @param sql the SQL statement
     * @param autoGeneratedKeys ignored
     * @return the update count (number of row affected by an insert,
     *         update or delete, or 0 if no rows or the statement was a
     *         create, drop, commit or rollback)
     * @throws SQLException if a database error occurred or a
     *         select statement was executed
     */
    long executeLargeUpdate(String sql, int autoGeneratedKeys) throws SQLException;

    /**
     * Executes a statement and returns the update count.
     * This method just calls executeUpdate(String sql) internally.
     * The method getGeneratedKeys supports at most one columns and row.
     *
     * @param sql the SQL statement
     * @param columnIndexes ignored
     * @return the update count (number of row affected by an insert,
     *         update or delete, or 0 if no rows or the statement was a
     *         create, drop, commit or rollback)
     * @throws SQLException if a database error occurred or a
     *         select statement was executed
     */
    long executeLargeUpdate(String sql, int columnIndexes[]) throws SQLException;

    /**
     * Executes a statement and returns the update count.
     * This method just calls executeUpdate(String sql) internally.
     * The method getGeneratedKeys supports at most one columns and row.
     *
     * @param sql the SQL statement
     * @param columnNames ignored
     * @return the update count (number of row affected by an insert,
     *         update or delete, or 0 if no rows or the statement was a
     *         create, drop, commit or rollback)
     * @throws SQLException if a database error occurred or a
     *         select statement was executed
     */
    long executeLargeUpdate(String sql, String columnNames[]) throws SQLException;

    // JDBC 4.3 (incomplete)

    /**
     * Enquotes the specified identifier.
     *
     * @param identifier
     *            identifier to quote if required
     * @param alwaysQuote
     *            if {@code true} identifier will be quoted unconditionally
     * @return specified identifier quoted if required or explicitly requested
     */
    String enquoteIdentifier(String identifier, boolean alwaysQuote) throws SQLException;

    /**
     * Checks if specified identifier may be used without quotes.
     *
     * @param identifier
     *            identifier to check
     * @return is specified identifier may be used without quotes
     */
    boolean isSimpleIdentifier(String identifier) throws SQLException;
}
