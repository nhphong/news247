package com.nhphong.vn247.presentation.view.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.nhphong.vn247.R
import com.nhphong.vn247.presentation.view.adapter.MainPagerAdapter
import com.nhphong.vn247.presentation.viewmodel.MainActivityViewModel
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.tab_layout as tabLayout
import kotlinx.android.synthetic.main.activity_main.view_pager as viewPager

class MainActivity: AppCompatActivity(), HasSupportFragmentInjector {

  @Inject
  lateinit var viewModel: MainActivityViewModel
  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

  private lateinit var disposables: CompositeDisposable

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val adapter = MainPagerAdapter(supportFragmentManager, this)
    viewPager.adapter = adapter
    tabLayout.setupWithViewPager(viewPager)
    (0 until tabLayout.tabCount).onEach {
      tabLayout.getTabAt(it)?.customView = adapter.getTabView(it)
    }
  }

  override fun onResume() {
    super.onResume()
    disposables = CompositeDisposable()
  }

  override fun onPause() {
    super.onPause()
    disposables.clear()
  }

  override fun supportFragmentInjector(): AndroidInjector<Fragment> {
    return dispatchingAndroidInjector
  }
}
