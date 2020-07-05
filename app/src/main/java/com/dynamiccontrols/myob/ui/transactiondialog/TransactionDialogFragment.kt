package com.dynamiccontrols.myob.ui.transactiondialog

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dynamiccontrols.myob.model.CategoryManager
import com.dynamiccontrols.myob.R
import com.dynamiccontrols.myob.databinding.TransactionDialogFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TransactionDialogFragment : BottomSheetDialogFragment() {

    private var _binding: TransactionDialogFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TransactionDialogViewModel by viewModels()

    @Inject lateinit var categoryManager : CategoryManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TransactionDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categorySet = categoryManager.generateCategorySet()
        val categoryNames = mutableListOf<String>()

        categorySet.forEach {
            categoryNames.add(it.name)
        }

        val categoryLabels = categoryNames.toTypedArray()

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu_popup_item,
            categoryLabels
        )

        with(binding.categoryInput) {
            setAdapter(adapter)
            inputType = 0
            setText(categoryLabels[0], false)
        }

        binding.addTransactionButton.setOnClickListener {
            viewModel.addTransactionButtonClicked(
                binding.amountEditText.text.toString(),
                binding.categoryInput.text.toString().trim()
            )
            dismiss()
        }

        binding.amountEditText.addTextChangedListener {
            binding.amount.isErrorEnabled = false
        }

        val amountErrorObserver = Observer<String> { errorMessage ->
            binding.amount.error = errorMessage
        }
        viewModel.displayAmountInputError.observe(this, amountErrorObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
