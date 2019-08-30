package com.lindroy.anddialog.params

import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.support.annotation.DrawableRes
import com.lindroid.anddialog.R
import com.lindroy.iosdialog.util.getResPx
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * @author Lin
 * @date 2019/8/24
 * @function 底部表格对话框item参数
 * @Description
 */
@Parcelize
data class MDGridItem(
    var text: String = "",
    internal @DrawableRes var iconId: Int = 0,
//    internal var textSize: Float = getResSp(R.dimen.md_grid_item_text_size), //单位为sp
//    internal @ColorInt var textColor: Int = getResColor(R.color.md_grid_item_text_color),
    var icon: @RawValue Drawable? = null,
    internal var paddingTopBottom: Int = getResPx(R.dimen.md_grid_item_padding_top_bottom)
//    internal var gravity: Int = Gravity.CENTER
) : Parcelable