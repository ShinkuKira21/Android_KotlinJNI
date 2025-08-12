package com.example.testapplicationc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testapplicationc.databinding.ActivityMainBinding
import com.example.testapplicationc.ui.HomeFragment
import com.example.testapplicationc.ui.ProfileFragment
import com.example.testapplicationc.ui.SettingsFragment
import com.example.tools.FragmentHandler

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentHandler = FragmentHandler(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragmentHandler.replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.menuHome -> fragmentHandler.replaceFragment(HomeFragment())
                R.id.menuProfile -> fragmentHandler.replaceFragment(ProfileFragment())
                R.id.menuSettings -> fragmentHandler.replaceFragment(SettingsFragment())
            }
            true
        }
    }
}