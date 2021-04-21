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
        String s = "";
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
                    double size = sizeOfFile(arg);
                    if (size == -1d) throw new Error("1");
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
            else s = (summarySize + "B");
        } else for (String arg : result.keySet()) {
            s += arg + " " + result.get(arg);
            if (!flagH) s += " B";
            s += "\n";
        }
        return s;
    }
    public static double sizeOfFile(String nameOfFile) {
        double size = 0d;
        File file = new File(nameOfFile);
        if (file.exists()) {
            if (file.isFile()) size = file.length();
            else {
                for (File folderFile : Objects.requireNonNull(file.listFiles())) size += sizeOfFile(folderFile.toString());
            }
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
