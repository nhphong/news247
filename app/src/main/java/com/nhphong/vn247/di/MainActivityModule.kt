package com.nhphong.vn247.di

import android.app.Activity
import com.nhphong.vn247.presentation.view.activity.MainActivity
import com.nhphong.vn247.presentation.viewmodel.MainActivityViewModel
import com.nhphong.vn247.presentation.viewmodel.MainActivityViewModelImpl
import dagger.Binds
import dagger.Module

@Module
interface MainActivityModule {
  @Binds fun viewModel(viewModel: MainActivityViewModelImpl): MainActivityViewModel
  @Binds fun activity(activity: MainActivity): Activity
}
