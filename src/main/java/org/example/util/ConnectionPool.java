package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ConnectionPool {

    private static final Logger logger = Logger.getLogger(ConnectionPool.class.getName());

    private static ConnectionPool instance;

    private List<Connection> availableConnections;
    private List<Connection> usedConnections;

    private final int poolSize;
    private final String dbUrl;
    private final String dbUsername;
    private final String dbPassword;
    private final String driverClass;
    private final String testQuery;

    private ConnectionPool() throws SQLException {
        ApplicationConfig config = ApplicationConfig.getInstance();

        this.poolSize = config.getIntProperty(
                ApplicationConfig.ConfigKey.DB_POOL_SIZE.getKey(), 10);

        this.dbUrl = config.getProperty(
                ApplicationConfig.ConfigKey.DB_URL.getKey());

        this.dbUsername = config.getProperty(
                ApplicationConfig.ConfigKey.DB_USERNAME.getKey());

        this.dbPassword = config.getProperty(
                ApplicationConfig.ConfigKey.DB_PASSWORD.getKey());

        this.driverClass = config.getProperty(
                ApplicationConfig.ConfigKey.DB_DRIVER_CLASS.getKey());

        this.testQuery = config.getProperty(
                ApplicationConfig.ConfigKey.HIKARI_CONNECTION_TEST_QUERY.getKey(),
                "SELECT 1"
        );

        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found: " + driverClass, e);
        }

        availableConnections = new ArrayList<>();
        usedConnections = new ArrayList<>();

        initializeConnections();
    }

    public static synchronized ConnectionPool getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private void initializeConnections() throws SQLException {
        for (int i = 0; i < poolSize; i++) {
            Connection connection = createConnection();
            availableConnections.add(connection);
        }
        logger.info("Connection pool initialized with " + poolSize + " connections.");
    }


    private Connection createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                dbUrl,
                dbUsername,
                dbPassword
        );
        logger.info("New database connection created.");
        return connection;
    }

    public synchronized Connection getConnection() throws SQLException {
        if (!availableConnections.isEmpty()) {
            Connection connection = availableConnections.remove(0);

            if (!validateConnection(connection)) {
                connection = createConnection();
            }
            usedConnections.add(connection);
            return connection;
        }

        if (usedConnections.size() < poolSize) {
            Connection connection = createConnection();
            usedConnections.add(connection);
            return connection;
        }
        throw new SQLException("Maximum connection pool size reached.");
    }

    public synchronized void releaseConnection(Connection connection) {
        if (connection == null) {
            return;
        }

        usedConnections.remove(connection);
        availableConnections.add(connection);

        logger.info("Connection returned to pool.");
    }

    public boolean validateConnection(Connection connection) {

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(this.testQuery);
            return true;
        } catch (SQLException e) {
            logger.warning("Connection validation failed: " + e.getMessage());
            return false;
        }
    }

    public synchronized void closeAll() {
        logger.info("Closing all database connections...");
        for (Connection connection : availableConnections) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warning("Error closing available connection: " + e.getMessage());
            }
        }

        for (Connection connection : usedConnections) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warning("Error closing used connection: " + e.getMessage());
            }
        }

        availableConnections.clear();
        usedConnections.clear();

        logger.info("All database connections closed.");
    }

    public int getAvailableConnectionCount() {
        return availableConnections.size();
    }

    public int getUsedConnectionCount() {
        return usedConnections.size();
    }

    public int getTotalConnectionCount() {
        return availableConnections.size() + usedConnections.size();
    }

    @Override
    public String toString() {
        return "ConnectionPool{" +
                "availableConnections=" + availableConnections.size() +
                ", usedConnections=" + usedConnections.size() +
                ", totalConnections=" + getTotalConnectionCount() +
                ", poolSize=" + poolSize +
                '}';
    }

    public static void main(String[] args) {

        try {

            ConnectionPool pool = ConnectionPool.getInstance();

            Connection conn1 = pool.getConnection();

            logger.info("Validate connection: " +
                    (pool.validateConnection(conn1) ? "Success" : "Failure"));

            logger.info("Available connections after acquiring 1: "
                    + pool.getAvailableConnectionCount());

            logger.info("Used connections after acquiring 1: "
                    + pool.getUsedConnectionCount());

            pool.releaseConnection(conn1);

            logger.info("Available connections after releasing 1: "
                    + pool.getAvailableConnectionCount());

            logger.info("Used connections after releasing 1: "
                    + pool.getUsedConnectionCount());

            pool.closeAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}