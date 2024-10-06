package com.example.mstats

import PlayerModel
import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mstats.databinding.ItemPlayerBinding

class PlayerAdapter(private var players: List<PlayerModel>) :
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(players[position])
    }

    override fun getItemCount(): Int {
        return players.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updatePlayers(filteredPlayers: List<PlayerModel>) {
        players = filteredPlayers
        notifyDataSetChanged()
    }

    inner class PlayerViewHolder(private val binding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(player: PlayerModel) {
            binding.overallRate.text = player.overall
            binding.namePlayer.text = player.name
            binding.numberPlayer.text = player.number
            binding.positionPlayer.text = player.position
            binding.profilePlayer.setImageResource(player.imageResId)
            binding.flag.setImageResource(player.flagResId)
            binding.footPlayer.text = player.foot

            binding.root.setOnClickListener {
                val context = binding.root.context
                Toast.makeText(context, "Clicked: ${player.name}", Toast.LENGTH_SHORT).show()

                val intent = Intent(context, DetailAct::class.java).apply {
                    putExtra("PLAYER_MODEL", player)
                }
                context.startActivity(intent)
            }
        }
    }
}
