package com.pil.movieApp.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pil.movieApp.presentation.mvvm.viewmodel.MainViewModel
import com.pil.movieApp.presentation.util.fragment.ErrorDialogFragment
import com.pil.retrofit_room.R
import com.pil.retrofit_room.databinding.ActivityMainBinding
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : AppCompatActivity(), KoinComponent {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGetMovies.setOnClickListener { viewModel.onMoviesButtonPressed() }
        binding.errorDialogButton.setOnClickListener { viewModel.onShowErrorButtonPressed() }
        viewModel.getValue().observe(this) { updateUI(it) }
    }

        private fun updateUI(data: MainViewModel.MainData) {
            when (data.status) {
                MainViewModel.MainStatus.SHOW_MOVIES -> {
                    startActivity(Intent(this, MovieActivity::class.java))
                    finish()
                }
                MainViewModel.MainStatus.SHOW_ERROR_DIALOG -> {
                    ErrorDialogFragment.newInstance(
                        getString(R.string.error_dialog_title),
                        getString(R.string.message_error_dialog)
                    ).show(supportFragmentManager, getString(R.string.error_dialog_title))

                }
            }
        }
    }

