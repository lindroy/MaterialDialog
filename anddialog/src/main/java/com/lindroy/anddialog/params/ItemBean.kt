package com.lindroy.anddialog.params

import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.view.Gravity
import com.lindroid.anddialog.R
import com.lindroy.iosdialog.util.getResColor
import com.lindroy.iosdialog.util.getResPx
import com.lindroy.iosdialog.util.getResSp
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * @author Lin
 * @date 2019/8/21
 * @function
 * @Description
 */

@Parcelize
data class ItemBean(
    var text: String = "",
    var textSize: Float = getResSp(R.dimen.md_list_item_text_size), //单位为sp
    internal @ColorInt var textColor: Int = getResColor(R.color.md_list_item_text_color),
    internal @DrawableRes var iconId:Int = 0,
    var icon:@RawValue Drawable? = null,
    internal var gravity: Int = Gravity.START or Gravity.CENTER_VERTICAL,
    internal var isBold: Boolean = false,
    internal var height: Int = getResPx(R.dimen.md_list_item_height),
    internal var paddingLeft: Int = getResPx(R.dimen.md_dialog_padding_start),
    internal var paddingTop: Int = 0,
    internal var paddingRight: Int = 0,
    internal var paddingBottom: Int = 0
) : Parcelable