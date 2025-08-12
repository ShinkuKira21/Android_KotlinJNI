package com.example.tools

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.testapplicationc.R
import com.example.testapplicationc.ui.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.jvm.java

class FragmentHandler(private val activity: FragmentActivity) {
    val fragmentManager = activity.supportFragmentManager

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = false, backStackName: String? = null)
    {
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragmentGroupLayout, fragment)

        if(addToBackStack) fragmentTransaction.addToBackStack(backStackName)

        fragmentTransaction.commit()
    }

    fun setupBackStackListener(botNavBar: BottomNavigationView?) {
        fragmentManager.addOnBackStackChangedListener {
            val fragment =
                activity.supportFragmentManager.findFragmentById(R.id.fragmentGroupLayout)

            if(botNavBar is BottomNavigationView)
            {
                when (fragment) {
                    is HomeFragment -> botNavBar.menu.findItem(R.id.menuHome).isChecked = true
                }
            }
        }
    }

    fun setupLaunchActivityButton(view: View, btnId: Int, targetActivity: Class<*>)
    {
        val btn = view.findViewById<Button>(btnId)

        btn.setOnClickListener {
            if (Activity::class.java.isAssignableFrom(targetActivity)) {
                val intent = Intent(activity, targetActivity)
                activity.startActivity(intent)
                activity.finish()
            }
        }
    }
}