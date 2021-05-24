package com.lindroy.materialdialog.params

import android.os.Parcelable
import androidx.annotation.ColorInt
import android.view.Gravity
import com.lindroid.anddialog.R
import com.lindroy.materialdialog.util.getResColor
import com.lindroy.materialdialog.util.getResPx
import com.lindroy.materialdialog.util.getResSp
import kotlinx.android.parcel.Parcelize

/**
 * @author Lin
 * @date 2019/8/21
 * @function 底部列表对话框item参数
 * @Description
 */


@Parcelize
data class MDListItem(
    var text: String = "",
    var textSize: Float = getResSp(R.dimen.md_list_item_text_size), //单位为sp
    internal @ColorInt var textColor: Int = getResColor(R.color.md_list_item_text_color),
    internal var gravity: Int = Gravity.START or Gravity.CENTER_VERTICAL,
    internal var isBold: Boolean = false,
    internal var height: Int = getResPx(R.dimen.md_list_item_height),
    internal var paddingLeft: Int = getResPx(R.dimen.md_dialog_padding_start),
    internal var paddingTop: Int = 0,
    internal var paddingRight: Int = 0,
    internal var paddingBottom: Int = 0
) : Parcelable