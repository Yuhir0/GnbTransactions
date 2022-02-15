package com.gnb.transactions.ui.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gnb.transactions.databinding.TransactionsFragmentBinding
import com.gnb.transactions.models.Transaction
import com.gnb.transactions.viwmodels.TransactionsViewModel
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
        viewModel.transactions.observe(viewLifecycleOwner, { transactions: List<Transaction>? ->
            binding.text.text = transactions.toString()
        })
        // TODO: Create adapter to show the transactions
        return binding.root
    }
}