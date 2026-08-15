package com.rifttracker.designsystem.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Single Activity, mesmo padrão do app real: só hospeda o Fragment via
 * FragmentContainerView (declarado no layout com android:name), sem
 * Navigation Component porque é uma tela só.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
