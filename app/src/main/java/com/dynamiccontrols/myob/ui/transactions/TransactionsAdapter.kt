package com.dynamiccontrols.myob.ui.transactions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dynamiccontrols.myob.databinding.TransactionCellBinding
import com.dynamiccontrols.myob.model.Transaction
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class TransactionsAdapter(
    private var transactions: List<Transaction>,
    private val onItemClick: (Int) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val itemBinding =
            TransactionCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ModulesViewHolder(itemBinding)
    }

    fun update(transactions: List<Transaction>){
        this.transactions = transactions
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val moduleCell = transactions[position]
        if (holder is ModulesViewHolder) {
            holder.bind(moduleCell)
            holder.itemView.setOnClickListener {
                onItemClick(moduleCell.id)
            }
        }
    }

    override fun getItemCount(): Int = transactions.size

    inner class ModulesViewHolder(private val binding: TransactionCellBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            val pattern = "dd-MM-YYYY"
            val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
            val date: String = simpleDateFormat.format(transaction.date)
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            binding.transactionUsdAmount.text = "Amount in USD: $" + df.format(transaction.amount)
            binding.transactionDate.text = "Transaction Date: $date"
            binding.transactionAmount.text = "Amount in NZD: $" + df.format(transaction.usdAmount)
            binding.categoryName.text = "Category: " + transaction.categoryName
        }
    }
}

