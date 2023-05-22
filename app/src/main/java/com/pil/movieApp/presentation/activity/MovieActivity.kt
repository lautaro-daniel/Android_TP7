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

class MovieActivity : AppCompatActivity() {

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

        val dataBase: MovieRoomDataBase by lazy {
            Room
                .databaseBuilder(this, MovieRoomDataBase::class.java, "Movie-DataBase")
                .build()
        }

        viewModel.callService()
        viewModel.getValue().observe(this) { updateUI(it) }
    }

    private fun updateUI(data: MoviesViewModel.MainData) {
        when (data.status) {
            MoviesViewModel.MainStatus.SHOW_INFO -> {
                if (data.movies.isEmpty()){
                    binding.emptyState.visibility = RecyclerView.VISIBLE
                }else {
                    binding.recycler.layoutManager = LinearLayoutManager(this)
                    binding.recycler.adapter = MovieAdapter(data.movies)
                }
            }
            MoviesViewModel.MainStatus.ERROR -> {
                ErrorDialogFragment.newInstance(getString(R.string.error_dialog_title),
                    getString(R.string.message_error_dialog)).show(supportFragmentManager,"errorDialog")
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