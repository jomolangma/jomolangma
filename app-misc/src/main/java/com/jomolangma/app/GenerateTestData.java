package com.jomolangma.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Created by ZhangLijun on 9/19/16.
 */
public class GenerateTestData {
    public static void main(String[] args) throws IOException {
        StringBuilder builder = new StringBuilder();
        File file = new File("./data.dat");
        if (file.exists()){
            file.delete();
        }

        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        Random random = new Random();
        for (int i=1; i<80000000; i++){
            builder.append(random.nextInt(2016) + 1)
                    .append(",")
                    .append(random.nextInt(12) + 1)
                    .append(",")
                    .append(random.nextInt(1000));

            bufferedWriter.write(builder.toString());
            bufferedWriter.newLine();

            builder.setLength(0);
        }

        builder.append(random.nextInt(2016) + 1)
                .append(",")
                .append(random.nextInt(12) + 1)
                .append(",")
                .append(random.nextInt(1000));

        bufferedWriter.write(builder.toString());
        bufferedWriter.close();
    }
}
