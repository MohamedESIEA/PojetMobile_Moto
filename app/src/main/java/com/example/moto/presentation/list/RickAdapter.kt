package com.example.moto.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moto.R

class RickAdapter(private var dataSet: List<Rick>, private var listener : ((Int)->Unit)? = null) : RecyclerView.Adapter<RickAdapter.ViewHolder>() {



    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView : ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.rick_name)
            imageView = view.findViewById((R.id.rick_img))
        }
    }


    fun updateList(list: List<Rick>) {
        dataSet = list
        notifyDataSetChanged()
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.rick_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val rick = dataSet[position]
        viewHolder.textView.text = rick.name
        viewHolder.itemView.setOnClickListener {
            listener?.invoke(position)
        }
        Glide
            .with(viewHolder.itemView.context)
            .load("https://rickandmortyapi.com/api/character/avatar/${position + 1 }.jpeg")
            .centerCrop()
            .into(viewHolder.imageView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
