package com.lindroy.anddialog.params

import android.widget.ImageView

/**
 * @author Lin
 * @date 2019/8/30
 * @function 方格选项对话框
 * @Description
 */
data class IconParams(
    var iconSize: Int = 0,
    var iconMaxSize: Int = 0,
    var scaleType: ImageView.ScaleType = ImageView.ScaleType.CENTER_CROP
)