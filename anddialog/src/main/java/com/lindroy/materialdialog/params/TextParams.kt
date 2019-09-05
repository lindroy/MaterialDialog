package com.lindroy.materialdialog.params

import android.os.Parcelable
import android.support.annotation.ColorInt
import android.view.Gravity
import kotlinx.android.parcel.Parcelize

/**
 * @author Lin
 * @date 2019/7/25
 * @function Dialog中的Text样式
 * @Description
 */
@Parcelize
data class TextParams(
    var textSize: Float = 0F, //单位为sp
    @ColorInt var textColor: Int = 0,
    var gravity: Int = Gravity.CENTER,
    var text: String = "",
    var isBold: Boolean = false,
    var height: Int = 0,
    var minHeight: Int = 0,
    var maxLines: Int = 2,
    var paddingLeft: Int = 0,
    var paddingTop: Int = 0,
    var paddingRight: Int = 0,
    var paddingBottom: Int = 0,
    var isVisible: Boolean = true
) : Parcelable
