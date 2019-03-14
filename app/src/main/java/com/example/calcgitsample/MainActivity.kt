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

                //合計金額を算出 （数量が10以上なら1割引き、50以上なら2割引き）
                val nebikikake1 = 0.9    //掛け率1
                val nebikikake2 = 0.8    //掛け率2
                val su1 =10              //値引判定用の数量1
                val su2 =50              //値引判定用の数量2
                var nebikiflg = false  //値引チェック用のフラグ
                val kingaku: Int        //合計金額格納用

                if(suText.toInt() >= su1){
                    nebikiflg = true
                }

                if(nebikiflg){
                    //値引きありのとき
                    if(suText.toInt() >= su2){
                        //2割引きのとき
                        kingaku = (tankaText.toInt() * suText.toInt() * nebikikake2).toInt()
                    }else {
                        //1割引きのとき
                        kingaku = (tankaText.toInt() * suText.toInt() * nebikikake1).toInt()
                    }
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
