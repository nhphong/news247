package com.nhphong.vn247.di

import com.nhphong.vn247.di.scope.ActivityScope
import com.nhphong.vn247.presentation.view.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBindingModule {

  @ActivityScope
  @ContributesAndroidInjector(modules = [MainActivityModule::class, MainActivityFragmentBindingModule::class])
  fun mainActivity(): MainActivity
}
