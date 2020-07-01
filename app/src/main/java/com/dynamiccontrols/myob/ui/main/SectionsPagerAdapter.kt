package com.dynamiccontrols.myob.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dynamiccontrols.myob.ui.categories.CategoriesFragment
import com.dynamiccontrols.myob.ui.overview.OverviewFragment
import com.dynamiccontrols.myob.ui.transactions.TransactionsFragment

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fragmentActivity: FragmentActivity)
    : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> OverviewFragment()
        1 -> TransactionsFragment()
        2 -> CategoriesFragment()
        else -> throw IllegalArgumentException("no item")
    }

}