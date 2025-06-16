package com.example.seminarfirstdemoapp;

import static com.example.seminarfirstdemoapp.AppConsts.*;

public class Model
{
    // 1 Board  - 2D Array
    private int[][] board;
    private int turnCounter = 0;

    public Model() {
        board = new int[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    // check Win
    // update Board (row,column)
    // is valid move (row,column)
    // TIE - turns are over and no one has won
    // reset Board







    // 2 currentPlayer




}
