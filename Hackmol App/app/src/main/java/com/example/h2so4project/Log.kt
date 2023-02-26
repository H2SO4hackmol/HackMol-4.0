package com.example.h2so4project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Log : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword : EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var dbref : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignUp = findViewById(R.id.btnSignUp)

        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val email=edtEmail.text.toString()
            val password = edtPassword.text.toString()

            login(email,password)

        }

    }

    private fun login(email:String,password:String)
    {
        dbref = FirebaseDatabase.getInstance().getReference().child("User")


        dbref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for (postsnapshot in snapshot.children)
                    {
                        val user = postsnapshot.getValue(User::class.java)
                        if(user?.email.equals(email))
                        {
//                            Log.d("getmail", user?.email+"  ")
                            if(user?.password.equals(password)){
//                                Log.d("getpas", user?.password+"  ")
                                val intent = Intent(this@Log, MainActivity2::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }
                        else{
                            Toast.makeText(this@Log, "User does not exist", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
//                else {
//                    gLayout.visibility = View.GONE
//                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

//        private fun login(email: String, password: String) {
//            //Logic for login user
//            if(this::mAuth.isInitialized)
//            {
//            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this)
//            { task ->
//                if (task.isSuccessful) {
//                    // code for logging user
//                    val intent = Intent(this, Log::class.java)
//                    startActivity(intent)
//                    finish()
//                } else {
//                    Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
//                }
//
//            }
//        }
    }

}