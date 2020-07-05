package com.dynamiccontrols.myob.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dynamiccontrols.myob.R
import com.dynamiccontrols.myob.databinding.CategoriesFragmentBinding
import com.dynamiccontrols.myob.model.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private var _binding: CategoriesFragmentBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: CategoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CategoriesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.categoriesRecyclerView
        with(recyclerView) {
            adapter = CategoriesAdapter(
                listOf()
            )
        }
        val categoriesObserver = Observer<Set<Category>> { categories ->
            val adapter = binding.categoriesRecyclerView.adapter as CategoriesAdapter
            adapter.update(categories)
        }
        viewModel.categoriesLiveData.observe(viewLifecycleOwner, categoriesObserver)
    }

    private fun onItemClick(id: Int) {
        Toast.makeText(context, getString(R.string.not_implemented), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
