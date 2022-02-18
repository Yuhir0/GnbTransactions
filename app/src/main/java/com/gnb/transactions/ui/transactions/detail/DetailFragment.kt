package com.gnb.transactions.ui.transactions.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.gnb.transactions.databinding.DetailFragmentBinding
import com.gnb.transactions.models.Transaction
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    companion object {
        private const val TRANSACTION_KEY = "transaction"

        fun newInstance(transaction: Transaction): DetailFragment {
            val fragment = DetailFragment()
            fragment.arguments = Bundle()
            fragment.requireArguments().putParcelable(TRANSACTION_KEY, transaction)
            return fragment
        }
    }

    private val logLabel = this::class.simpleName

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModel()

    lateinit var transaction: Transaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = requireArguments()
        transaction = args.getParcelable(TRANSACTION_KEY)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        launchCoroutines()

        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        binding.detailSku.text = transaction.sku
        binding.detailAmount.text = transaction.amount.toString()
        binding.detailCurrency.text = transaction.currency

        viewModel.conversionList.observe(viewLifecycleOwner, { currencies ->
            Log.d(logLabel, currencies.toString())
        })

        return binding.root
    }

    private fun launchCoroutines() {
        lifecycleScope.launch {
            Log.d(logLabel, "Launch coroutine")
            viewModel.conversion(transaction.currency, transaction.amount)
        }
    }
}