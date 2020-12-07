package com.ucb.drawchallenge

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val cameraRequestCode = 1
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), cameraRequestCode)


        val button_challenge: Button = findViewById(R.id.button_challenge)
        button_challenge.setOnClickListener {
            sortChallenge()
        }
    }


    private fun sortChallenge(){

        var randomInt = Random().nextInt(6)+1

        val showChallenge = when (randomInt){

            1 -> draw_challenge.text = "Desenhe alguém que você ame!"
            2 -> draw_challenge.text = "Desenhe uma cena de um filme!"
            3 -> draw_challenge.text = "Desenhe um personagem de animação!"
            4 -> draw_challenge.text = "Desenhe um atleta famoso!"
            5 -> draw_challenge.text = "Desenhe um músico famoso!"
            else -> draw_challenge.text = "Estou sem ideias, desenhe o que vier a mente!"}
        button_challenge.visibility = View.GONE
        button_camera.visibility = View.VISIBLE

        imageView = findViewById(R.id.imgView)

        button_camera.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, cameraRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode  == cameraRequestCode){
            val foto: Bitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(foto)
        }
    }


}