package com.example.retrofit_postdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit

import android.R
import android.content.DialogInterface
import android.view.View
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    
    private var mobilenumber: EditText? = null
    private var password: EditText? = null
    private var post: Button? = null
    private var response: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initializing our views
        mobilenumber = findViewById(R.id.ed_mobilenumber)
        password = findViewById(R.id.ed_password)
        post = findViewById(R.id.post)
        response = findViewById(R.id.response)


        // adding on click listener to our button.
        post.setOnClickListener(object : DialogInterface.OnClickListener() {
            fun onClick(v: View?) {
                // validating if the text field is empty or not.
                if (mobilenumber.getText().toString().isEmpty() && password.getText().toString()
                        .isEmpty()
                ) {
                    Toast.makeText(
                        this@MainActivity,
                        "Please enter both the values",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }
                // calling a method to post the data and passing our name and job.
                postData(mobilenumber.getText().toString(), password.getText().toString())
            }
        })
    }

    private fun postData(mobilenumber: String, password: String) {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.mrmoasilha.com")
            .addConverterFactory(GsonConverterFactory.create()) // at last we are building our retrofit builder.
            .build()
        // below line is to create an instance for our retrofit api class.
        val retrofitAPI: RestApi = retrofit.create(RestApi::class.java)

        // passing data from our text fields to our modal class.
        val modal = UserInfo(
            deviceMac =,
            deviceId =,
            deviceModel =,
            deviceName =,
            language =,
            iPAddress =,
            pushNotificationToken =,
            cabLatitude =,
            cabLongitude =,
            loginType =,
        )

        // calling a method to create a post and passing our modal class.
        val call: Call<UserInfo> = retrofitAPI.createPost(modal)

        // on below line we are executing our method.
        call.enqueue(object : Callback<UserInfo?>() {
            fun onResponse(call: Call<UserInfo?>?, response: Response<UserInfo?>) {
                // this method is called when we get response from our api.
                Toast.makeText(this@MainActivity, "Data added to API", Toast.LENGTH_SHORT).show()

                // on below line we are setting empty text
                // to our both edit text.
                password!!.setText("")
                mobilenumber!!.setText("")

                // we are getting response from our body 
                // and passing it to our modal class.
                val responseFromAPI: UserInfo? = response.body()

                // on below line we are getting our data from modal class and adding it to our string.
                val responseString = """
                       Response Code : ${response.code().toString()}
                       timeStamp : ${responseFromAPI}.2021-08-13 13:07:11().toString()}
                       name : ${responseFromAPI.Trinadh()}
                       """.trimIndent()

                // below line we are setting our 
                // string to our text view.
                UserInfo!!.text = responseString
            }

            fun onFailure(call: Call<UserInfo?>?, t: Throwable) {
                // setting text to our text view when 
                // we get error response from API.
                UserInfo!!.text = "Error found is : " + t.message
            }
        })
    }
}