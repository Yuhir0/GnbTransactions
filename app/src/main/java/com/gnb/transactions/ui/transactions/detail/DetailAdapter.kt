package com.gnb.transactions.ui.transactions.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gnb.transactions.R
import com.gnb.transactions.models.Transaction

class DetailAdapter :
    RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    private val dataSet = ArrayList<Transaction>()
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.transaction_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: Transaction = dataSet[position]
        holder.apply {
            amount.text = data.amount.toString()
            currency.text = data.currency
        }
    }

    fun addTransactions(transactions: List<Transaction>) {
        this.dataSet.addAll(transactions)
        notifyItemRangeChanged(0, itemCount)
    }

    override fun getItemCount(): Int = dataSet.size

    open inner class ViewHolder (view: View) :
        RecyclerView.ViewHolder(view) {
        val amount: TextView = view.findViewById(R.id.transaction_amount)
        val currency: TextView = view.findViewById(R.id.transaction_currency)
    }
}