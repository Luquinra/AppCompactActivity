package com.example.appcompactactivity

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appcompactactivity.databinding.ActivityTicTacToeBinding

class TicTacToeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTicTacToeBinding
    private val buttons = Array(3) { arrayOfNulls<Button>(3) }
    private var player1Turn = true
    private var roundCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicTacToeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initializeButtons()
        setupResetButton()
    }

    private fun initializeButtons() {
        buttons[0][0] = binding.button00
        buttons[0][1] = binding.button01
        buttons[0][2] = binding.button02
        buttons[1][0] = binding.button03
        buttons[1][1] = binding.button04
        buttons[1][2] = binding.button05
        buttons[2][0] = binding.button06
        buttons[2][1] = binding.button07
        buttons[2][2] = binding.button08

        for (row in buttons) {
            for (button in row) {
                button?.setOnClickListener {
                    onButtonClick(button)
                }
            }
        }
    }

    private fun setupResetButton() {
        binding.newGame.setOnClickListener {
            resetGame()
        }
    }

    private fun onButtonClick(button: Button?) {
        if (!button?.text.toString().isEmpty()) {
            return
        }

        if (player1Turn) {
            button?.text = "X"
        } else {
            button?.text = "O"
        }

        roundCount++

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins()
            } else {
                player2Wins()
            }
        } else if (roundCount == 9) {
            draw()
        } else {
            player1Turn = !player1Turn
        }
    }

    private fun checkForWin(): Boolean {
        val field = Array(3) { arrayOfNulls<String>(3) }

        for (i in 0 until 3) {
            for (j in 0 until 3) {
                field[i][j] = buttons[i][j]?.text.toString()
            }
        }

        for (i in 0 until 3) {
            if (field[i][0] == field[i][1] && field[i][0] == field[i][2] && !field[i][0]?.isEmpty()!!) {
                return true
            }
            if (field[0][i] == field[1][i] && field[0][i] == field[2][i] && !field[0][i]?.isEmpty()!!) {
                return true
            }
        }

        if (field[0][0] == field[1][1] && field[0][0] == field[2][2] && !field[0][0]?.isEmpty()!!) {
            return true
        }

        return field[0][2] == field[1][1] && field[0][2] == field[2][0] && !field[0][2]?.isEmpty()!!
    }

    private fun player1Wins() {
        showToast("Player 1 wins!")
        resetGame()
    }

    private fun player2Wins() {
        showToast("Player 2 wins!")
        resetGame()
    }

    private fun draw() {
        showToast("It's a draw!")
        resetGame()
    }

    private fun resetGame() {
        for (row in buttons) {
            for (button in row) {
                button?.text = ""
            }
        }
        roundCount = 0
        player1Turn = true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}