package org.example.repository;

import org.example.entity.QuantityMeasurementEntity;
import org.example.exception.DatabaseException;
import org.example.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    private static final Logger logger =
            Logger.getLogger(QuantityMeasurementDatabaseRepository.class.getName());

    private static QuantityMeasurementDatabaseRepository instance;

    private ConnectionPool connectionPool;

    private static final String INSERT_QUERY =
            "INSERT INTO quantity_measurement_entity " +
                    "(this_value, this_unit, this_measurement_type, that_value, that_unit, " +
                    "that_measurement_type, operation, result_value, result_unit, " +
                    "result_measurement_type, result_string, is_error, error_message, " +
                    "created_at, updated_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

    private static final String SELECT_ALL_QUERY =
            "SELECT * FROM quantity_measurement_entity ORDER BY created_at DESC";

    private static final String SELECT_BY_OPERATION =
            "SELECT * FROM quantity_measurement_entity WHERE operation = ? ORDER BY created_at DESC";

    private static final String SELECT_BY_MEASUREMENT_TYPE =
            "SELECT * FROM quantity_measurement_entity WHERE this_measurement_type = ? ORDER BY created_at DESC";

    private static final String DELETE_ALL_QUERY =
            "DELETE FROM quantity_measurement_entity";

    private static final String COUNT_QUERY =
            "SELECT COUNT(*) FROM quantity_measurement_entity";

    private QuantityMeasurementDatabaseRepository() throws SQLException {
        this.connectionPool = ConnectionPool.getInstance();
    }

    public static synchronized QuantityMeasurementDatabaseRepository getInstance() throws SQLException {
        if (instance == null) {
            instance = new QuantityMeasurementDatabaseRepository();
        }
        return instance;
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_QUERY)) {

            ps.setDouble(1, entity.getThisValue());
            ps.setString(2, entity.getThisUnit());
            ps.setString(3, entity.getThisMeasurementType());
            ps.setDouble(4, entity.getThatValue());
            ps.setString(5, entity.getThatUnit());
            ps.setString(6, entity.getThatMeasurementType());
            ps.setString(7, entity.getOperation());
            ps.setDouble(8, entity.getResultValue());
            ps.setString(9, entity.getResultUnit());
            ps.setString(10, entity.getResultMeasurementType());
            ps.setString(11, entity.getResultString());
            ps.setBoolean(12, entity.isError());
            ps.setString(13, entity.getErrorMessage());

            ps.executeUpdate();

            logger.info("Measurement saved successfully");

        } catch (SQLException e) {
            logger.severe("Error saving measurement: " + e.getMessage());
            throw new DatabaseException("Failed to save measurement", e);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        List<QuantityMeasurementEntity> list = new ArrayList<>();

        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_QUERY);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToEntity(rs));
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch measurements", e);
        }

        return list;
    }

    private QuantityMeasurementEntity mapResultSetToEntity(ResultSet rs) throws SQLException {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setThisValue(rs.getDouble("this_value"));
        entity.setThisUnit(rs.getString("this_unit"));
        entity.setThisMeasurementType(rs.getString("this_measurement_type"));

        entity.setThatValue(rs.getDouble("that_value"));
        entity.setThatUnit(rs.getString("that_unit"));
        entity.setThatMeasurementType(rs.getString("that_measurement_type"));

        entity.setOperation(rs.getString("operation"));

        entity.setResultValue(rs.getDouble("result_value"));
        entity.setResultUnit(rs.getString("result_unit"));
        entity.setResultMeasurementType(rs.getString("result_measurement_type"));
        entity.setResultString(rs.getString("result_string"));

        entity.setError(rs.getBoolean("is_error"));
        entity.setErrorMessage(rs.getString("error_message"));

        return entity;
    }
}