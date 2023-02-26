package com.example.h2so4project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.google.firebase.database.*

class MainActivity2 : AppCompatActivity() {

    var database: FirebaseDatabase? = null
    var reference: DatabaseReference? = null
    var st_list: ArrayList<String?> = ArrayList()
    var dist_list: ArrayList<String?> = ArrayList()
    var sel_dist: String? = null
    var sel_state:kotlin.String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        database = FirebaseDatabase.getInstance()
        val st_arrayAdapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
            this@MainActivity2,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            st_list as List<String?>
        )
        val dist_arrayAdapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
            this@MainActivity2,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            dist_list as List<String?>
        )
        database!!.reference.child("Details").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                st_list.clear()
                dist_list.clear()
                for (dataSnapshot in snapshot.children) {
                    val model: Hospital? = dataSnapshot.child("hospital_details")
                        .getValue(Hospital::class.java)

                    if ((st_list.contains(model?.state))) {}
                      else {
                        st_list.add(model?.state)
                    }

                        if(dist_list.contains(model?.district)){}
                        else {
                            dist_list.add(model?.district)
                        }
                }
                st_arrayAdapter.notifyDataSetChanged()
                dist_arrayAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {}
        })

        st_arrayAdapter.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item)
        val st_spinner = findViewById<Spinner>(R.id.state_spinner)
        st_spinner.adapter = st_arrayAdapter



        st_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                sel_state = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }



        dist_arrayAdapter.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item)
        val dist_spinner = findViewById<Spinner>(R.id.disct_spinner)
        dist_spinner.adapter = dist_arrayAdapter

        dist_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                //                sel_dist=parent.getItemAtPosition(position).toString();
                sel_dist = dist_list[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val srch_button = findViewById<Button>(R.id.search_bt)

        srch_button.setOnClickListener {
            val intent = Intent(this@MainActivity2, HospitalslistActivity::class.java)
            Log.d("gettm", sel_state + "")
            intent.putExtra("tState", sel_state)
            intent.putExtra("tDistict", sel_dist)
            startActivity(intent)
        }


    }
}