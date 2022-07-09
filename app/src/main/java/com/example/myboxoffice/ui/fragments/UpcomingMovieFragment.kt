package com.example.myboxoffice.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myboxoffice.R
import com.example.myboxoffice.adapters.MoveListAdapter
import com.example.myboxoffice.base.fragment.BaseFragment
import com.example.myboxoffice.callbacks.HomeFragmentCallback
import com.example.myboxoffice.databinding.FragmentUpcomingMovieBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class UpcomingMovieFragment :  BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var page: Int = 1
    private var available: Int? = 0
    private val TAG = UpcomingMovieFragment::class.java.simpleName

    private lateinit var adapter: MoveListAdapter
    private lateinit var binding: FragmentUpcomingMovieBinding
    private val itemsVM: UpcomingMovieFragmentVM by lazy {
        ViewModelProvider(this).get(UpcomingMovieFragmentVM::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onResume() {
        super.onResume()

        itemsVM.getItemsApi(page)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_upcoming_movie, container, false)

            binding = FragmentUpcomingMovieBinding.bind(mView!!)
                .apply {
                    this.lifecycleOwner = this@UpcomingMovieFragment
                    this.viewModel = itemsVM
                }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemsVM.getFragmentCB().observe(viewLifecycleOwner, vmCallbackObserver)
        itemsVM.getCallback().observe(viewLifecycleOwner, callbackObserver)


        init()
    }
    fun init() {

        adapter = MoveListAdapter(itemsVM)

        binding.homeRV.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.homeRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!binding.homeRV.canScrollVertically(1)) {
                    if (page <= itemsVM.available!!) {
                        page = page.plus(1)
                        (page)
                    }
                }
            }
        })
        binding.homeRV.adapter = adapter
    }







    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UpcomingMovieFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private val callbackObserver = Observer<HomeFragmentCallback> { callBack ->
        when (callBack) {
            is HomeFragmentCallback.PopulateAdapter -> {
                populateAdapters()
            }

        }
    }

    private fun populateAdapters() {
        adapter.filter.filter(null)
    }

    override fun onDestroy() {
        itemsVM.remove()
        itemsVM.getCallback().removeObserver(callbackObserver)
        itemsVM.clearObservers(vmCallbackObserver)
        super.onDestroy()
    }


}