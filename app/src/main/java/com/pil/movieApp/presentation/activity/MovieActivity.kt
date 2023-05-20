package com.pil.movieApp.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.pil.movieApp.presentation.adapter.MovieAdapter
import com.pil.movieApp.database.MovieDataBaseImpl
import com.pil.movieApp.database.MovieRoomDataBase
import com.pil.movieApp.fragment.ErrorDialogFragment
import com.pil.movieApp.mvvm.contract.MainContract
import com.pil.movieApp.mvvm.model.MainModel
import com.pil.movieApp.mvvm.viewmodel.MainViewModel
import com.pil.movieApp.mvvm.viewmodel.factory.ViewModelFactory
import com.pil.movieApp.service.MovieClient
import com.pil.movieApp.service.MovieRequestGenerator
import com.pil.movieApp.service.MovieServiceImpl
import com.pil.retrofit_room.R
import com.pil.retrofit_room.databinding.ActivityMainBinding

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainContract.ViewModel

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

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                arrayOf(
                    MainModel(
                        MovieServiceImpl(MovieRequestGenerator.createService(MovieClient::class.java)),
                        MovieDataBaseImpl(dataBase.moviesDao()),
                    ),
                ),
            ),
        )[MainViewModel::class.java]

        viewModel.getValue().observe(this) { updateUI(it) }
    }

    private fun updateUI(data: MainViewModel.MainData) {
        when (data.status) {
            MainViewModel.MainStatus.SHOW_INFO -> {
                if (data.movies.isEmpty()){
                    binding.emptyState.visibility = RecyclerView.VISIBLE
                }else {
                    binding.recycler.layoutManager = LinearLayoutManager(this)
                    binding.recycler.adapter = MovieAdapter(data.movies)
                }
            }
            MainViewModel.MainStatus.ERROR -> {
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