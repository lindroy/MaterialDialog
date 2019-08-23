package com.lindroy.anddialog.params

import android.os.Parcelable
import android.support.annotation.ColorInt
import android.support.annotation.DimenRes
import android.support.annotation.FloatRange
import android.support.annotation.StyleRes
import android.view.Gravity
import com.lindroid.anddialog.R
import com.lindroy.anddialog.constants.DialogType
import com.lindroy.anddialog.constants.MD_ALERT
import com.lindroy.anddialog.constants.MD_SINGLE_CHOICE
import com.lindroy.iosdialog.util.*
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
    @DialogType internal var type: Int = MD_ALERT,
    internal var tag: String = "MaterialDialog",
    internal var dimAmount: Float = 0.32F,
    internal var widthScale: Float = 0.85F,
    internal var widthPx: Int = 0,
    internal var heightScale: Float = 0.0F,
    internal var titleParams: TextParams = TextParams(
        textSize = getResSp(R.dimen.md_dialog_title_size),
        isBold = true,
        textColor = getResColor(
            R.color.md_dialog_title_text_color
        )
    ),
    internal var msgParams: TextParams = TextParams(
        textSize = getResSp(R.dimen.md_dialog_message_size),
        isBold = false,
        textColor = getResColor(R.color.md_dialog_message_text_color)
    ),
    internal var posButtonParams: ButtonParams = ButtonParams(textColor = getResColor(R.color.md_dialog_button_text_color)),
    internal var negButtonParams: ButtonParams = ButtonParams(),
    internal var neuButtonParams: ButtonParams = ButtonParams(),
    internal var dismissible: Boolean = true,
    internal var cancelableOutSide: Boolean = true,
    @FloatRange(from = 0.0, to = 1.0) internal var backgroundAlpha: Float = 1.0F,
    @ColorInt internal var backgroundColor: Int = getResColor(R.color.md_dialog_bg_color),
    var radius: Float = getResPx(R.dimen.md_dialog_bg_corner_radius).toFloat(),
    @StyleRes var animStyle: Int = 0,
    var gravity: Int = Gravity.CENTER,
    var isKeepPortraitWidth: Boolean = gravity == Gravity.CENTER
) : Parcelable {

    val isSingleChoice
        get() = type == MD_SINGLE_CHOICE

    /**
     * 最终计算得到的宽度
     */
    val finalWidth
        get() = when (widthPx) {
            in 1..screenWidth -> widthPx
            else -> {
                if (isKeepPortraitWidth) {
                    widthPx = (widthScale * screenWidthOnPortrait).toInt()
                    widthPx
                } else (widthScale * screenWidth).toInt()
            }
        }

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

    /**
     * @see setCornerRadius
     */
    fun setCornerRadius(@DimenRes dimenId: Int) = setCornerRadius(getResPx(dimenId).toFloat())


}