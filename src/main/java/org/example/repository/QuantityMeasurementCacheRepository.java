/**
 * @author:KrutikaBhadane
 */

package org.example.repository;

import org.example.entity.QuantityMeasurementEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository{

    public static final String FILE_NAME = "quantity_measurements.ser";

    List<QuantityMeasurementEntity> quantityMeasurementEntityCache;

    private static QuantityMeasurementCacheRepository instance;

    private QuantityMeasurementCacheRepository() {
        quantityMeasurementEntityCache = new ArrayList<>();
        loadFromDisk();
    }

    public static QuantityMeasurementCacheRepository getInstance(){
        if (instance == null){
            instance = new QuantityMeasurementCacheRepository();
        }

        return instance;
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {
        quantityMeasurementEntityCache.add(entity);
        saveToDisk(entity);
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return quantityMeasurementEntityCache;
    }

    private void saveToDisk(QuantityMeasurementEntity entity){
        try {
                FileOutputStream fos = new FileOutputStream(FILE_NAME, true);
                AppendableObjectOutputStream oos = new AppendableObjectOutputStream(fos);

            oos.writeObject(entity);
        } catch (IOException e) {
            System.out.println("Error saving entity: "+e.getMessage());
        }
    }

    private void loadFromDisk(){

        File file = new File(FILE_NAME);

        if (!file.exists()) return;

        try (
                FileInputStream fis = new FileInputStream(FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {

            while (true) {
                try {
                    QuantityMeasurementEntity entity = (QuantityMeasurementEntity) ois.readObject();
                    quantityMeasurementEntityCache.add(entity);
                } catch (EOFException e) {
                    break;
                }
            }
            System.out.println("Loaded: "+quantityMeasurementEntityCache.size()+" quantity measurement entities from storage.");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error loading quantity measurement entities: "+ex.getMessage());
        }
    }
}

