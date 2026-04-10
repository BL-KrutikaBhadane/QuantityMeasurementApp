/**
 * @author:KrutikaBhadane
 */

package org.example.serviceImpl;

import org.example.dto.QuantityDTO;
import org.example.entity.QuantityMeasurementEntity;
import org.example.entity.QuantityModel;
import org.example.interfaces.IMeasurable;
import org.example.service.IQuantityMeasurementService;
import org.example.service.QuantityMeasurementAuditService;

import java.util.List;
import java.util.function.DoubleBinaryOperator;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private QuantityMeasurementAuditService auditService;

    public QuantityMeasurementServiceImpl(QuantityMeasurementAuditService auditService) {
        this.auditService = auditService;
    }

    private enum Operation {
        COMPARISON, CONVERSION, ARITHMETIC
    }

    private enum ArithmeticOperation {
        ADDITION, SUBTRACTION, DIVISION
    }

    @Override
    public boolean compare(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {

        QuantityModel<IMeasurable> q1 = getQuantityModel(thisQuantityDTO);
        QuantityModel<IMeasurable> q2 = getQuantityModel(thatQuantityDTO);

        return executeOperation(
                q1,
                q2,
                "COMPARISON",
                () -> compareTo(q1, q2),
                null
        );
    }

    private <U extends IMeasurable> boolean compareTo(QuantityModel<U> q1, QuantityModel<U> q2){
        double base1 = q1.getUnit().convertToBaseUnit(q1.getValue());
        double base2 = q2.getUnit().convertToBaseUnit(q2.getValue());

        return Double.compare(base1, base2) == 0;
    }

    @Override
    public QuantityDTO convert(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {

        QuantityModel<IMeasurable> source = getQuantityModel(thisQuantityDTO);
        QuantityModel<IMeasurable> target = getQuantityModel(thatQuantityDTO);

        return executeOperation(
                source,
                target,
                "CONVERSION",
                () -> {
                    double value = convertTo(source, target.getUnit());
                    return new QuantityDTO(value,
                            target.getUnit().getUnitName(),
                            source.getUnit().getMeasurementType());
                },
                dto -> new QuantityModel<>(dto.getValue(), target.getUnit())
        );
    }

    private <U extends IMeasurable> double convertTo(QuantityModel<U> source, U targetUnit){
        double baseValue = source.getUnit().convertToBaseUnit(source.getValue());
        double resultValue = targetUnit.convertFromBaseUnit(baseValue);
        return resultValue;
    }

    @Override
    public QuantityDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {

        QuantityModel<IMeasurable> q1 = getQuantityModel(thisQuantityDTO);
        QuantityModel<IMeasurable> q2 = getQuantityModel(thatQuantityDTO);

        return executeOperation(
                q1, q2, "ADDITION",
                () -> {
                    validateArithmeticOperands(q1, q2, null, false);
                    double result = performArithmetic(q1, q2, null, ArithmeticOperation.ADDITION);
                    return new QuantityDTO(result,
                            q1.getUnit().getUnitName(),
                            q1.getUnit().getMeasurementType());
                },
                dto -> new QuantityModel<>(dto.getValue(), q1.getUnit())
        );
    }

    @Override
    public QuantityDTO add(
            QuantityDTO thisQuantityDTO,
            QuantityDTO thatQuantityDTO,
            QuantityDTO targetUnitDTO) {

        QuantityModel<IMeasurable> q1 = getQuantityModel(thisQuantityDTO);
        QuantityModel<IMeasurable> q2 = getQuantityModel(thatQuantityDTO);
        QuantityModel<IMeasurable> target = getQuantityModel(targetUnitDTO);

        return executeOperation(
                q1, q2, "ADDITION",
                () -> {
                    validateArithmeticOperands(q1, q2, target.getUnit(), true);
                    double result = performArithmetic(q1, q2, target.getUnit(), ArithmeticOperation.ADDITION);
                    return new QuantityDTO(result,
                            target.getUnit().getUnitName(),
                            target.getUnit().getMeasurementType());
                },
                dto -> new QuantityModel<>(dto.getValue(), target.getUnit())
        );
    }

    @Override
    public QuantityDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {

        QuantityModel<IMeasurable> q1 = getQuantityModel(thisQuantityDTO);
        QuantityModel<IMeasurable> q2 = getQuantityModel(thatQuantityDTO);

        return executeOperation(
                q1, q2, "SUBTRACTION",
                () -> {
                    validateArithmeticOperands(q1, q2, null, false);
                    double result = performArithmetic(q1, q2, null, ArithmeticOperation.SUBTRACTION);
                    return new QuantityDTO(result,
                            q1.getUnit().getUnitName(),
                            q1.getUnit().getMeasurementType());
                },
                dto -> new QuantityModel<>(dto.getValue(), q1.getUnit())
        );
    }

    @Override
    public QuantityDTO subtract(
            QuantityDTO thisQuantityDTO,
            QuantityDTO thatQuantityDTO,
            QuantityDTO targetUnitDTO) {

        QuantityModel<IMeasurable> q1 = getQuantityModel(thisQuantityDTO);
        QuantityModel<IMeasurable> q2 = getQuantityModel(thatQuantityDTO);
        QuantityModel<IMeasurable> target = getQuantityModel(targetUnitDTO);

        return executeOperation(
                q1, q2, "SUBTRACTION",
                () -> {
                    validateArithmeticOperands(q1, q2, target.getUnit(), true);
                    double result = performArithmetic(q1, q2, target.getUnit(), ArithmeticOperation.SUBTRACTION);
                    return new QuantityDTO(result,
                            target.getUnit().getUnitName(),
                            target.getUnit().getMeasurementType());
                },
                dto -> new QuantityModel<>(dto.getValue(), target.getUnit())
        );
    }

    @Override
    public QuantityDTO divide(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {

        QuantityModel<IMeasurable> q1 = getQuantityModel(thisQuantityDTO);
        QuantityModel<IMeasurable> q2 = getQuantityModel(thatQuantityDTO);

        return executeOperation(
                q1, q2, "DIVISION",
                () -> {
                    validateArithmeticOperands(q1, q2, null, false);
                    double result = performArithmetic(q1, q2, null, ArithmeticOperation.DIVISION);
                    return new QuantityDTO(result, "RATIO", "SCALAR");
                },
                dto -> new QuantityModel<>(dto.getValue(), q1.getUnit())
        );
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurementRecords() {
        return auditService.getAllAuditMeasurements();
    }

    private QuantityModel<IMeasurable> getQuantityModel(QuantityDTO dto) {
        IMeasurable unit = IMeasurable.getUnit(dto.getMeasurementType(), dto.getUnit());
        return new QuantityModel<>(dto.getValue(), unit);
    }

    private <U extends IMeasurable> void validateArithmeticOperands(
            QuantityModel<U> q1, QuantityModel<U> q2,
            U targetUnit, boolean targetRequired
    ) {
        if (q1 == null || q2 == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (!q1.getUnit().getMeasurementType()
                .equals(q2.getUnit().getMeasurementType()))
            throw new IllegalArgumentException("Measurement types must match");

        if (targetRequired && targetUnit == null)
            throw new IllegalArgumentException("Target unit required");
    }

    private <U extends IMeasurable> double performArithmetic(
            QuantityModel<U> q1, QuantityModel<U> q2,
            U targetUnit, ArithmeticOperation operation
    ) {
        double base1 = q1.getUnit().convertToBaseUnit(q1.getValue());
        double base2 = q2.getUnit().convertToBaseUnit(q2.getValue());

        DoubleBinaryOperator op;

        switch (operation) {

            case ADDITION:
                op = Double::sum;
                break;

            case SUBTRACTION:
                op = (a, b) -> a - b;
                break;

            case DIVISION:
                op = (a, b) -> a / b;
                break;

            default:
                throw new IllegalArgumentException("Invalid operation");
        }
        double baseResult = op.applyAsDouble(base1, base2);
        if(targetUnit == null) return baseResult;

        return targetUnit.convertFromBaseUnit(baseResult);
    }

    private <T> T executeOperation(
            QuantityModel<IMeasurable> q1,
            QuantityModel<IMeasurable> q2,
            String operation,
            java.util.function.Supplier<T> action,
            java.util.function.Function<T, QuantityModel<IMeasurable>> resultMapper
    ) {
        try {
            T result = action.get();

            if (resultMapper != null) {
                QuantityModel<IMeasurable> resultModel = resultMapper.apply(result);
                auditService.logSuccess(q1, q2, operation, resultModel);
            } else {
                auditService.logComparison(q1, q2, operation, (Boolean) result);
            }

            return result;

        } catch (Exception e) {
            auditService.logError(q1, q2, operation, e.getMessage());
            throw e;
        }
    }
}
