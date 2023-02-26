package com.example.h2so4project

import android.icu.number.NumberFormatter.with
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class HospitalslistActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var hospitalRecyclerView: RecyclerView
    private lateinit var hospitalArrayList: ArrayList<Hospital>
    private lateinit var state : TextView
    private lateinit var distict : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospitalslist)

        hospitalRecyclerView = findViewById(R.id.hospitalslist)
        hospitalRecyclerView.layoutManager = LinearLayoutManager(this)
        hospitalRecyclerView.setHasFixedSize(true)

        hospitalArrayList = arrayListOf<Hospital>()

        val states = findViewById<TextView>(R.id.hp_state)
        val disticts = findViewById<TextView>(R.id.hp_distict)

        val lState = intent.getStringExtra("tState")
        val lDistict  = intent.getStringExtra("tDistict")



        Log.d("Get data", lState + " " + lDistict)
        // set the data into view
        states.text = lState
        disticts.text = lDistict

        getHospitalData()
    }

    private fun getHospitalData() {

//        val St : String = "2JCRUo1niSU3OWBBb240CANkCrw1"
//        val image : ImageView = findViewById(R.id.hp_image)

        distict = findViewById(R.id.hp_distict)
        state = findViewById(R.id.state)

        dbref = FirebaseDatabase.getInstance().getReference("Details")

        dbref.addValueEventListener(object :ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    hospitalArrayList.clear()
                    for(postsnapshot in snapshot.children)
                    {
                        val user = postsnapshot.child("hospital_details").getValue(Hospital::class.java)

                        user?.uid = postsnapshot.key

                        if((distict.text.toString()).equals(user!!.district))
                        {
                            hospitalArrayList.add(user)
                        }
//                        if((distict.text.toString()).equals(user!!.district))
//                            hospitalArrayList.add(user)

                    }
                }

                hospitalRecyclerView.adapter = MyAdapter(hospitalArrayList, this@HospitalslistActivity)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

//        val image : ImageView = findViewById(R.id.hp_image)
//
//        dbref = FirebaseDatabase.getInstance().getReference("Details").child("hospital_details")
//
//
//        dbref.child("profile").addValueEventListener(object :ValueEventListener{
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if(snapshot.exists())
//                {
//
//                    val link  = snapshot.getValue(Hospital::class.java)
//
//                  //  Picasso.get().load(link?.profile).into(image)
//                    hospitalArrayList.add(link)
////
//
//                    }
//                }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//
//            })

    }
}