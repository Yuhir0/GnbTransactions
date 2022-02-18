package com.gnb.transactions.ui.transactions.transactions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gnb.transactions.R
import com.gnb.transactions.models.Transaction
import com.gnb.transactions.utils.OnClickItemListener

class TransactionsAdapter(private val clickListener: OnClickItemListener<Transaction>) :
    RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {

    private val transactions = ArrayList<Transaction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.transaction_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction: Transaction = transactions[position]
        holder.apply {
            view.setOnClickListener {
                clickListener.onClick(transaction)
            }
            sku.text = transaction.sku
            amount.text = transaction.amount.toString()
            currency.text = transaction.currency
        }
    }

    fun addTransactions(transactions: List<Transaction>) {
        this.transactions.addAll(transactions)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = transactions.size

    open inner class ViewHolder (view: View) :
        RecyclerView.ViewHolder(view) {
        val view: View = view.findViewById(R.id.transaction_item_view)
        val sku: TextView = view.findViewById(R.id.transaction_sku)
        val amount: TextView = view.findViewById(R.id.transaction_amount)
        val currency: TextView = view.findViewById(R.id.transaction_currency)
    }
}