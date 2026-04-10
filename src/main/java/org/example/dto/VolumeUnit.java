/**
 * @author:KrutikaBhadane
 */

package org.example.dto;

import org.example.dto.IMeasurableUnit;

public enum VolumeUnit implements IMeasurableUnit {
    MILLILITRE,
    LITRE,
    GALLON;

    @Override
    public String getUnitName() {
        return this.name();
    }

    @Override
    public String getMeasurementType() {
        return this.getClass().getSimpleName();
    }
}
