/**
 * @author:KrutikaBhadane
 */

package org.example.interfaces;

import org.example.dto.QuantityDTO;
import org.example.entity.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityMeasurementService {

    double convert(QuantityDTO dto);

}
