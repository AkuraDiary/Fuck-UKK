package com.readthefuckingmanual.fuckukk.ui.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.readthefuckingmanual.fuckukk.R
import com.readthefuckingmanual.fuckukk.data.source.preferences.UserPreferences
import com.readthefuckingmanual.fuckukk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    private val userPreferences by lazy { UserPreferences(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        greet()
    }

    fun greet(){
        binding?.textView?.text = "Hello ${userPreferences.getSession().username}"
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}