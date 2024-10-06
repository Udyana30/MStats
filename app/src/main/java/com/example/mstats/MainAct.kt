package com.example.mstats

import PlayerModel
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mstats.databinding.ActivityMainBinding

class MainAct : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var playerAdapter: PlayerAdapter

    private val filters = mutableListOf(
        FilterModel("ALL", emptyList()),
        FilterModel("Attackers", listOf("ST", "LW", "RW", "ALL ROLE")),
        FilterModel("Midfielders", listOf("CM", "CAM", "RM", "LM", "ALL ROLE")),
        FilterModel("Defenders", listOf("CB", "RB", "LB", "ALL ROLE")),
        FilterModel("Goalkeepers", listOf("GK", "ALL ROLE"))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val players = PlayerRepository.players

        playerAdapter = PlayerAdapter(players)
        binding.recyclerPlayers.apply {
            layoutManager = LinearLayoutManager(this@MainAct)
            adapter = playerAdapter
        }

        val filterAdapter = FilterAdapter(this, filters) { selectedFilter ->
            filterPlayers(selectedFilter, players)
        }

        binding.recyclerFilter.apply {
            layoutManager = LinearLayoutManager(this@MainAct, LinearLayoutManager.HORIZONTAL, false)
            adapter = filterAdapter
        }

        binding.dropdownMenu.setOnClickListener { view ->
            showPopupMenu(view)
        }

    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menu.add("About")

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.title) {
                "About" -> {
                    startActivity(Intent(this, AboutAct::class.java))
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    private fun filterPlayers(selectedFilter: FilterModel, allPlayers: List<PlayerModel>) {
        val filteredPlayers = if (selectedFilter.name == "ALL") {
            allPlayers
        } else {
            allPlayers.filter { player ->
                val playerPositions = player.position.split(", ").map { it.trim() }
                playerPositions.any { position -> selectedFilter.position.contains(position) }
            }
        }
        playerAdapter.updatePlayers(filteredPlayers)
    }

}