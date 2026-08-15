package com.rifttracker.designsystem

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.rifttracker.designsystem.databinding.ActivityDesignSystemPreviewBinding

/**
 * Vitrine de todo componente do design system numa tela só: busca + lista +
 * revelar ao tocar (acordeão). Vive em src/main/ (não src/debug/) porque uma
 * dependência publicada via Maven só carrega a variante release — sem ícone
 * de launcher de propósito, abra via:
 * adb shell am start -n <applicationId>/com.rifttracker.designsystem.DesignSystemPreviewActivity
 */
class DesignSystemPreviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDesignSystemPreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDesignSystemPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sheetBehavior = BottomSheetBehavior.from(binding.sheet)
        sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        val rows = listOf(
            Triple("Cores", binding.itemColors, binding.headerColors to binding.detailColors),
            Triple("Tipografia", binding.itemTypography, binding.headerTypography to binding.detailTypography),
            Triple("Botões", binding.itemButtons, binding.headerButtons to binding.detailButtons),
            Triple("Card", binding.itemCard, binding.headerCard to binding.detailCard),
            Triple("Input", binding.itemInput, binding.headerInput to binding.detailInput),
            Triple("Chips de rank", binding.itemChipsRank, binding.headerChipsRank to binding.detailChipsRank),
            Triple("Chips de região", binding.itemChipsRegion, binding.headerChipsRegion to binding.detailChipsRegion),
            Triple("Bottom sheet", binding.itemBottomSheet, binding.headerBottomSheet to binding.detailBottomSheet),
        )

        rows.forEach { (name, _, headerAndDetail) ->
            val (header, detail) = headerAndDetail
            header.setOnClickListener {
                detail.visibility = if (detail.visibility == View.VISIBLE) View.GONE else View.VISIBLE
                // a linha "Bottom sheet" também expande/colapsa o componente de
                // verdade — é o próprio componente sendo demonstrado, não só texto
                if (name == "Bottom sheet") {
                    sheetBehavior.state = if (sheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                        BottomSheetBehavior.STATE_COLLAPSED
                    } else {
                        BottomSheetBehavior.STATE_EXPANDED
                    }
                }
            }
        }

        binding.searchInput.addTextChangedListener { text ->
            val query = text?.toString().orEmpty()
            rows.forEach { (name, item, _) ->
                item.visibility = if (name.contains(query, ignoreCase = true)) View.VISIBLE else View.GONE
            }
        }
    }
}
