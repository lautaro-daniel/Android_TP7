package com.pil.movieApp.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.pil.movieApp.presentation.adapter.MovieAdapter
import com.pil.movieApp.database.MovieRoomDataBase
import com.pil.movieApp.domain.util.fragment.ErrorDialogFragment
import com.pil.movieApp.presentation.mvvm.viewmodel.MoviesViewModel
import com.pil.retrofit_room.R
import com.pil.retrofit_room.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent

class MovieActivity : AppCompatActivity(), KoinComponent {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MoviesViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonBackHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        viewModel.callService()
        viewModel.getValue().observe(this) { updateUI(it) }
    }

    private fun updateUI(data: MoviesViewModel.MainData) {
        when (data.status) {
            MoviesViewModel.MainStatus.SHOW_INFO -> {
                binding.recycler.layoutManager = LinearLayoutManager(this)
                binding.recycler.adapter = MovieAdapter(data.movies)

            }
            MoviesViewModel.MainStatus.ERROR -> {
                binding.emptyState.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.callService()
        binding.appDescription.visibility = View.GONE
        binding.buttonGetMovies.visibility = View.GONE
        binding.errorDialogButton.visibility = View.GONE
    }
}