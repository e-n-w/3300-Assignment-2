package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calcText = findViewById<EditText>(R.id.calc_text)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val button0 = findViewById<Button>(R.id.button0)
        val buttondivide = findViewById<Button>(R.id.buttondivide)
        val buttonmultiply = findViewById<Button>(R.id.buttonmultiply)
        val buttonminus = findViewById<Button>(R.id.buttonminus)
        val buttonplus = findViewById<Button>(R.id.buttonplus)

        val buttonequals = findViewById<Button>(R.id.buttonequals)

        val buttonList = listOf(
            button1,
            button2,
            button3,
            button4,
            button5,
            button6,
            button7,
            button8,
            button9,
            button0,
            buttondivide,
            buttonmultiply,
            buttonplus,
            buttonminus
        )

        for(button in buttonList){
            button.setOnClickListener{
                calcText.append(button.text)
            }
        }

        buttonequals.setOnClickListener {
            calcText.setText(calculate(calcText.text.toString()))
        }

    }
}

fun calculate(str: String) : String{
    if(str.equals("ERR")){
        return "ERR"
    }
    for(i in str.indices){
        if(str[i] == '*' || str[i] == '/'){
            var j = i
            var k = i
            while(j >= 0 && (str[j] != '+' || str[j] != '-' )) {
                j--
            }
            while(k < str.length && (str[k] != '*' || str[k] != '/' || str[k] != '+' || str[k] != '-' )){
                k++
            }
            val left = str.substring(j + 1, i)
            val right = str.substring(i + 1, k)
            if(i + 1 == k || j + 1 == i){
                return "ERR"
            }
            var retStr = StringBuilder(str)
            return try{
                if(str[i] == '*'){
                    val result = left.toDouble()*right.toDouble()
                    retStr.delete(j + 1, k)
                    retStr = retStr.insert(j + 1, result.toString())
                    calculate(retStr.toString())
                }else{
                    if(right.toDouble() == 0.0){
                        return "ERR"
                    }
                    val result = left.toDouble()/right.toDouble()
                    retStr.delete(j + 1, k)
                    retStr = retStr.insert(j + 1, result.toString())
                    calculate(retStr.toString())
                }
            }catch(e: NumberFormatException){
                "ERR"
            }
        }
    }
    for(i in str.indices){
        if(str[i] == '+' || str[i] == '-'){
            val left = calculate(str.substring(0, i))
            val right = calculate(str.substring(i + 1))
            return try{
                if(str[i] == '+'){
                    val result = left.toDouble() + right.toDouble()
                    result.toString()
                }else{
                    val result = left.toDouble() - right.toDouble()
                    result.toString()
                }
            }catch(e:NumberFormatException){
                "ERR"
            }
        }
    }
    return try{
        str
    }catch (e: NumberFormatException){
        "ERR"
    }
}