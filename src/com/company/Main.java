package com.company;

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        System.out.println(maker(args));
    }
    public static String maker(String[] args) {
        String s;
        double kilo = 1024d;
        double summarySize = 0d;
        boolean flagH = false;
        boolean flagC = false;
        Map<String, String> result = new HashMap<>();
        for (String arg : args) {
            switch (arg) {
                case "-h" -> flagH = true;
                case "-c" -> flagC = true;
                case "--si" -> kilo = 1000d;
                default -> {
                    File file = new File(arg);
                    double size = sizeOfFile(file, kilo, flagH);
                    if (size == -1d) {
                        System.err.println("Файл не найден");
                        System.exit(1);
                    };
                    if (flagC) summarySize += size;
                    else {
                        if (flagH) result.put(arg, humanSize(size, kilo));
                        else result.put(arg, Double.toString(size));
                    }
                }
            }
        }
        if (flagC) {
            if (flagH) s = (humanSize(summarySize, kilo));
            else s = String.valueOf(summarySize);
        } else {
            StringBuilder sBuilder = new StringBuilder();
            for (String arg : result.keySet()) {
                sBuilder.append(arg).append(" ").append(result.get(arg));
                sBuilder.append("\n");
            }
            s = sBuilder.toString();
        }
        return s;
    }
    public static double sizeOfFile(File file, double kilo, boolean flagH) {
        double size = 0d;
        if (file.exists()) {
            if (file.isFile()) size = file.length();
            else {
                for (File folderFile : Objects.requireNonNull(file.listFiles())) size += sizeOfFile(folderFile, kilo, true);
            }
            if (!flagH) size = Math.round(size / kilo * 100.0) / 100.0;
        } else size = -1d;
        return size;
    }

    public static String humanSize(double size, double kilo) {
        boolean takeItShorter = true;
        int count = 0;
        String[] unit = {" B", " KB", " MB", " GB"};
        while (takeItShorter)
            if (size >= kilo - 1) {
                size /= kilo;
                count += 1;
            } else takeItShorter = false;
        size = Math.round(size * 100.0) / 100.0;
        return (size + unit[count]);
    }
}
