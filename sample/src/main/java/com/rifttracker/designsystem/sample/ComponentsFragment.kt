package com.rifttracker.designsystem.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.rifttracker.designsystem.sample.databinding.FragmentComponentsBinding

/**
 * Vitrine de todo componente do design system, portada da
 * DesignSystemPreviewActivity (módulo :) pro app de exemplo, hospedada
 * como Fragment na MainActivity: busca + lista + revelar ao tocar (acordeão).
 */
class ComponentsFragment : Fragment() {

    private var _binding: FragmentComponentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentComponentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BottomSheetBehavior.from(binding.sheet).state = BottomSheetBehavior.STATE_COLLAPSED

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

        rows.forEach { (_, _, headerAndDetail) ->
            val (header, detail) = headerAndDetail
            header.setOnClickListener {
                detail.visibility = if (detail.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            }
        }

        binding.searchInput.addTextChangedListener { text ->
            val query = text?.toString().orEmpty()
            rows.forEach { (name, item, _) ->
                item.visibility = if (name.contains(query, ignoreCase = true)) View.VISIBLE else View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
