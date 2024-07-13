package com.example.golans_ex2;

import java.util.Random;

public class GameManager {

    private int[][] main_IMG_cones;
    private int[][] main_IMG_coins;
    private int[] main_IMG_racingCars;
    private int numOfCols;
    private int numOfRows;
    private int finalScore;
    private static final int COINS_SCORE = 100;

    private int numOfCrashes = 0;
    private int lifeCount;
    private int playerIndex;
    private Random random = new Random();

    public GameManager() {
        this(8, 3, 3);
    }

    public GameManager(int rows, int cols, int lifeCount) {
        this.lifeCount = lifeCount;
        numOfRows = rows;
        numOfCols = cols;
        main_IMG_cones = new int[rows][cols];
        main_IMG_coins = new int[rows][cols];
        main_IMG_racingCars = new int[cols];
        coneAndRacingCarStartingPosition(rows, cols);
        playerIndex = cols / 2;
        finalScore = 0;
    }

    private void coneAndRacingCarStartingPosition(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                main_IMG_cones[i][j] = 0;
                main_IMG_coins[i][j] = 0;
                if (i == 0) {
                    if (j == cols / 2) {
                        main_IMG_racingCars[j] = 1;
                    } else {
                        main_IMG_racingCars[j] = 0;
                    }
                }
            }
        }
    }

    public int[][] getMain_IMG_coins() {
        return main_IMG_coins;
    }

    public GameManager setMain_IMG_coins(int[][] main_IMG_coins) {
        this.main_IMG_coins = main_IMG_coins;
        return this;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public GameManager setFinalScore(int finalScore) {
        this.finalScore = finalScore;
        return this;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public GameManager setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
        return this;
    }

    public int[] getMain_IMG_racingCars() {
        return main_IMG_racingCars;
    }

    public GameManager setMain_IMG_racingCars(int[] main_IMG_racingCars) {
        this.main_IMG_racingCars = main_IMG_racingCars;
        return this;
    }

    public int[][] getMain_IMG_cones() {
        return main_IMG_cones;
    }

    public GameManager setMain_IMG_cones(int[][] main_IMG_cones) {
        this.main_IMG_cones = main_IMG_cones;
        return this;
    }

    public int getNumOfCols() {
        return numOfCols;
    }

    public GameManager setNumOfCols(int numOfCols) {
        this.numOfCols = numOfCols;
        return this;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public GameManager setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
        return this;
    }

    public void setLifeCount(int lifeCount) {
        this.lifeCount = lifeCount;
    }

    public int getNumOfCrashes() {
        return numOfCrashes;
    }

    public void setNumOfCrashes(int numOfCrashes) {
        this.numOfCrashes = numOfCrashes;
    }

    public int getLifeCount() {
        return lifeCount;
    }

    public boolean isGameLost() {
        return getLifeCount() == getNumOfCrashes();
    }

    public boolean isCrash() {
        for (int i = 0; i < getNumOfCols(); i++) {
            if (main_IMG_cones[getNumOfRows() - 1][i] == 1 && main_IMG_racingCars[i] == 1) {
                numOfCrashes++;
                return true;
            }
            if (main_IMG_coins[getNumOfRows() - 1][i] == 1 && main_IMG_racingCars[i] == 1) {
                finalScore += COINS_SCORE;
                return false;
            }
        }
        return false;
    }

    public void racingCarMovingRight() {
        if (main_IMG_racingCars[getNumOfCols() - 1] == 1) {
            main_IMG_racingCars[getNumOfCols() - 1] = 0;
            main_IMG_racingCars[0] = 1;
        } else {
            for (int i = 0; i < (getNumOfCols() - 1); i++) {
                if (main_IMG_racingCars[i] == 1) {
                    main_IMG_racingCars[i] = 0;
                    main_IMG_racingCars[i + 1] = 1;
                    break;
                }
            }
        }
        isCrash();
    }

    public void racingCarMovingLeft() {
        if (main_IMG_racingCars[0] == 1) {
            main_IMG_racingCars[0] = 0;
            main_IMG_racingCars[getNumOfCols() - 1] = 1;
        } else {
            for (int i = 1; i < getNumOfCols(); i++) {
                if (main_IMG_racingCars[i] == 1) {
                    main_IMG_racingCars[i] = 0;
                    main_IMG_racingCars[i - 1] = 1;
                }
            }
        }
        isCrash();
    }

    public void coinsAndConesMoving(long sec) {
        for (int i = numOfRows - 1; i >= 0; i--) {
            for (int j = numOfCols - 1; j >= 0; j--) {
                if (main_IMG_cones[i][j] == 1 && i == numOfRows - 1) {
                    main_IMG_cones[i][j] = 0;
                }
                if (main_IMG_coins[i][j] == 1 && i == numOfRows - 1) {
                    main_IMG_coins[i][j] = 0;
                }
                if (main_IMG_cones[i][j] == 1) {
                    main_IMG_cones[i][j] = 0;
                    main_IMG_cones[i + 1][j] = 1;
                }
                if (main_IMG_coins[i][j] == 1) {
                    main_IMG_coins[i][j] = 0;
                    main_IMG_coins[i + 1][j] = 1;
                }
            }
        }
        if (sec % 2 == 0) {
            startNewConeAndCoin();
        }
    }

    private void startNewConeAndCoin() {
        int randomColumnCone = 0;
        int randomColumnCoin = 0;
        while (randomColumnCone == randomColumnCoin) {
            randomColumnCone = random.nextInt(numOfCols);
            randomColumnCoin = random.nextInt(numOfCols);
        }
        main_IMG_cones[0][randomColumnCone] = 1;
        main_IMG_coins[0][randomColumnCoin] = 1;
    }
}
