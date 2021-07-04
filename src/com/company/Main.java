package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //1.создать переменные полей (2 шт. размером 10х10) и сопуствующие переменные
        //2.очистить массивы компа и человека (заполнить пустотой)
        //3.расставить однопалубные корабли на полях компа и человека (кол-во кораблей соответсвует размеру поля)
        //4.ход человека (выстрел в поле компа путём ввода кординат, при успехе повторить ход)
        //5.ход компа (выстрел в поле человека путём рандома кординат, при успехе повторить ход)
        //6.проверка после каждого выстрела на поббеду одного из игроков, если кто-то выиграл, то закончить игру иначе перейти на шаг 4

        final char EMPTY_CELL = '.';
        final char SHIP_CELL = 'K';
        final char MISS_CELL = 'O';
        final char DEAD_CELL = 'X';

        final int USER_STEP = 1;
        final int COMP_STEP = 2;

        final String USER_WIN = "Победил игрок";
        final String COMP_WIN = "Победил компьютер";

        Random random = new Random();

        int fieldSize = 2;
        char compField[][] = new char[fieldSize][fieldSize];
        char userField[][] = new char[fieldSize][fieldSize];

        int countCompShips = fieldSize;
        int countUserShips = fieldSize;

        int iCell = 0, jCell = 0;

        boolean isPlay = true;
        int step = USER_STEP;
        boolean isCorrectInput = true;
        String winner = "";

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                compField[i][j] = EMPTY_CELL;
                userField[i][j] = EMPTY_CELL;
            }
        }

        for (int k = 0; k < countCompShips; k++) {
            do {
                iCell = random.nextInt(fieldSize);
                jCell = random.nextInt(fieldSize);
            } while (compField[iCell][jCell] != EMPTY_CELL);

            compField[iCell][jCell] = SHIP_CELL;
        }

        for (int k = 0; k < countUserShips; k++) {
            do {
                iCell = random.nextInt(fieldSize);
                jCell = random.nextInt(fieldSize);
            } while (userField[iCell][jCell] != EMPTY_CELL);

            userField[iCell][jCell] = SHIP_CELL;
        }

        while (isPlay == true) {
            for (int k = 0; k < 50; k++) {
                System.out.println();
            }

            System.out.println("Поле игрока:");
            for (int i = 0; i < fieldSize; i++) {
                for (int j = 0; j < fieldSize; j++) {
                    System.out.printf("%-2c", userField[i][j]);
                }
                System.out.println();
            }

            System.out.println();

            System.out.println("Поле компьютера:");
            for (int i = 0; i < fieldSize; i++) {
                for (int j = 0; j < fieldSize; j++) {
                    if (compField[i][j] == SHIP_CELL) {
                        System.out.printf("%-2c", EMPTY_CELL);
                    } else {
                        System.out.printf("%-2c", compField[i][j]);
                    }
                }
                System.out.println();
            }

            System.out.println();

            switch (step) {
                case USER_STEP:
                    System.out.println("Ход игрока");

                    do {
                        isCorrectInput = true;
                        Scanner input = new Scanner(System.in);

                        try {
                            System.out.print("Введите i: ");
                            iCell = input.nextInt();

                            System.out.print("Введите j: ");
                            jCell = input.nextInt();
                        } catch (Exception e) {
                            isCorrectInput = false;
                            continue;
                        }

                        if (iCell < 0 || iCell > fieldSize - 1 || jCell < 0 || jCell > fieldSize - 1) {
                            isCorrectInput = false;
                            continue;
                        }

                        if (compField[iCell][jCell] == DEAD_CELL || compField[iCell][jCell] == MISS_CELL) {
                            isCorrectInput = false;
                            continue;
                        }

                    } while (isCorrectInput == false);

                    if (compField[iCell][jCell] == EMPTY_CELL) {
                        compField[iCell][jCell] = MISS_CELL;
                        step = COMP_STEP;
                    } else if (compField[iCell][jCell] == SHIP_CELL) {
                        compField[iCell][jCell] = DEAD_CELL;
                        countCompShips--;
                    }

                    break;
                case COMP_STEP:
                    System.out.println("Ход компьютера");
                    System.out.println("Для продолжения нажмите <Enter>");

                    Scanner input = new Scanner(System.in);
                    input.nextLine();

                    do {
                        isCorrectInput = true;

                        iCell = random.nextInt(fieldSize);
                        jCell = random.nextInt(fieldSize);

                        if (userField[iCell][jCell] == DEAD_CELL || userField[iCell][jCell] == MISS_CELL) {
                            isCorrectInput = false;
                            continue;
                        }

                    } while (isCorrectInput == false);

                    if (userField[iCell][jCell] == EMPTY_CELL) {
                        userField[iCell][jCell] = MISS_CELL;
                        step = USER_STEP;
                    } else if (userField[iCell][jCell] == SHIP_CELL) {
                        userField[iCell][jCell] = DEAD_CELL;
                        countUserShips--;
                    }
                    break;
            }

            if (countCompShips == 0) {
                winner = USER_WIN;
                isPlay = false;
            } else if (countUserShips == 0) {
                winner = COMP_WIN;
                isPlay = false;
            }
        }

        for (int k = 0; k < 50; k++) {
            System.out.println();
        }

        System.out.println(winner);

        System.out.println("Поле игрока:");
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                System.out.printf("%-2c", userField[i][j]);
            }
            System.out.println();
        }

        System.out.println();

        System.out.println("Поле компьютера:");
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                System.out.printf("%-2c", compField[i][j]);
            }
            System.out.println();
        }
    }
}