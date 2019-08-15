package com.lindroy.anddialog.bean

import android.support.annotation.ColorInt
import com.lindroid.anddialog.R
import com.lindroy.iosdialog.util.getResColor
import com.lindroy.iosdialog.util.getResSp

/**
 * @author Lin
 * @date 2019/8/15
 * @function
 * @Description
 */
data class ListItemParams(
        val text: String,
        @ColorInt val textColor: Int = getResColor(R.color.md_single_choice_item_text_color),
        val textSize: Float = getResSp(R.dimen.md_single_choice_text_size),
        var isChecked:Boolean = false
)