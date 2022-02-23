package com.gnb.transactions.ui.transactions.products

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gnb.transactions.R
import com.gnb.transactions.databinding.TransactionsFragmentBinding
import com.gnb.transactions.ui.transactions.detail.DetailFragment
import com.gnb.transactions.utils.OnClickItemListener
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductsFragment : Fragment() {

    private val logLabel = this::class.simpleName
    private var _binding: TransactionsFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = ProductsFragment()
    }

    private val viewModel: ProductsViewModel by viewModel()
    private var _adapter: ProductsAdapter? = null
    private val adapter get() = _adapter!!

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TransactionsFragmentBinding.inflate(inflater, container, false)
        _adapter = ProductsAdapter(onClickItem())
        viewModel.launchDataLoad()

        binding.searchProduct.addTextChangedListener {
            adapter.replaceData(viewModel.searchProduct(it.toString()))
        }

        prepareRecycler(binding.productsRecycler)
        viewModel.products.observe(viewLifecycleOwner, {
            adapter.replaceData(
                viewModel.searchProduct(binding.searchProduct.text.toString()))
        })

        return binding.root
    }

    private fun prepareRecycler(recycler: RecyclerView) {
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        recycler.layoutManager = llm
        recycler.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        recycler.adapter = adapter
    }

    private fun onClickItem() = object : OnClickItemListener<String> {
        override fun onClick(item: String?) {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, DetailFragment.newInstance(item!!))
                .addToBackStack("DETAIL")
                .commit()
        }
    }
}