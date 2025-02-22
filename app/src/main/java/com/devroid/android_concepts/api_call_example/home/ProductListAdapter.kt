package com.devroid.android_concepts.api_call_example.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devroid.android_concepts.api_call_example.ProductDetail
import com.devroid.devroidconcept.R
import com.squareup.picasso.Picasso

class ProductListAdapter(private var productsList: ArrayList<ProductDetail>, private var callback : ProductsCallBack) :
    RecyclerView.Adapter<ProductListAdapter.ProductsViewHolder>() {

    ///call back functions to the UI Fragment
    interface ProductsCallBack {
        fun onIncrementPressed(productDetail: ProductDetail)
        fun onDecrementPressed(productDetail: ProductDetail)
    }


    //First Step
    override fun getItemCount() = productsList.size


    //Step 2 - ViewHolder class
    class ProductsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val productNameTextView: TextView
        val productPriceTextView: TextView
        val productImageView: ImageView
        val quantityCountTextview: TextView
        val decrementCountButton: Button
        val incrementCountButton: Button

        init {
            productNameTextView = view.findViewById(R.id.productName)
            productPriceTextView = view.findViewById(R.id.productPrice)
            productImageView = view.findViewById(R.id.productImageView)
            incrementCountButton = view.findViewById(R.id.incrementCountButton)
            quantityCountTextview = view.findViewById(R.id.quantityCountTextview)
            decrementCountButton = view.findViewById(R.id.decrementCountButton)

        }

    }


    //Step 3 returns View Holder class
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_card_view, parent, false)

        return ProductsViewHolder(view)
    }


    //Final step - UI DATA map -> draw
    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {

        val product = productsList[position]

        holder.productNameTextView.text = product.title
        holder.productPriceTextView.text = "₹ " + product.price.toString()
        holder.quantityCountTextview.text = product.quantity.toString()


        Picasso.get()
            .load(product.image)
            .resize(150, 150)
            .centerInside()
            .placeholder(R.drawable.loading)
            .error(R.drawable.cofee1)
            .into(holder.productImageView) //image


        holder.incrementCountButton.setOnClickListener {
            callback.onIncrementPressed(product)
        }

        holder.decrementCountButton.setOnClickListener {
            callback.onDecrementPressed(product)
        }

    }


    // Method to update the list and notify the adapter
    fun updateProduct(newItemList: ArrayList<ProductDetail>) {
        productsList = newItemList
        notifyDataSetChanged() // Notify that the data has changed
    }


}