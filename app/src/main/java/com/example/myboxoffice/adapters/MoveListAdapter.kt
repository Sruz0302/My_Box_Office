package com.example.myboxoffice.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myboxoffice.databinding.RecyclerMoviesBinding
import com.example.myboxoffice.model.movie.MovieResponse
import com.example.myboxoffice.ui.fragments.UpcomingMovieFragmentVM
import androidx.fragment.app.findFragment
import com.bumptech.glide.Glide
import org.jetbrains.anko.internals.AnkoInternals.createAnkoContext
import java.util.*
import kotlin.collections.ArrayList

class MoveListAdapter (private val itemsVM: UpcomingMovieFragmentVM)
    : RecyclerView.Adapter<MoveListAdapter.ViewHolder>(), Filterable {
    private val TAG = MoveListAdapter::class.java.simpleName

    lateinit var fragment: Fragment
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        this.fragment = parent.findFragment()
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerMoviesBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: RecyclerMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var image: ImageView? = null
        fun bind(item: MovieResponse.Item) {
          image=  binding.imageView5
            binding.item = item
            binding.viewModel = itemsVM
            binding.position = adapterPosition

            binding.executePendingBindings()
        }
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        itemsVM.itemsResponseFilter[position].let {
           // Log.e("imageurl",itemsVM.itemsResponseFilter[position].getImageUrl())
            holder.image?.let { it1 ->
                Glide.with(fragment.requireContext())
                    .load(itemsVM.itemsResponseFilter[position].getImageUrl())
                    .into(it1)
            };
            holder.bind(it
            )
        }
    }

    override fun getItemCount(): Int {
        return itemsVM.itemsResponseFilter.size
    }

    override fun getFilter(): Filter {
        return filter
    }

//    private val update()

    private val filter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults? {
            val filteredList: MutableList<MovieResponse.Item> = ArrayList()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(itemsVM.itemsResponse.results!!)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }

                for (item in itemsVM.itemsResponse.results!!) {
                    if (item.title!!.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            itemsVM.itemsResponseFilter.clear()
            itemsVM.itemsResponseFilter.addAll(results.values as ArrayList<MovieResponse.Item>)
            notifyDataSetChanged()
        }
    }



}