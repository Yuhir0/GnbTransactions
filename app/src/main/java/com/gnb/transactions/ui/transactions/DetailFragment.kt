package com.gnb.transactions.ui.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gnb.transactions.R
import com.gnb.transactions.viwmodels.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private val viewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detail_fragment, container, false)
        // TODO: Get Rates
        return view
    }
}