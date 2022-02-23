package com.gnb.transactions.ui.transactions.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gnb.transactions.R
import com.gnb.transactions.utils.OnClickItemListener

class ProductsAdapter(private val clickListener: OnClickItemListener<String>) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private val dataSet = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.product_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product: String = dataSet[position]
        holder.apply {
            view.setOnClickListener {
                clickListener.onClick(product)
            }
            sku.text = product
        }
    }

    fun addProducts(products: List<String>) {
        this.dataSet.addAll(products)
        notifyItemRangeInserted(0, itemCount)
    }

    override fun getItemCount(): Int = dataSet.size

    fun replaceData(products: List<String>) {
        val items = itemCount
        this.dataSet.clear()
        notifyItemRangeRemoved(0, items);
        addProducts(products)

    }

    open inner class ViewHolder (view: View) :
        RecyclerView.ViewHolder(view) {
        val view: View = view.findViewById(R.id.product_item_layout)
        val sku: TextView = view.findViewById(R.id.product_sku)
    }
}