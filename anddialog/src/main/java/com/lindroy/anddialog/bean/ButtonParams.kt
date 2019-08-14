package com.lindroy.anddialog.bean

import android.os.Parcelable
import android.support.annotation.ColorInt
import com.lindroid.anddialog.R
import com.lindroy.anddialog.listener.OnDialogClickListener
import com.lindroy.iosdialog.util.getResColor
import com.lindroy.iosdialog.util.getResSp
import kotlinx.android.parcel.Parcelize

/**
 * @author Lin
 * @date 2019/7/27
 * @function 对话框按钮参数
 * @Description
 */

data class ButtonParams(
    var textSize: Float = getResSp(R.dimen.md_dialog_button_text_size),
    @ColorInt var textColor: Int = getResColor(R.color.md_dialog_button_text_color),
    var text: String = "",
    var isVisible: Boolean = true,
//    var clickListener: ((DialogInterface) -> Unit)? = null
    var clickListener: OnDialogClickListener? = null
):UnParcelableParams() {
    val isVisibleWithText
        get() = isVisible && text.isNotEmpty()

//    override fun clone() = super.clone()

}

@Parcelize
open class UnParcelableParams : Parcelable