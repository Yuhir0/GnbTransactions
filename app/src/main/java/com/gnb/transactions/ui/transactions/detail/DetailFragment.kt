package com.gnb.transactions.ui.transactions.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gnb.transactions.databinding.DetailFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    companion object {
        private const val PRODUCT_KEY = "product"

        fun newInstance(product: String): DetailFragment {
            val fragment = DetailFragment()
            fragment.arguments = Bundle()
            fragment.requireArguments().putString(PRODUCT_KEY, product)
            return fragment
        }
    }

    private val logLabel = this::class.simpleName

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModel()
    private var _adapter: DetailAdapter? = null
    private val adapter get() = _adapter!!
    lateinit var product: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = requireArguments()
        product = args.getString(PRODUCT_KEY)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        _adapter = DetailAdapter()

        binding.progressBar.visibility = View.VISIBLE

        viewModel.launchLoadData(product)

        viewModel.transactions.observe(viewLifecycleOwner, {
            Log.d(logLabel, it.toString())
            adapter.addTransactions(it)
        })

        binding.productSelected.text = product
        prepareRecycle(binding.detailTransactionsRecycler)

        viewModel.total.observe(viewLifecycleOwner, {
            Log.d(logLabel, it.toString())
            binding.transactionTotal.text = it.toString()
            binding.progressBar.visibility = View.GONE
        })

        return binding.root
    }

    private fun prepareRecycle(recycler: RecyclerView) {
        val viewManager = LinearLayoutManager(context)
        viewManager.orientation = LinearLayoutManager.VERTICAL
        recycler.layoutManager = viewManager
        recycler.adapter = adapter
    }
}