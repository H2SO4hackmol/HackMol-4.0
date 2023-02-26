package com.example.h2so4project

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso


class MyAdapter(public val listOfHospital: ArrayList<Hospital>,val context: Context) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hospital_item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = listOfHospital[position]

        holder.name.text = currentitem.name
        holder.address.text = currentitem.address
        val rate:Float? =currentitem.feedback
        if (rate != null) {
            holder.ratingBar.rating=rate
        }

        Picasso.get().load(currentitem?.profile).into(holder.image)




        // sending data to another activity
        holder.itemView.setOnClickListener {

            // get position of selected item
            val userUid = FirebaseAuth.getInstance().uid
            val model = listOfHospital.get(position)

            //get name and address of selected item with intent
            val gName : String? = model.name
            val gaddress : String? = model.address
            val gUid : String? = model.uid

            // create intent in kotlin
            val intent : Intent = Intent(context,LastActivity::class.java)

            intent.putExtra("tName",gName)
            intent.putExtra("tAddress",gaddress)
            intent.putExtra("uid",gUid)

            context.startActivity(intent)

        }


    }

    override fun getItemCount(): Int {
        return listOfHospital.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.findViewById(R.id.hp_name)
        val address : TextView = itemView.findViewById(R.id.hp_address)
        val image : ImageView = itemView.findViewById(R.id.hp_image)
        val ratingBar:RatingBar=itemView.findViewById(R.id.ry_ratingBar)
    }
}