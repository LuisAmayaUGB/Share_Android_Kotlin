package com.example.shareandroidkotlin

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var uriString: String
    var hasPackage:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)






        btnShare.setOnClickListener {
            //Get text from TextView and store in variable "s"
            val s = textView.text.toString()
            //Intent to share the text
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, s);
            startActivity(Intent.createChooser(shareIntent,"Compartir vía"))
        }


        shareFacebook.setOnClickListener{
            startActivity(getOpenFacebookIntent());
 }



        btnwhatsap.setOnClickListener{
            val message = messageEditText.text.toString()

// Calling the function
            sendMessage(message);
        }


    }










    private fun checkFacebookPackage(context: Context, target:String):Boolean{
        val pm=context.packageManager
        try {

            pm.getPackageInfo(target, PackageManager.GET_META_DATA)
        }catch (exp:PackageManager.NameNotFoundException)
        {
            return false
        }
        return true
    }




    fun sendMessage(message:String){

        // Creating intent with action send
        val intent = Intent(Intent.ACTION_SEND)

        // Setting Intent type
        intent.type = "text/plain"

        // Setting whatsapp package name
        intent.setPackage("com.whatsapp")

        // Give your message here
        intent.putExtra(Intent.EXTRA_TEXT, message)

        // Checking whether whatsapp is installed or not
        if (intent.resolveActivity(packageManager) == null) {
            Toast.makeText(this,
                "Please install whatsapp first.",
                Toast.LENGTH_SHORT).show()
            return
        }

        // Starting Whatsapp
        startActivity(intent)
    }




    private fun getOpenFacebookIntent(): Intent? {
        return try {
            packageManager.getPackageInfo("com.facebook.katana", 0)
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Taller-Mauricio-113842120533035"))
        } catch (e: Exception) {
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/tallerMauricio"))
        }
    }



}