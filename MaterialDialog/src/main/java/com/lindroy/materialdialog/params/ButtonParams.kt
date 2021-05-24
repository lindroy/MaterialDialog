package com.lindroy.materialdialog.params

import android.os.Parcelable
import androidx.annotation.ColorInt
import com.lindroid.anddialog.R
import com.lindroy.materialdialog.listener.OnDialogClickListener
import com.lindroy.materialdialog.util.getResColor
import com.lindroy.materialdialog.util.getResSp
import kotlinx.android.parcel.Parcelize

/**
 * @author Lin
 * @date 2019/7/27
 * @function 对话框按钮参数
 * @Description
 */
@Parcelize
data class ButtonParams(
    var textSize: Float = getResSp(R.dimen.md_dialog_button_text_size),
    @ColorInt var textColor: Int = getResColor(R.color.md_dialog_button_text_color),
    var text: String = "",
    var isVisible: Boolean = true,
    var clickListener: OnDialogClickListener? = null
): Parcelable

