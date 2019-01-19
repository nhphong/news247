package com.nhphong.vn247.di

import com.nhphong.vn247.presentation.view.fragment.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityFragmentBindingModule {

  @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
  fun homeFragment(): HomeFragment
}
