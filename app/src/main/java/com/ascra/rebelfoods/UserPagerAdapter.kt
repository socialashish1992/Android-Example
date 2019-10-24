package com.ascra.rebelfoods

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class UserPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {

    private val TAB_TITLE = arrayOf("User", "User Favourite")

    override fun getItem(position: Int): Fragment {
        return if (position == 0)
            UserFragment.newInstance()
        else
            UserFavouriteFragment.newInstance()
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLE[position]
    }
}
