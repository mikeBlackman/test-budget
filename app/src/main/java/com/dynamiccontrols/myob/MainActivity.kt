package com.dynamiccontrols.myob

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.dynamiccontrols.myob.databinding.MainActivityBinding
import com.dynamiccontrols.myob.ui.main.SectionsPagerAdapter
import com.dynamiccontrols.myob.ui.transactiondialog.TransactionDialogFragment
import com.dynamiccontrols.myob.ui.util.ZoomOutPageTransformer
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private lateinit var binding: MainActivityBinding

    companion object {
        private val TAB_TITLES = arrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
            R.string.tab_text_3
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = binding.viewPager
        viewPager.setPageTransformer(ZoomOutPageTransformer())
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        viewPager.adapter = sectionsPagerAdapter

        val tabLayout = binding.tabs
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        val fab = binding.fab
        fab.setOnClickListener {
            val fm: FragmentManager = supportFragmentManager
            val transactionDialogFragment = TransactionDialogFragment()
            transactionDialogFragment.show(fm, "transaction_dialog_fragment")

//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
        }
    }
}