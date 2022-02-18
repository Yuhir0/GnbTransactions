package com.gnb.transactions.ui.transactions.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gnb.transactions.R
import com.gnb.transactions.databinding.TransactionsFragmentBinding
import com.gnb.transactions.models.Transaction
import com.gnb.transactions.ui.transactions.detail.DetailFragment
import com.gnb.transactions.utils.OnClickItemListener
import org.koin.androidx.viewmodel.ext.android.viewModel


class TransactionsFragment : Fragment() {

    private var _binding: TransactionsFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = TransactionsFragment()
    }

    private val viewModel: TransactionsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TransactionsFragmentBinding.inflate(inflater, container, false)

        val adapter = TransactionsAdapter(object : OnClickItemListener<Transaction> {
            override fun onClick(item: Transaction?) {
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, DetailFragment.newInstance(item!!))
                    .addToBackStack("DETAIL")
                    .commit()
            }
        })

        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        binding.transactionsRecycler.layoutManager = llm
        binding.transactionsRecycler.adapter = adapter
        viewModel.transactions.observe(viewLifecycleOwner, { transactions: List<Transaction> ->
            adapter.addTransactions(transactions)
        })

        return binding.root
    }
}