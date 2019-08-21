package com.lindroy.anddialog.params

import android.os.Parcelable
import android.support.annotation.ColorInt
import android.view.Gravity
import com.lindroid.anddialog.R
import com.lindroy.iosdialog.util.getResColor
import com.lindroy.iosdialog.util.getResPx
import com.lindroy.iosdialog.util.getResSp
import kotlinx.android.parcel.Parcelize

/**
 * @author Lin
 * @date 2019/8/21
 * @function
 * @Description
 */

@Parcelize
data class ListItemBean(
    var text: String = "",
    var textSize: Float = getResSp(R.dimen.md_list_item_text_size), //单位为sp
    @ColorInt var textColor: Int = getResColor(R.color.md_list_item_text_color),
    var gravity: Int = Gravity.START or Gravity.CENTER_VERTICAL,
    var isBold: Boolean = false,
    var height: Int = getResPx(R.dimen.md_list_item_height),
    var paddingLeft: Int = getResPx(R.dimen.md_dialog_padding_start),
    var paddingTop: Int = 0,
    var paddingRight: Int = 0,
    var paddingBottom: Int = 0,
    var isVisible: Boolean = true
) : Parcelable