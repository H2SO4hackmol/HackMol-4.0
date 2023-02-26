package com.example.h2so4project

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.icu.util.HebrewCalendar.AV
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import java.util.Objects

class LastActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var fdb : FirebaseDatabase
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var drArrayList: ArrayList<Hospital>
    private lateinit var drRecyclerView: RecyclerView


//    private lateinit var arrayList : ArrayList<Hospital>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last)

//        val userUid = FirebaseAuth.getInstance().currentUser?.uid
        val name = findViewById<TextView>(R.id.pr_name)
        val address = findViewById<TextView>(R.id.pr_address)

        val lName = intent.getStringExtra("tName")
        val lAddress  = intent.getStringExtra("tAddress")
        val userUid = intent.getStringExtra("uid")


        // set the data into view
        name.text = lName
        address.text = lAddress

        val gAvalSeat = findViewById<TextView>(R.id.gSeat)
        val dAvalSeat = findViewById<TextView>(R.id.dSeat)
        val pAvalSeat = findViewById<TextView>(R.id.pSeat)
        val gFare = findViewById<TextView>(R.id.gFare)
        val dFare = findViewById<TextView>(R.id.dFare)
        val pFare = findViewById<TextView>(R.id.pFare)


        val gLayout = findViewById<LinearLayout>(R.id.glayout)
        val dLayout = findViewById<LinearLayout>(R.id.dLayout)
        val pLayout = findViewById<LinearLayout>(R.id.pLayout)

        val image : ImageView = findViewById(R.id.pr_image)

        val direction : ImageView = findViewById(R.id.imgDirection)




        dbref = FirebaseDatabase.getInstance().getReference().child("Details")
            .child(userUid!!).child("hospital_details")

        dbref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    val user = snapshot.getValue(Hospital::class.java)
                    Picasso.get().load(user?.profile).into(image)
                    direction.setOnClickListener {

                        val intent = Intent(android.content.Intent.ACTION_VIEW,android.net.Uri.parse(user?.location_link))
                        startActivity(intent)

                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })






        dbref = FirebaseDatabase.getInstance().getReference().child("Details")
            .child(userUid!!).child("ward_details").child("General")

        dbref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                        val user = snapshot.getValue(Hospital::class.java)
                        gAvalSeat.text = user?.available
                        gFare.text = user?.charges
                }
                else {
                    gLayout.visibility = View.GONE
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


        dbref = FirebaseDatabase.getInstance().getReference().child("Details")
            .child(userUid!!).child("ward_details").child("Dengue")

        dbref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    val user = snapshot.getValue(Hospital::class.java)
                    dAvalSeat.text = user?.available
                    dFare.text = user?.charges
                }
                else {
                    dLayout.visibility = View.GONE
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        dbref = FirebaseDatabase.getInstance().getReference().child("Details")
            .child(userUid!!).child("ward_details").child("Private")

        dbref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    val user = snapshot.getValue(Hospital::class.java)
                    pAvalSeat.text = user?.available
                    pFare.text = user?.charges
                }
                else {
                    pLayout.visibility = View.GONE
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


//        val drName1 = findViewById<TextView>(R.id.drName1)
//        val drName2 = findViewById<TextView>(R.id.drName2)
//        val drName3 = findViewById<TextView>(R.id.drName3)
//        val drSpe1 = findViewById<TextView>(R.id.drSpe1)
//        val drSpe2 = findViewById<TextView>(R.id.drSpe2)
//        val drSpe3 = findViewById<TextView>(R.id.drSpe3)
//        val drMail1 = findViewById<TextView>(R.id.drMail1)
//        val drMail2 = findViewById<TextView>(R.id.drMail2)
//        val drMail3 = findViewById<TextView>(R.id.drMail3)

        dbref = FirebaseDatabase.getInstance().getReference().child("Details")
            .child(userUid!!).child("doctor_details")

//        val dr_name = findViewById<TextView>(R.id.dr_name)
//        val dr_spec = findViewById<TextView>(R.id.dr_Spec)
//        val dr_email = findViewById<TextView>(R.id.dr_email)
        val drRecyclerView = findViewById<RecyclerView>(R.id.doctorList)
        drRecyclerView.layoutManager = LinearLayoutManager(this)
        drRecyclerView.setHasFixedSize(true)

        drArrayList = arrayListOf<Hospital>()

        if(this::drArrayList.isInitialized) {

            dbref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
//                    drArrayList.clear()
                        for (postsnapshot in snapshot.children) {
                            val user = postsnapshot.getValue(Hospital::class.java)

                        user?.uid = postsnapshot.key

//                        if((distict.text.toString()).equals(user!!.district))
                            drArrayList.add(user!!)

                        }
                    }

                    drRecyclerView.adapter = DrAdapter(drArrayList, this@LastActivity)
//                else {
//                    pLayout.visibility = View.GONE
//                }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

        }
        val ratingStar = findViewById<RatingBar>(R.id.ratingBar)

        dbref = FirebaseDatabase.getInstance().getReference()
            .child("Details").child(userUid!!).child("hospital_details").child("feedback")

