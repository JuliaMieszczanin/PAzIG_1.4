package com.example.pazig

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Patient : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database2: DatabaseReference
    private lateinit var mailD: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)

        val uid = FirebaseAuth.getInstance().currentUser!!.email
        mailD=uid?.subSequence(0, 5).toString()

        //get email
        val i = intent
        val email = i.getStringExtra("one").toString()

        //go to mydata
        val patdata: Button =findViewById(R.id.myPerdata)
        patdata.setOnClickListener {
            val gotopatdata=Intent(this, PatientData::class.java)
            gotopatdata.putExtra("two", mailD)
            startActivity(gotopatdata)
        }
        //go to uploaddata
        val upldata: Button =findViewById(R.id.uploaddata)
        upldata.setOnClickListener {
            val gotoupldata=Intent(this, UploadData::class.java)
            gotoupldata.putExtra("uploadtwo", mailD)
            startActivity(gotoupldata)
        }
        //go to appointments
        val app: Button =findViewById(R.id.Pappointments)
        app.setOnClickListener {
            val gotoapp = Intent(this, Appointments::class.java)
            gotoapp.putExtra("aone", mailD)
            startActivity(gotoapp)

        }
        firebaseAuth= FirebaseAuth.getInstance()
        val logout: Button=findViewById(R.id.logoutPat)
        logout.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}