package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var board: Array<Array<ImageView>>
    private var currentPlayer = "X"
    private var movesCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Initialize the 3x3 board
        board = arrayOf(
            arrayOf(findViewById(R.id.main_activity_image00), findViewById(R.id.main_activity_image01), findViewById(R.id.main_activity_image02)),
            arrayOf(findViewById(R.id.main_activity_image10), findViewById(R.id.main_activity_image11), findViewById(R.id.main_activity_image12)),
            arrayOf(findViewById(R.id.main_activity_image20), findViewById(R.id.main_activity_image21), findViewById(R.id.main_activity_image22))
        )

        // Set click listeners for each ImageView
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j].setOnClickListener {
                    onCellClick(board[i][j], i, j)
                }
            }
        }

        // Set reset button
        findViewById<View>(R.id.main_activity_restart_button).setOnClickListener {
            resetBoard()
        }
    }

    private fun onCellClick(cell: ImageView, row: Int, col: Int) {
        // TODO
    }

    private fun checkWin(row: Int, col: Int): Boolean {
        // TODO
        return false
    }

    private fun endGame() {
        // TODO
    }

    private fun resetBoard() {
        for (row in board) {
            for (cell in row) {
                cell.setImageResource(R.drawable.empty_cell)
                cell.isEnabled = true
            }
        }
        currentPlayer = "X"
        movesCount = 0
        findViewById<View>(R.id.main_activity_restart_button).visibility = View.GONE
    }
}