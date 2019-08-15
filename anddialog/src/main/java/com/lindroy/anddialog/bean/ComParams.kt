package com.lindroy.anddialog.bean

import android.os.Parcelable
import android.support.annotation.ColorInt
import android.support.annotation.FloatRange
import android.support.annotation.StyleRes
import com.lindroid.anddialog.R
import com.lindroy.anddialog.MaterialDialog
import com.lindroy.anddialog.constants.DialogType
import com.lindroy.iosdialog.util.getResColor
import com.lindroy.iosdialog.util.getResPx
import com.lindroy.iosdialog.util.getResSp
import kotlinx.android.parcel.Parcelize

/**
 * @author Lin
 * @date 2019/8/14
 * @function
 * @Description
 */
@Suppress("UNCHECKED_CAST")
@Parcelize
open class ComParams<T : ComParams<T>>(
        @DialogType internal var type: Int = MaterialDialog.ALERT,
        internal var tag: String = "MaterialDialog",
        internal var dimAmount: Float = 0.32F,
        internal var heightScale: Float = 0.0F,
        internal var titleParams: TextParams = TextParams(
                textSize = getResSp(R.dimen.md_dialog_title_size),
                isBold = true,
                textColor = getResColor(R.color.md_dialog_title_text_color
                )),
        internal var msgParams: TextParams = TextParams(
                textSize = getResSp(R.dimen.md_dialog_message_size),
                isBold = false,
                textColor = getResColor(R.color.md_dialog_message_text_color)),
        internal var posButtonParams: ButtonParams = ButtonParams(textColor = getResColor(R.color.md_dialog_button_text_color)),
        internal var negButtonParams: ButtonParams = ButtonParams(),
        internal var neuButtonParams: ButtonParams = ButtonParams(),
        internal var dismissible: Boolean = true,
        internal var cancelableOutSide: Boolean = true,
        @FloatRange(from = 0.0, to = 1.0) internal var backgroundAlpha: Float = 1.0F,
        @ColorInt internal var backgroundColor: Int = getResColor(R.color.md_dialog_bg_color),
        var radius: Float = getResPx(R.dimen.md_dialog_bg_corner_radius).toFloat(),
//    @StyleRes var themeStyle: Int = 0,
        @StyleRes var animStyle: Int = 0
) : Parcelable {
    /**
     * 设置对话框的动画样式
     */
    fun setAnimStyle(@StyleRes styleId: Int) = this.apply { animStyle = styleId } as T

    /**
     * 窗口明暗程度，0~1.0，1全不透明，系统默认值为0.32F
     */
    fun setDimAmount(@FloatRange(from = 0.0, to = 1.0) dimAmount: Float) =
            this.apply { this.dimAmount = dimAmount } as T

    /**
     * 设置背景圆角半径
     */
    fun setCornerRadius(radius: Float) = this.apply { this.radius = radius } as T


}