//            .child("rishabhtest")

        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists())
                {
                    val user : Float? = snapshot.getValue(Float::class.java)
                    if (user != null) {
                        ratingStar.rating = user
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

     //  ratingStar.setOnRatingBarChangeListener { ratingBar, fl, b -> dbref.setValue(fl)}
            dbref = FirebaseDatabase.getInstance().getReference()
                .child("Details").child(userUid!!).child("hospital_details").child("rated")

            dbref.addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists())
                    {
                        snapshot.child(userUid+"")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        }




//        ratingStar.setOnRatingBarChangeListener { ratingBar, fl, b ->  }
//        dbref.addValueEventListener(object :ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if(snapshot.exists())
//                {
//                   ratingStar.rating.snap
//                }
//                else {
//                    pLayout.visibility = View.GONE
//                }
//            }
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })

//        dbref = FirebaseDatabase.getInstance().getReference()
//            .child("Details").child(userUid!!).child("doctor_details")
//            .child("vikas")
//
//        dbref.addValueEventListener(object :ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if(snapshot.exists())
//                {
//                    val user = snapshot.getValue(Hospital::class.java)
//                    drName3.text = user?.username
//                    drSpe3.text = user?.specialization
//                    drMail3.text = user?.email
//                }
//                else {
//                    pLayout.visibility = View.GONE
//                }
//            }
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })







//        dbref = FirebaseDatabase.getInstance().getReference().child("Details").child(userUid!!).child("ward_details").child("General").child("available")
//
//        dbref.addValueEventListener(object :ValueEventListener{
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if(snapshot.exists())
//                {
//
//                    gAvalSeat.text = snapshot.getValue().toString()
//
////                    for(postsnapshot in snapshot.children)
////                    {
//
////                        if(postsnapshot.key?.equals("availabe") == true){
////                            var data = postsnapshot.getValue()
//
////                        }
//
////                         val user = postsnapshot.getValue(Hospital::class.java)
////
////                        gAvalSeat.text= user?.General.toString()
////                        dAvalSeat.text = snapshot.getValue().toString()
//
////                    }
//                }
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })

//        dbref = FirebaseDatabase.getInstance().getReference().child("Details").child(uid).child("ward_details").child("Dengue").child("available")
//
//        dbref.addValueEventListener(object :ValueEventListener{
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if(snapshot.exists())
//                {
//                    dAvalSeat.text = snapshot.getValue().toString()
//
//                }
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })




//        firebaseAuth = FirebaseAuth.getInstance()
//        val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference(firebaseAuth!!.uid!!)
//        databaseReference.addValueEventListener(object :ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val user = snapshot.getValue(Hospital::class.java)
//                gAvalSeat.text = user?.General.toString()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//               Toast.makeText(this@LastActivity,"tumse na ho payega" + error.code,Toast.LENGTH_SHORT).show()
//            }
//        })
//
//


//        dbref =
//            dbref.child("Details").document(userUid!!).get()
//                .addOnSuccessListener { document ->
//                    if(document != null)
//                    {
//                        val  g = document.data!!["General"].toString()
//                    }
//                    // Write was successful!
//
//                    // ...
//                }
//                .addOnFailureListener {
//                    // Write failed
//                    // ...
//                }

//        getDataOfHospitals(userUid)
//        getDataOfHospitals()

    }

//    private fun getDataOfHospitals() {
//
//        // fetch seats data from firebase
//        val gAvalSeat = findViewById<TextView>(R.id.gSeat)
//        val dAvalSeat = findViewById<TextView>(R.id.dSeat)
//
//        dbref = FirebaseDatabase.getInstance().getReference("ward details")
////        dbref.child("Details").child(uid!!)
//
//        dbref.addValueEventListener(object : ValueEventListener {
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if(snapshot.exists())
//                {
//                    for(postsnapshot in snapshot.children)
//                    {
//                        var userg = postsnapshot.child("General").getValue().toString()
////                        var userp = postsnapshot.child("ward details").child("dengue").getValue().toString()
////                        val user = postsnapshot.getValue(Hospital::class.java)
////                            arrayList.add(user!!)
////
//                        gAvalSeat.text = userg
////                        dAvalSeat.text = userp
////                        dAvalSeat.text = user?.dengue.toString()
//                    }
//                }
//
////                hospitalRecyclerView.adapter = MyAdapter(hospitalArrayList, this@HospitalslistActivity)
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })
//
//    }
