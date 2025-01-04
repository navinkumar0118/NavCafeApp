package com.devroid.android_concepts.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devroid.devroidconcept.R

class DemoListAdapter(private val contactList: ArrayList<Contact> ) : RecyclerView.Adapter<DemoListAdapter.ContactViewHolder>() {

    //First Step
    override fun getItemCount() = contactList.size


    //Step 2 - ViewHolder class
    class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val contactNameTextView: TextView
        val contactNumberTextView: TextView
        val contactImgView: ImageView

        init {
            contactNameTextView = view.findViewById(R.id.contactName)
            contactNumberTextView = view.findViewById(R.id.contactPhoneNumber)
            contactImgView = view.findViewById(R.id.contactImageView)
        }

    }


    //Step 3 returns View Holder class
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoListAdapter.ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contacts_card_view, parent, false)

        return ContactViewHolder(view)
    }

    //Final step - UI DATA map -> draw
    override fun onBindViewHolder(holder: DemoListAdapter.ContactViewHolder, position: Int) {

        val currentContact  = contactList[position]

        holder.contactNameTextView.text = currentContact.name
        holder.contactNumberTextView.text = currentContact.phone
    }





}