/**
 * @author:KrutikaBhadane
 */

package org.example.service;

import org.example.dto.QuantityDTO;
import org.example.entity.QuantityMeasurementEntity;
import org.example.interfaces.ConversionStrategy;
import org.example.interfaces.IQuantityMeasurementService;
import org.example.repository.QuantityMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private MeasurementFactory factory;

    @Autowired
    private QuantityMeasurementRepository repository;

    @Override
    public double convert(QuantityDTO dto) {

        if (dto.getValue() < 0) {
            throw new RuntimeException("Value cannot be negative");
        }

        ConversionStrategy strategy =
                factory.getStrategy(dto.getFromUnit());

        double result = strategy.convert(
                dto.getValue(),
                dto.getFromUnit(),
                dto.getToUnit()
        );

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue(dto.getValue());
        entity.setFromUnit(dto.getFromUnit());
        entity.setToUnit(dto.getToUnit());
        entity.setResult(result);

        repository.save(entity);

        return result;
    }
}