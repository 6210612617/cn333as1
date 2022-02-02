package com.example.numberguessinggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.Math.abs
import java.util.*


class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var btnSubmit  : Button
    private lateinit var btnTryAgain  : Button
    private lateinit var tvHint: TextView
    private lateinit var etGuess: EditText
    private  lateinit var  tvCount : TextView
    private  lateinit var  tvAns : TextView
    private  var realAns =0
    private  var count2 = 0
    private  var tempcount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSubmit = findViewById<Button>(R.id.btnSubmit)
        btnTryAgain = findViewById<Button>(R.id.btnTryAgain)
        tvHint = findViewById(R.id.tvHint)
        etGuess = findViewById(R.id.etGuess)
        tvCount = findViewById(R.id.tvCount)
        tvAns = findViewById(R.id.tvAns)


        btnSubmit.setOnClickListener {
            CheckAnswer(true)
        }
        btnTryAgain.setOnClickListener{
            tempcount = count
            count = 0
            TryAgain(true)

        }
        if (count == 0){
            GuessNumber()
        }

    }
    public fun GuessNumber(){
        val r = Random()
        realAns = r.nextInt(1000)
        if (realAns == 0){
            GuessNumber()
        }

    }

    private fun CheckAnswer(isBtnSubmitSelect : Boolean){
        val ans = etGuess.text.toString().toInt()
        val dif = ans-realAns
        if (ans == realAns){
            tvHint.text = "You Win"
            btnTryAgain.text = "Try Again"
            btnSubmit.setEnabled(false)
            tvAns.text = "Answer is : $realAns "
            count2++
        }
        else if((abs(dif) == 1) or (abs(dif) == 2) ){
            tvHint.text = " Hint : Closer!!"
            count++
        }
        else if(abs(dif) <= 10){
            tvHint.text = " Hint : in range!!"
            count++
        }
        else if(abs(dif) <= 100){
            tvHint.text = " Hint : getting close!"
            count++
        }
        else if (ans > realAns) {
            tvHint.text = " Hint : It's Higher!"
            count++
        }
        else{
            tvHint.text = " Hint : It's lower!"
            count++
        }
        tvCount.text = "Count : $count"


    }
    private fun TryAgain(isTryAgainselect : Boolean){
        if(count2 == 0){
            tvAns.text = "Answer is : $realAns "
            tvCount.text = "Count : $tempcount"
            btnTryAgain.text = "Next to Reset"
            tvHint.text = " Let's Try Again"
            count2++
            btnSubmit.setEnabled(false)
        }
        else{
            etGuess.getText().clear();
            tvHint.text = "Let's Guess"
            tvAns.text = "  "
            btnTryAgain.text = "Reset Game"
            count2 = 0
            tvCount.text = "Count : $count"
            btnSubmit.setEnabled(true)
            GuessNumber()
        }



    }


}