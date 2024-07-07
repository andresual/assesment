package com.andresual.nexmedisassesment.presentation.bindingadapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.andresual.nexmedisassesment.R
import com.andresual.nexmedisassesment.util.Constants
import com.andresual.nexmedisassesment.util.setTintColor
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import jp.wasabeef.glide.transformations.CropTransformation

@SuppressLint("CheckResult")
@BindingAdapter("imageUrl", "centerCrop", "fitTop", "errorImage", requireAll = false)
fun ImageView.loadImage(
    imageUrl: String?,
    centerCrop: Boolean?,
    fitTop: Boolean,
    errorImage: Drawable?
) {

    val glide = Glide.with(context)
        .load(imageUrl)
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(errorImage ?: AppCompatResources.getDrawable(context, R.drawable.ic_baseline_image_24))
        .dontAnimate()

    if (centerCrop == true) glide.centerCrop()
    if (fitTop) glide.apply(RequestOptions.bitmapTransform(CropTransformation(0, 1235, CropTransformation.CropType.TOP)))

    glide.into(this)
}

@BindingAdapter("iconTint")
fun ImageView.setIconTint(color: Int?) {
    color?.let { setColorFilter(it.setTintColor()) }
}

@BindingAdapter("fixedSize")
fun RecyclerView.setFixedSize(hasFixedSize: Boolean) {
    setHasFixedSize(hasFixedSize)
}

@BindingAdapter("isVisible")
fun View.setVisibility(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("android:background")
fun View.setBackground(color: Int) {
    setBackgroundColor(if (color != 0) color else ContextCompat.getColor(context, R.color.day_night_inverse))
}

@BindingAdapter("collapsingToolbar", "frameLayout", "collapsingToolbarTitle", "backgroundColor", requireAll = false)
fun AppBarLayout.setToolbarCollapseListener(
    collapsingToolbar: CollapsingToolbarLayout,
    frameLayout: FrameLayout,
    collapsingToolbarTitle: String?,
    backgroundColor: Int
) {
    var isShow = true
    var scrollRange = -1

    addOnOffsetChangedListener { appBarLayout, verticalOffset ->
        if (scrollRange == -1) scrollRange = appBarLayout?.totalScrollRange!!

        if (scrollRange + verticalOffset == 0) {
            frameLayout.isVisible = false
            collapsingToolbar.setCollapsedTitleTextColor(backgroundColor.setTintColor())
            collapsingToolbar.title = collapsingToolbarTitle
            isShow = true
        } else if (isShow) {
            frameLayout.isVisible = isShow
            collapsingToolbar.title = " "
            isShow = false
        }
    }
}

@BindingAdapter("transparentBackground")
fun View.setTransparentBackground(backgroundColor: Int) {
    setBackgroundColor(ColorUtils.setAlphaComponent(backgroundColor, 220))
}

@BindingAdapter("fragment", "backArrowTint", "toolbarTitle", "toolbarTitleColor", requireAll = false)
fun Toolbar.setupToolbar(
    fragment: Fragment,
    backArrowTint: Int,
    title: String?,
    titleColor: Int?
) {
    with(fragment.requireActivity() as AppCompatActivity) {
        setSupportActionBar(this@setupToolbar)
        if (title != null) setTitle(title)
    }

    setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)

    navigationIcon?.setTint(
        if (backArrowTint != 0) backArrowTint.setTintColor()
        else ContextCompat.getColor(context, R.color.day_night)
    )

    if (titleColor != null) {
        setTitleTextColor(
            if (titleColor != 0) titleColor.setTintColor()
            else ContextCompat.getColor(context, R.color.day_night)
        )
    }

    setNavigationOnClickListener {
        fragment.requireActivity().onBackPressedDispatcher.onBackPressed()
    }
}

@BindingAdapter("detailId", "detailImageUrl", requireAll = false)
fun View.setDetailsNavigation(
    id: String,
    imageUrl: String?
) {
    var backgroundColor = ContextCompat.getColor(context, R.color.day_night_inverse)

    imageUrl?.let {
        Glide.with(context)
            .asBitmap()
            .load(imageUrl)
            .priority(Priority.HIGH)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Palette.from(resource).generate().dominantSwatch?.rgb?.let { color ->
                        backgroundColor = color
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
    }

    setOnClickListener {
        val destination = R.id.action_global_mealDetailsFragment

        val bundle = bundleOf(
            Constants.DETAIL_ID to id.toInt(),
            Constants.BACKGROUND_COLOR to backgroundColor
        )

        findNavController().navigate(destination, bundle)
    }
}