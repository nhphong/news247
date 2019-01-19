package com.nhphong.vn247.presentation.view.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import com.nhphong.vn247.R
import com.nhphong.vn247.presentation.view.fragment.AccountFragment
import com.nhphong.vn247.presentation.view.fragment.HomeFragment
import com.nhphong.vn247.presentation.view.fragment.TrendingFragment
import com.nhphong.vn247.presentation.view.fragment.VideoFragment
import kotlinx.android.synthetic.main.tab_item.view.*

class MainPagerAdapter(
  fragmentManager: FragmentManager,
  private val context: Context
): FragmentStatePagerAdapter(fragmentManager) {

  private val pages = listOf(HomeFragment(), VideoFragment(), TrendingFragment(), AccountFragment())

  override fun getItem(position: Int): Fragment {
    return pages[position]
  }

  override fun getCount(): Int {
    return pages.size
  }

  fun getTabView(pos: Int): View {
    var icRes = 0
    var textRes = 0

    when(pages[pos]) {
      is HomeFragment -> {
        icRes = R.drawable.ic_home
        textRes = R.string.home
      }
      is VideoFragment -> {
        icRes = R.drawable.ic_video
        textRes = R.string.video
      }
      is TrendingFragment -> {
        icRes = R.drawable.ic_popular
        textRes = R.string.trending
      }
      is AccountFragment -> {
        icRes = R.drawable.ic_account
        textRes = R.string.account
      }
    }

    return LayoutInflater.from(context).inflate(R.layout.tab_item, null).apply {
      icon.setImageResource(icRes)
      title.text = context.getString(textRes)
    }
  }
}
