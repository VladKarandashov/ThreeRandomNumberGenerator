package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static String PATH_TO_CONFIG = "src/main/resources/config.properties";

    public static void main(String[] args){

        Properties property = new Properties();

        try {
            FileInputStream fis = new FileInputStream(PATH_TO_CONFIG);
            property.load(fis);

            long delayTime = Long.parseLong(property.getProperty("time.delayTime"));

            final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleAtFixedRate(() -> {
                Random random = new Random();

                long minNumber1 = Long.parseLong(property.getProperty("number1.start"));
                long maxNumber1 = Long.parseLong(property.getProperty("number1.stop"));
                long number1 = random.nextLong(maxNumber1 - minNumber1 + 1) + minNumber1;

                long minNumber2 = Long.parseLong(property.getProperty("number2.start"));
                long maxNumber2 = Long.parseLong(property.getProperty("number2.stop"));
                long number2 = random.nextLong(maxNumber2 - minNumber2 + 1) + minNumber2;

                double minNumber3 = Double.parseDouble(property.getProperty("number3.start"));
                double maxNumber3 = Double.parseDouble(property.getProperty("number3.stop"));
                long decimalPlaces = Long.parseLong(property.getProperty("number3.decimalPlaces"));
                double number3 = minNumber3 + (maxNumber3 - minNumber3) * random.nextDouble();
                number3 = Math.round(number3*Math.pow(10.0, decimalPlaces))/Math.pow(10.0, decimalPlaces);

                System.out.println(number1 + " " + number2 + " " + number3);
            }, 0, delayTime, TimeUnit.SECONDS);

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }

}
