package com.example.h2so4project

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sResult: String
    private lateinit var dResult: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val state = arrayOf<String?>("Uttar Pradesh", "Delhi", "Bihar", "Uttra Khand")
        val district = arrayOf<String?>("Prayagraj", "Varanasi", "Muzaffarnagar", "Lucknow")
        val st_arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this@MainActivity,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            state
        )
        st_arrayAdapter.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item)
        val st_spinner = findViewById<Spinner>(R.id.state_spinner)
        st_spinner.adapter = st_arrayAdapter
        st_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {

                sResult = parent?.getItemAtPosition(position).toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        val dist_arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this@MainActivity,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            district
        )
        dist_arrayAdapter.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item)
        val dist_spinner = findViewById<Spinner>(R.id.disct_spinner)
        dist_spinner.adapter = dist_arrayAdapter as SpinnerAdapter?
        dist_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {

               dResult = parent?.getItemAtPosition(position).toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val search : Button = findViewById(R.id.search_bt)

        search.setOnClickListener {
            val state : String = sResult
            val distict : String = dResult

            val intent = Intent(this,HospitalslistActivity::class.java)

            intent.putExtra("tstate",state)
            intent.putExtra("tDistict",distict)

            this.startActivity(intent)

        }

    }

}