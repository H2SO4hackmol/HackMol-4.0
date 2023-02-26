package com.example.h2so4project

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso


class DrAdapter(public val listOfDoctor: ArrayList<Hospital>,val context: Context) : RecyclerView.Adapter<DrAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dr_item,parent,false)
        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: DrAdapter.MyViewHolder, position: Int) {
        val user  = listOfDoctor[position]

        holder.dr_name.text = user.username
        holder.dr_spec.text = user.specialization
        holder.dr_email.text = user.email
    }


    override fun getItemCount(): Int {
        return listOfDoctor.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dr_name : TextView = itemView.findViewById(R.id.dr_name)
        val dr_spec : TextView = itemView.findViewById(R.id.dr_Spec)
        val dr_email : TextView = itemView.findViewById(R.id.dr_email)
    }

}