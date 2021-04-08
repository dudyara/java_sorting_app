package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String [] args) throws IOException {

        ArrayList<String> readList = new ArrayList<>(); // список строк для чтения
        ArrayList<Integer> intList = new ArrayList<>(); // список для сортировки int
        ArrayList<String> routeList = new ArrayList<>(); // список исходных файлов

        String out = ""; // конечный файл

        if (args.length < 3) // проверка на количество аргументов
            System.out.print("Неверные аргументы\n");
        else if ((args[0].equals("-a")) || (args[0].equals("-d"))) { // находим конечный и исходные файлы
            out = args[2];
            routeList.addAll(Arrays.asList(args).subList(3, args.length));
        } else { // если первый аргумент пропущен
            out = args[1];
            routeList.addAll(Arrays.asList(args).subList(2, args.length));
        }

        for (String s : routeList) { // считывание из файлов
            try {
                File file = new File(s);
                FileReader fr = new FileReader("C:/Users/"+System.getProperty("user.name")+"/" + file);
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();
                while (line != null) {
                    readList.add(line);
                    line = reader.readLine();
                }
            } catch (FileNotFoundException e) {
                System.out.print("Файл не найден\n");
            }
        }

        try {
            if ((args[0].equals("-i")) || (args[1].equals("-i"))) { // для int
                for (String s : readList) intList.add(Integer.parseInt(s));// переписываем в другой список int

                for (int i = intList.size() - 1; i > 0; i--) // пузырек
                    for (int j = 0; j < i; j++) {
                        if (args[0].equals("-d")) { // если сортировка по убыванию
                            if (intList.get(j) < intList.get(j + 1)) {
                                int tmp = intList.get(j);
                                intList.set(j, intList.get(j + 1));
                                intList.set(j + 1, tmp);
                            }
                        } else {  // если сортировка по возрастанию
                            if (intList.get(j) > intList.get(j + 1)) {
                                int tmp = intList.get(j);
                                intList.set(j, intList.get(j + 1));
                                intList.set(j + 1, tmp);
                            }
                        }
                    }

                try { // запись в файл
                    FileWriter writer = new FileWriter("C:/Users/"+System.getProperty("user.name")+"/" + out);
                    for (Integer integer : intList)
                        writer.write(integer + System.getProperty("line.separator"));
                    writer.close();
                } catch (FileNotFoundException e) {
                    System.out.print("Файл не найден\n");
                    }
            }
            else if ((args[0].equals("-s")) || (args[1].equals("-s"))) // для string
            {
                for (int i = readList.size() - 1; i > 0; i--) // пузырек для string
                    for (int j = 0; j < i; j++) {
                        if (args[0].equals("-d")) { // если сортировка по убыванию
                            if (readList.get(j).compareTo(readList.get(j + 1)) < 0) {
                                String tmp = readList.get(j);
                                readList.set(j, readList.get(j + 1));
                                readList.set(j + 1, tmp);
                            }
                        } else { // если сортировка по возрастанию
                            if (readList.get(j).compareTo(readList.get(j + 1)) >= 0) {
                                String tmp = readList.get(j);
                                readList.set(j, readList.get(j + 1));
                                readList.set(j + 1, tmp);
                            }
                        }
                    }

                try { // запись в файл
                    FileWriter writer = new FileWriter("C:/Users/"+System.getProperty("user.name")+"/" + out);
                    for (String s : readList)
                        writer.write(s + System.getProperty("line.separator"));
                    writer.close();
                } catch (FileNotFoundException e) {
                    System.out.print("Файл не найден\n");
                }
            }
            else
                System.out.print("Неверные аргументы\n");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("Индекс за границей массива\n");
        }
        catch (NumberFormatException e) {
            System.out.print("Ошибка типов данных\n");
        }
    }
}
