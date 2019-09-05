package com.lindroy.materialdialog.params

import android.os.Parcelable
import android.widget.ImageView
import kotlinx.android.parcel.Parcelize

/**
 * @author Lin
 * @date 2019/8/30
 * @function 方格选项对话框
 * @Description
 */
@Parcelize
data class IconParams(
    var iconSize: Int = 0,
    var iconMaxSize: Int = 0,
    var scaleType: ImageView.ScaleType = ImageView.ScaleType.CENTER_CROP
): Parcelable