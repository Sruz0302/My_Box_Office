package com.example.myboxoffice.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myboxoffice.R
import com.example.myboxoffice.base.fragment.BaseFragment
import com.example.myboxoffice.databinding.FragmentMovieDetailsBinding
import com.example.myboxoffice.databinding.FragmentUpcomingMovieBinding
import com.example.myboxoffice.model.movie.MovieResponse
import com.example.myboxoffice.utils.Constants


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MovieDetailsFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentMovieDetailsBinding
    private val itemsDetailVM: ItemDetailsVM by lazy {
        ViewModelProvider(this).get(
            ItemDetailsVM::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemsDetailVM.getFragmentCB().observe(viewLifecycleOwner, vmCallbackObserver)


        init()
    }

    private fun init() {
        itemsDetailVM.item.value =
            arguments?.getSerializable(Constants.BUNDLE_KEY) as MovieResponse.Item


        if(itemsDetailVM.item.value!!.getImageUrl()!=null){
            Glide.with(this)
                .load(itemsDetailVM.item.value!!.getImageUrl())
                .into( binding.imgPoser)
        }
        itemsDetailVM.item.value!!.title?.let {
            binding.tvTitle.text=it
        }
        itemsDetailVM.item.value!!.overview?.let {
            binding.tvOverview.text=it
        }
        itemsDetailVM.item.value!!.release_date?.let {
            binding.tvReleaseDate.text=it
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_movie_details, container, false)

            binding = FragmentMovieDetailsBinding.bind(mView!!)
                .apply {
                    this.lifecycleOwner=this@MovieDetailsFragment
                    this.viewModel=ItemDetailsVM()
                }
        }
        return binding.root
    }



//

    companion object {
        fun newInstance(item: MovieResponse.Item) = MovieDetailsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(Constants.BUNDLE_KEY, item)
            }
        }
    }

}