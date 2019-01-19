package com.nhphong.vn247.presentation.view.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nhphong.vn247.R
import com.nhphong.vn247.domain.entity.ArticlePreview
import kotlinx.android.synthetic.main.article_preview.view.*

class ArticlePreviewHolder(private val view: View): RecyclerView.ViewHolder(view) {
  fun bindView(articlePreview: ArticlePreview) {
    view.source.text = articlePreview.source
    view.title.text = articlePreview.title
    view.published_at.text = articlePreview.publishedAt
    view.description.text = articlePreview.description

    Glide.with(view.context)
      .load(articlePreview.imageUrl)
      .apply(
        RequestOptions.centerCropTransform()
          .placeholder(R.drawable.placeholder))
      .into(view.image)
  }

  fun clear() {
    view.source.text = ""
    view.title.text = ""
    view.published_at.text = ""
    view.description.text = ""
    Glide.with(view.context).clear(view.image)
  }
}
