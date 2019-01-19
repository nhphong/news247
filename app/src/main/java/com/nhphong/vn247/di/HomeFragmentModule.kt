package com.nhphong.vn247.di

import com.nhphong.vn247.presentation.viewmodel.ArticlePreviewViewModel
import com.nhphong.vn247.presentation.viewmodel.ArticlePreviewViewModelImpl
import dagger.Binds
import dagger.Module

@Module
interface HomeFragmentModule {
  @Binds
  fun articlePreviewViewModel(viewModel: ArticlePreviewViewModelImpl): ArticlePreviewViewModel
}
