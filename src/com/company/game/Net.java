package com.company.game;

import com.company.exception.IncorrectArgumentException;
import com.company.exception.OccupiedSiteException;

import java.util.Scanner;

public class Net {
    private String[] array = new String[]{" - ", " - ", " - ", " - ", " - ", " - ", " - ", " - ", " - "};
    private final Scanner scanner;

    public Net(Scanner scanner) {
        this.scanner = scanner;
    }

    private boolean isFirstPlayer = true;

    public void printNet() {
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            counter++;
            if (counter % 3 == 0) {
                System.out.println();
                counter = 0;
            }
        }
    }

    public boolean checkWin() {
        if (array[0].equals(array[1]) && array[1].equals(array[2]) && !array[0].equals(" - ") ||
                array[3].equals(array[4]) && array[4].equals(array[5]) && !array[3].equals(" - ") ||
                array[6].equals(array[7]) && array[7].equals(array[8]) && !array[6].equals(" - ") ||
                array[0].equals(array[3]) && array[3].equals(array[6]) && !array[0].equals(" - ") ||
                array[1].equals(array[4]) && array[4].equals(array[7]) && !array[1].equals(" - ") ||
                array[2].equals(array[5]) && array[5].equals(array[8]) && !array[2].equals(" - ") ||
                array[0].equals(array[4]) && array[4].equals(array[8]) && !array[0].equals(" - ") ||
                array[2].equals(array[4]) && array[4].equals(array[6]) && !array[2].equals(" - ")) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void writeArgument(int index) throws OccupiedSiteException {
        if (array[index - 1].equals(" - ")){
            if (isFirstPlayer){
                array[index - 1] = " X ";
            } else {
                array[index - 1] = " O ";
            }
        } else {
            throw new OccupiedSiteException("Место занято!");
        }
    }

    public void readArgument() throws IncorrectArgumentException, OccupiedSiteException {
        String str = scanner.nextLine();
        if (!isDigit(str)){
            throw new IncorrectArgumentException("Введите число от 1 до 9");
        }
        int index = Integer.parseInt(str);
        if (index < 1 || index > 9){
            throw new IncorrectArgumentException("Введите число от 1 до 9");
        }
        writeArgument(index);
    }

    public void startGame() {

        System.out.println("Первый игрок - Х");
        System.out.println("Второй игрок - О");
        System.out.println();

        do {
            if (isFirstPlayer) {
                System.out.println("Ход первого игрока");
            } else {
                System.out.println("Ход второго игрока");
            }
            printNet();
            try {
                readArgument();
            } catch (IncorrectArgumentException e) {
                e.printStackTrace();
                continue;
            } catch (OccupiedSiteException e){
                e.printStackTrace();
                continue;
            }
            isFirstPlayer = !isFirstPlayer;
        } while (!checkWin());
        printNet();

        if (!isFirstPlayer) {
            System.out.println("Первый игрок победил!");
        } else {
            System.out.println("Второй игрок победил!");
        }
    }
}
