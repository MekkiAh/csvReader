package com.example.csvreader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CsvService {
    @Autowired
    CarRepository carRepository ;
    @Async
    public CompletableFuture<List<Car>> saveCars(final MultipartFile file) throws Exception {
        final long start = System.currentTimeMillis();

        List<Car> cars = parseCSVFile(file);


        cars = carRepository.saveAll(cars);

        return CompletableFuture.completedFuture(cars);
    }

    private List<Car> parseCSVFile(final MultipartFile file) throws Exception {
        final List<Car> cars=new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line=br.readLine()) != null) {
                    final String[] data=line.split(";");
                    final Car car=new Car();
                    car.setManufacturer(data[0]);
                    car.setModel(data[1]);
                    car.setType(data[2]);
                    cars.add(car);
                }
                return cars;
            }
        } catch(final IOException e) {
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }

}
