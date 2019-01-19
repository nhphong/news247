package com.nhphong.vn247.presentation.viewmodel

import com.nhphong.vn247.domain.entity.ArticlePreview
import com.nhphong.vn247.domain.data.FirebaseRepository
import io.reactivex.Flowable
import javax.inject.Inject

interface ArticlePreviewViewModel {
  fun articlePreviews(): Flowable<List<ArticlePreview>>
}

class ArticlePreviewViewModelImpl @Inject constructor(
  private val firebaseRepository: FirebaseRepository
): ArticlePreviewViewModel {
  override fun articlePreviews(): Flowable<List<ArticlePreview>> {
    return firebaseRepository.articlePreviews()
  }
}
