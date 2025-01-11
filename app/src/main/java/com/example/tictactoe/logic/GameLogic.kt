package com.example.tictactoe.logic

import com.example.tictactoe.model.Cell

class GameLogic {

    private val boardSize = 3
    private var board: Array<Array<Cell>> = Array(boardSize) { row ->
        Array(boardSize) { col -> Cell(row, col) }
    }

    var currentPlayer = "X"
        private set
    private var movesCount = 0

    fun getBoard(): Array<Array<Cell>> = board

    fun makeMove(row: Int, col: Int): Boolean {
        val cell = board[row][col]
        if (cell.value.isNotEmpty()) return false // Invalid move (cell already occupied)

        cell.value = currentPlayer
        movesCount++
        return true
    }

    fun checkWin(row: Int, col: Int): Boolean {
        val currentSymbol = currentPlayer

        // Check row
        if (board[row].all { it.value == currentSymbol }) return true

        // Check column
        if (board.all { it[col].value == currentSymbol }) return true

        // Check diagonal (top-left to bottom-right)
        if (row == col && board.indices.all { board[it][it].value == currentSymbol }) return true

        // Check diagonal (top-right to bottom-left)
        if (row + col == boardSize - 1 && board.indices.all { board[it][boardSize - 1 - it].value == currentSymbol }) return true

        return false
    }

    fun isDraw(): Boolean = movesCount == boardSize * boardSize

    fun switchPlayer() {
        currentPlayer = if (currentPlayer == "X") "O" else "X"
    }

    fun resetGame() {
        for (row in board) {
            for (cell in row) {
                cell.value = ""
            }
        }
        currentPlayer = "X"
        movesCount = 0
    }
}