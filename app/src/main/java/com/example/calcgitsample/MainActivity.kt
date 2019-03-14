package com.example.calcgitsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.keisan)


        button.setOnClickListener {

            //未入力チェック用
            var isValid = true

            val tankaInput = findViewById<EditText>(R.id.tanka)
            val tankaText = tankaInput.text.toString()

            if (tankaText.isEmpty()) {
                // 単価が未入力の場合
                tankaInput.error = getString(R.string.tanka_err)
                isValid = false
            }

            val suInput = findViewById<EditText>(R.id.su)
            val suText = suInput.text.toString()

            if (suText.isEmpty()) {
                // 数量が未入力の場合
                suInput.error = getString(R.string.su_err)
                isValid = false
            }



            if (isValid) {

                //合計金額を算出 （数量が10以上なら1割引きに変更）
                val nebikikake = 0.9    //掛け率
                val su =10              //値引判定用の数量
                var nebikiflg = false  //値引チェック用フラグ
                val kingaku: Int          //合計金額格納用

                if(suText.toInt() >= su){
                    nebikiflg = true
                }

                if(nebikiflg){
                    //値引きありのとき
                    kingaku = (tankaText.toInt() * suText.toInt() * nebikikake).toInt()
                }else{
                    //値引きなしのとき
                    kingaku = tankaText.toInt() * suText.toInt()
                }

                //トーストで合計金額を表示
                Toast.makeText(applicationContext,"合計金額は${kingaku.toString()}円です",Toast.LENGTH_LONG).show()

            }


        }


    }


}
