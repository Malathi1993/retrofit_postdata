package com.example.retrofit_postdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.mlkit.vision.text.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity2 : AppCompatActivity() {

    private var editTextmobile: EditText? = null
    private var editTextpassword: EditText? = null
    private var buttonSubmit: Button? = null
    private var Response: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // initializing our views
        editTextmobile  = findViewById(R.id.ed_mobilenumber)
        editTextpassword = findViewById(R.id.ed_password)
        PostTOAPI = findViewById(R.id.postdatabtn)
        Response = findViewById(R.id.idTVResponse)

        // adding on click listener to our button.
        PostTOAPI?.setOnClickListener(View.OnClickListener { // validating if the text field is empty or not.
            if (editTextmobile?.getText().toString().isEmpty() && editTextpassword?.getText().toString().isEmpty()) {
                Toast.makeText(
                    this@MainActivity2,
                    "Please enter both the values",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }
            // calling a method to post the data and passing our name and job.
            postData(editTextmobile?.getText().toString(), editTextpassword?.getText().toString())
        })
    }

    private fun postData(mobile: String, password: String) {

        // on below line we are creating a retrofit
        // builder and passing our base url
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/") // as we are sending data in json format so
            // we have to add Gson converter factory
            .addConverterFactory(GsonConverterFactory.create()) // at last we are building our retrofit builder.
            .build()
        // below line is to create an instance for our retrofit api class.
        val retrofitAPI: RestApi = retrofit.create(RestApi::class.java)

        // passing data from our text fields to our modal class.
        //val modal = UserInfo( val deviceType: String, val deviceModel: Int,val deviceId: Int, val deviceName: String,val language: String, val iPAddress: Int, val pushNotificationToken: String,val cabLatitude: Int,val cabLongitude: Int, val loginType: String )

        // calling a method to create a post and passing our modal class.
        val call: Call<UserInfo> = retrofitAPI.createPost(modal)

        // on below line we are executing our method.
        call.enqueue(object : Callback<UserInfo?> {
            override fun onResponse(call: Call<UserInfo?>, response: Response<UserInfo?>) {
                // this method is called when we get response from our api.
                Toast.makeText(this@MainActivity2, "Data added to API", Toast.LENGTH_SHORT).show()

                // below line is for hiding our progress bar.

                // on below line we are setting empty text
                // to our both edit text.
                editTextmobile.Text("")
                editTextpassword.Text("")

                // we are getting response from our body
                // and passing it to our modal class.
                val responseFromAPI: UserInfo? = response.body()

                // on below line we are getting our data from modal class and adding it to our string.
                val responseString = """
                    Response Code : ${response.code()}
                    timeStamp : ${responseFromAPI.toString()}
                    name : ${responseFromAPI.toString()}
                    type : ${responseFromAPI.toString()}
                    status : ${responseFromAPI.toString()}
                    token : ${responseFromAPI.toString()}
                    """.trimIndent()

                // below line we are setting our
                // string to our text view.
                Response!!.text = responseString
            }

            override fun onFailure(call: Call<UserInfo?>, t: Throwable) {
                // setting text to our text view when
                // we get error response from API.
                Response!!.text = "Error found is : " + t.message
            }
        })
    }
}
