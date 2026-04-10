/**
 * @author:KrutikaBhadane
 */

package org.example.service;

import org.example.dto.QuantityDTO;
import org.example.entity.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityMeasurementService {
    public boolean compare(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);
    public QuantityDTO convert(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);
    public QuantityDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);
    public QuantityDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetUnitDTO);
    public QuantityDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);
    public QuantityDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetUnitDTO);
    public QuantityDTO divide(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);
    public List<QuantityMeasurementEntity> getAllMeasurementRecords();
}
