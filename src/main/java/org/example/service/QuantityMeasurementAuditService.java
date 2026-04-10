/**
 * @author:KrutikaBhadane
 */

package org.example.service;

import org.example.entity.QuantityMeasurementEntity;
import org.example.entity.QuantityModel;
import org.example.interfaces.IMeasurable;
import org.example.repository.IQuantityMeasurementRepository;

import java.util.List;

public class QuantityMeasurementAuditService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementAuditService(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    public void logSuccess(
            QuantityModel<IMeasurable> q1,
            QuantityModel<IMeasurable> q2,
            String operation,
            QuantityModel<IMeasurable> result
    ){
        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(q1, q2, operation, result);

        repository.save(entity);
    }

    public void logComparison(
            QuantityModel<IMeasurable> q1,
            QuantityModel<IMeasurable> q2,
            String operation,
            boolean result
    ){
        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(q1, q2, operation, String.valueOf(result));

        repository.save(entity);
    }

    public void logError(
            QuantityModel<IMeasurable> q1,
            QuantityModel<IMeasurable> q2,
            String operation,
            String message
    ){
        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(q1, q2, operation, message, true);

        repository.save(entity);
    }

    public List<QuantityMeasurementEntity> getAllAuditMeasurements() {
        return repository.getAllMeasurements();
    }
}
