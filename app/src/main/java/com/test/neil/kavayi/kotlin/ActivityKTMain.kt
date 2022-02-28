package com.test.neil.kavayi.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.neil.kavayi.R
import com.test.neil.kavayi.kotlin.ui.main.ActivityKTMainFragment

class ActivityKTMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_k_t_main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ActivityKTMainFragment.newInstance())
                    .commitNow()
        }
    }
}