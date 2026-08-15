package com.rifttracker.designsystem.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.rifttracker.designsystem.R
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
        val sheetBehavior = BottomSheetBehavior.from(binding.sheet)
        sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        // dados de exemplo pro card de rank incluído — os mesmos números do
        // mockup aprovado (68 PDL, 55% de vitórias), só pra vitrine mostrar
        // algo plausível, não é dado de feature de verdade
        binding.rankCardPreview.rankPdl.text = "68 PDL"
        binding.rankCardPreview.rankRecord.text = "142V 118D · 260 partidas"
        binding.rankCardPreview.rankWinSegment.layoutParams =
            (binding.rankCardPreview.rankWinSegment.layoutParams as android.widget.LinearLayout.LayoutParams).apply {
                weight = 55f
            }
        binding.rankCardPreview.rankLossSpacer.layoutParams =
            (binding.rankCardPreview.rankLossSpacer.layoutParams as android.widget.LinearLayout.LayoutParams).apply {
                weight = 45f
            }
        binding.rankCardPreview.rankWinrateLabel.text = "55% de vitórias"

        binding.statCardWin.statValue.text = "61%"
        binding.statCardWin.statLabel.text = "Vitórias"
        binding.statCardDifficulty.statValue.text = "4/5"
        binding.statCardDifficulty.statLabel.text = "Dificuldade"

        // dados de exemplo pros novos componentes de tracker (histórico,
        // maestria, estado vazio) — mesma ideia dos exemplos acima, só pra
        // vitrine mostrar algo plausível
        binding.matchHistoryPreview.matchChampionName.text = "Ahri"
        binding.matchHistoryPreview.matchKda.text = "7/2/5"
        binding.matchHistoryPreview.matchTimeAgo.text = "há 2h"

        binding.championMasteryPreview.masteryChampionName.text = "Ahri"
        binding.championMasteryPreview.masteryPoints.text = "245.320 pontos"
        binding.championMasteryPreview.masteryLevelBadge.text = "M7"

        binding.skeletonPreview.skeletonRoot.startAnimation(
            AnimationUtils.loadAnimation(requireContext(), R.anim.skeleton_pulse),
        )

        binding.emptyStatePreview.emptyStateTitle.text = "Invocador não encontrado"
        binding.emptyStatePreview.emptyStateMessage.text = "Confira o Riot ID e a região e tente de novo."
        binding.emptyStatePreview.emptyStateRetryButton.visibility = View.VISIBLE

        val rows = listOf(
            Triple("Cores", binding.itemColors, binding.headerColors to binding.detailColors),
            Triple("Tipografia", binding.itemTypography, binding.headerTypography to binding.detailTypography),
            Triple("Botões", binding.itemButtons, binding.headerButtons to binding.detailButtons),
            Triple("Card", binding.itemCard, binding.headerCard to binding.detailCard),
            Triple("Input", binding.itemInput, binding.headerInput to binding.detailInput),
            Triple("Chips de rank", binding.itemChipsRank, binding.headerChipsRank to binding.detailChipsRank),
            Triple("Chips de região", binding.itemChipsRegion, binding.headerChipsRegion to binding.detailChipsRegion),
            Triple("Card de rank", binding.itemRankCard, binding.headerRankCard to binding.detailRankCard),
            Triple("Card de partida", binding.itemMatchCard, binding.headerMatchCard to binding.detailMatchCard),
            Triple(
                "Componentes de campeão",
                binding.itemChampionParts,
                binding.headerChampionParts to binding.detailChampionParts,
            ),
            Triple("Barra de winrate", binding.itemWinrateBar, binding.headerWinrateBar to binding.detailWinrateBar),
            Triple("Bottom navigation", binding.itemBottomNav, binding.headerBottomNav to binding.detailBottomNav),
            Triple("Bottom sheet", binding.itemBottomSheet, binding.headerBottomSheet to binding.detailBottomSheet),
            Triple(
                "Item de histórico de partida",
                binding.itemMatchHistory,
                binding.headerMatchHistory to binding.detailMatchHistory,
            ),
            Triple(
                "Card de maestria de campeão",
                binding.itemChampionMastery,
                binding.headerChampionMastery to binding.detailChampionMastery,
            ),
            Triple("Skeleton de carregamento", binding.itemSkeleton, binding.headerSkeleton to binding.detailSkeleton),
            Triple("Estado vazio/erro", binding.itemEmptyState, binding.headerEmptyState to binding.detailEmptyState),
            Triple("Avatar", binding.itemAvatar, binding.headerAvatar to binding.detailAvatar),
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
