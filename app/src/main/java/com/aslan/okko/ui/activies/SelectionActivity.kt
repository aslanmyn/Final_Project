package com.aslan.okko.ui.activies

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.aslan.okko.R
import com.aslan.okko.databinding.ActivitySelectionBinding
import com.aslan.okko.viewmodel.SelectionViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class SelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectionBinding
    private lateinit var viewModel: SelectionViewModel
    private lateinit var sp: SharedPreferences
    private lateinit var bottomNavigationView:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        sp=getSharedPreferences("entryInformation", MODE_PRIVATE)

         bottomNavigationView = binding.bottomNavView

        viewModel = ViewModelProvider(this)[SelectionViewModel::class.java]

        binding.bottomNavView.background = null
        bottomNavigationView.menu.getItem(1).isEnabled = false

        setupBottomNavigation()
        showPP()





        binding.fab.setOnClickListener {
            logOut()
        }

    }

    private fun setupBottomNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavView)

        val navController = findNavController(R.id.fragmentContainerView)

        bottomNavigationView.setOnItemSelectedListener { item ->
            val destination = when (item.itemId) {
                R.id.dividedFragment -> R.id.dividedFragment
                R.id.favouritesFragment -> R.id.favouritesFragment
                else -> null
            }

            destination?.let {
                navController.navigate(it)
                true
            } ?: false
        }
    }

    private fun logOut(){

        val editor=sp.edit()
        val takenId=sp.getString("userName","")


       //Alert Dialog
       val builder = AlertDialog.Builder(this)
        builder.setTitle("Экран журнала")
        builder.setMessage("Имя пользователя: $takenId Вы находитесь в своей учетной записи, нажмите «Продолжить», чтобы выйти.")

        builder.setPositiveButton("Продолжать") { dialog, which ->

            editor.remove("userName")
            editor.apply()
            viewModel.deleteAllFromRoom(application)
            startActivity(Intent(this, MainActivity::class.java))


            Toast.makeText(this, "$takenId вышел из аккаунта", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        builder.setNegativeButton("Закрывать") { dialog, which ->


            Toast.makeText(this, "Диалоговое окно оповещения закрыто", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun showPP(){
        val imageUri = intent.getStringExtra("imageUri")
        if (imageUri != null) {
            val selectedImage = Uri.parse(imageUri)
            binding.profileP.setImageURI(selectedImage)
        }

    }

}