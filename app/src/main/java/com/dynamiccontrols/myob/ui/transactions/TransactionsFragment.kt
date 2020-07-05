package com.dynamiccontrols.myob.ui.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dynamiccontrols.myob.R
import com.dynamiccontrols.myob.databinding.TransactionsFragmentBinding
import com.dynamiccontrols.myob.model.Transaction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionsFragment : Fragment() {

    private var _binding: TransactionsFragmentBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: TransactionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TransactionsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.transactionsRecyclerView
        with(recyclerView){
            adapter = TransactionsAdapter(listOf(), ::onItemClick)
        }

        val amountErrorObserver = Observer<List<Transaction>> { transactions ->
            val adapter = binding.transactionsRecyclerView.adapter as TransactionsAdapter
            adapter.update(transactions)
        }
        viewModel.transactionsLiveData.observe(viewLifecycleOwner, amountErrorObserver)
    }

    private fun onItemClick(id : Int){
        Toast.makeText(context, getString(R.string.not_implemented), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
