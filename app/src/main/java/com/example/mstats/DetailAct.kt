package com.example.mstats

import PlayerModel
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mstats.databinding.ActivityDetailBinding

@Suppress("DEPRECATION")
class DetailAct : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val player = intent.getParcelableExtra<PlayerModel>(EXTRA_PLAYER_MODEL)

        player?.let {
            binding.overallRate.text = it.overall
            binding.namePlayer.text = it.name
            binding.numberPlayer.text = it.number
            binding.positionPlayer.text = it.position
            binding.flag.setImageResource(it.flagResId)
            binding.playerProfile.setImageResource(it.imageResId)
            binding.agePlayer.text = it.age
            binding.footPlayer.text = it.foot
            binding.heightPlayer.text = it.height
            binding.weightPlayer.text = it.weight
            binding.contractPlayer.text = it.contract
            binding.detail.text = it.description
        }

        //Navigate to MainActivity
        binding.back.setOnClickListener {
            val intent = Intent(this, MainAct::class.java)
            startActivity(intent)
            finish()
        }

        //Implicit intent
        binding.actionShare.setOnClickListener {
            sharePlayerInfo(player)
        }
    }

    private fun sharePlayerInfo(player: PlayerModel?) {
        player?.let {
            val shareText = "Check this player: ${it.name} - ${it.position} (${it.number})"
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareText)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, "Share player info via"))
        }
    }

    companion object {
        const val EXTRA_PLAYER_MODEL = "PLAYER_MODEL"
    }
}