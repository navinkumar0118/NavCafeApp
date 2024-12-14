package com.devroid.android_concepts.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devroid.devroidconcept.R


class ContactsListAdapter(private val dataSet: Array<String>) : RecyclerView.Adapter<ContactsListAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    //contains views of each item layout
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contactName: TextView
        val contactNumber: TextView
        val contactImg: ImageView


        init {
          //   Define click listener for the ViewHolder's View
            contactName = view.findViewById(R.id.contactName)
            contactNumber = view.findViewById(R.id.contactPhoneNumber)
            contactImg = view.findViewById(R.id.contactImageView)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.contacts_card_view, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.contactName.text = dataSet[position]
        viewHolder.contactNumber.text = "98765432"


    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}