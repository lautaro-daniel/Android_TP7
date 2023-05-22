package com.pil.movieApp.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pil.movieApp.domain.util.fragment.ErrorDialogFragment
import com.pil.retrofit_room.R
import com.pil.retrofit_room.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBackHome.visibility = View.GONE

        binding.buttonGetMovies.setOnClickListener {
            val intent = Intent(this, MovieActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.errorDialogButton.setOnClickListener{
            ErrorDialogFragment.newInstance(getString(R.string.error_dialog_title),
                getString(R.string.message_error_dialog)).show(supportFragmentManager,"errorDialog")

        }
    }
}
