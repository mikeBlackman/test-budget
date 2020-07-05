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
        return TransactionViewHolder(itemBinding)
    }

    fun update(transactions: List<Transaction>){
        this.transactions = transactions
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val transaction = transactions[position]
        if (holder is TransactionViewHolder) {
            holder.bind(transaction)
            holder.itemView.setOnClickListener {
                onItemClick(transaction.id)
            }
        }
    }

    override fun getItemCount(): Int = transactions.size

    inner class TransactionViewHolder(private val binding: TransactionCellBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            val pattern = "dd-MM-YYYY"
            val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
            val date: String = simpleDateFormat.format(transaction.date)
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            binding.transactionUsdAmount.text = "Amount in USD: $" + df.format(transaction.usdAmount)
            binding.transactionDate.text = "Transaction Date: $date"
            binding.transactionAmount.text = "Amount in NZD: $" + df.format(transaction.amount)
            binding.categoryName.text = "Category: " + transaction.categoryName
        }
    }
}

