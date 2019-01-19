package com.nhphong.vn247.domain.data

import com.nhphong.vn247.domain.entity.ArticlePreview
import com.nhphong.vn247.external.data.firebase.CloudFirestoreGateway
import io.reactivex.Flowable
import javax.inject.Inject

interface FirebaseRepository {
  fun articlePreviews(): Flowable<List<ArticlePreview>>
}

class FirebaseRepositoryImpl @Inject constructor(
  private val firestoreGateway: CloudFirestoreGateway
): FirebaseRepository {
  override fun articlePreviews(): Flowable<List<ArticlePreview>> {
    return firestoreGateway.articlePreviews()
  }
}
