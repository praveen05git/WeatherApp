package com.hencesimplified.weatherapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlin.math.E

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val searchBtn=findViewById<FloatingActionButton>(R.id.floatingSearch)
        val searchtxt=findViewById<EditText>(R.id.txtSearch)
        val cardViewShake= findViewById<CardView>(R.id.cardview)
        val shakeAnimation = AnimationUtils.loadAnimation(applicationContext,R.anim.shake)


        searchBtn.setOnClickListener{

            val cityName=searchtxt.text.toString().toUpperCase()

            if(cityName.equals(""))
            {
                cardViewShake.startAnimation(shakeAnimation)
                //Toast.makeText(applicationContext,"Enter City Name",Toast.LENGTH_SHORT).show()
                Snackbar.make(it,"Enter City Name",Snackbar.LENGTH_SHORT).setBackgroundTint(Color.parseColor("#96bb7c")).setTextColor(Color.parseColor("#ffffff")).show()
            }
            else
            {
                val intent = Intent(this, weather_page::class.java)
                // To pass any data to next activity
                intent.putExtra("cityName", cityName)
                // start your next activity
                startActivity(intent)
                overridePendingTransition(R.anim.right_enter,R.anim.left_out)
            }

        }

    }

    override fun onBackPressed()
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Warning!")
        builder.setMessage("Are you sure, you want to exit?")
        builder.setPositiveButton("Yes"){dialogInterface, i ->
            val ExitIntent = Intent(Intent.ACTION_MAIN)
            ExitIntent.addCategory(Intent.CATEGORY_HOME)
            ExitIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(ExitIntent)
        }
        builder.setNegativeButton("No"){dialogInterface, i ->
            dialogInterface.cancel();
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()

    }

}

