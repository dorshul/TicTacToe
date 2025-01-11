package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
444440=import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.logic.GameLogic
import com.example.tictactoe.model.Cell

class MainActivity : AppCompatActivity() {

    private lateinit var gameLogic: GameLogic
    private lateinit var cellToImageViewMap: MutableMap<Cell, ImageView>
    private var currentToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        gameLogic = GameLogic()
        cellToImageViewMap = mutableMapOf()

        val board = gameLogic.getBoard()
        val ids = arrayOf(
            arrayOf(findViewById(R.id.main_activity_image00), findViewById(R.id.main_activity_image01), findViewById(R.id.main_activity_image02)),
            arrayOf(findViewById(R.id.main_activity_image10), findViewById(R.id.main_activity_image11), findViewById(R.id.main_activity_image12)),
            arrayOf(findViewById(R.id.main_activity_image20), findViewById(R.id.main_activity_image21), findViewById(R.id.main_activity_image22))
        )

        for (i in board.indices) {
            for (j in board[i].indices) {
                val cell = board[i][j]
                val imageView = findViewById<ImageView>(ids[i][j])
                cellToImageViewMap[cell] = imageView

                imageView.setOnClickListener {
                    onCellClick(cell, imageView)
                }
            }
        }

        findViewById<View>(R.id.main_activity_restart_button).setOnClickListener {
            resetBoard()
        }
    }

    private fun onCellClick(cell: Cell, imageView: ImageView) {
        if (!gameLogic.makeMove(cell.row, cell.col)) {
            showToast("Cell Already filled")
            return
        }

        // Update the UI
        imageView.setImageResource(if (gameLogic.currentPlayer == "X") R.drawable.o else R.drawable.x)

        if (gameLogic.checkWin(cell.row, cell.col)) {
            endGame("${gameLogic.currentPlayer} wins!")
            return
        }

        if (gameLogic.isDraw()) {
            endGame("It's a draw!")
            return
        }

        gameLogic.switchPlayer()
    }

    private fun endGame(message: String) {
        showToast(message)
        for ((cell, imageView) in cellToImageViewMap) {
            imageView.isEnabled = false
        }
        findViewById<View>(R.id.main_activity_restart_button).visibility = View.VISIBLE
    }

    private fun resetBoard() {
        gameLogic.resetGame()
        for ((cell, imageView) in cellToImageViewMap) {
            imageView.setImageResource(R.drawable.empty_cell)
            imageView.isEnabled = true
        }
        findViewById<View>(R.id.main_activity_restart_button).visibility = View.GONE
    }

    private fun showToast(message: String) {
        currentToast?.cancel()
        currentToast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        currentToast!!.show()
    }
}