package com.example.testapplicationc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.testapplicationc.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBackStackListener()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.home -> replaceFragment(Home())
                R.id.profile -> replaceFragment(Profile())
                R.id.settings -> replaceFragment(Settings())

                else -> { }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment)
    {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    private fun setupBackStackListener() {
        supportFragmentManager.addOnBackStackChangedListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.frame_layout)
            val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

            when (fragment) {
                is Home -> bottomNav.menu.findItem(R.id.home).isChecked = true
                is Profile -> bottomNav.menu.findItem(R.id.profile).isChecked = true
                is Settings -> bottomNav.menu.findItem(R.id.settings).isChecked = true
            }
        }
    }
}