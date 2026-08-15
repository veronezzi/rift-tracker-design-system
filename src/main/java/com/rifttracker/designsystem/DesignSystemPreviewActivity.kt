package com.rifttracker.designsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.rifttracker.designsystem.databinding.ActivityDesignSystemPreviewBinding

/**
 * Vitrine de todo componente do design system numa tela só. Vive em
 * src/main/ (não src/debug/) porque uma dependência publicada via Maven só
 * carrega a variante release — sem ícone de launcher de propósito, abra via:
 * adb shell am start -n <applicationId>/com.rifttracker.designsystem.DesignSystemPreviewActivity
 */
class DesignSystemPreviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDesignSystemPreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDesignSystemPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        BottomSheetBehavior.from(binding.sheet).state = BottomSheetBehavior.STATE_COLLAPSED
    }
}
