package com.lindroy.materialdialog.params

import android.os.Parcelable
import android.support.annotation.ColorInt
import android.support.annotation.FloatRange
import android.support.annotation.StyleRes
import com.lindroid.anddialog.R
import com.lindroy.iosdialog.util.*
import com.lindroy.materialdialog.constants.DialogType
import com.lindroy.materialdialog.constants.MD_ALERT
import com.lindroy.materialdialog.constants.MD_SINGLE_CHOICE
import kotlinx.android.parcel.Parcelize

/**
 * @author Lin
 * @date 2019/8/14
 * @function
 * @Description
 */
@Suppress("UNCHECKED_CAST")
@Parcelize
open class BaseAlertParams<T : BaseAlertParams<T>>(
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
    internal var cancelableOutside: Boolean = true,
    @FloatRange(from = 0.0, to = 1.0) internal var backgroundAlpha: Float = 1.0F,
    @ColorInt internal var backgroundColor: Int = getResColor(R.color.md_dialog_bg_color),
    internal var radius: Float = getResPx(R.dimen.md_dialog_bg_corner_radius).toFloat(),
    @StyleRes internal var animStyle: Int = 0,
//    var gravity: Int = Gravity.CENTER,
    var isKeepPortraitWidth: Boolean = true
) : Parcelable {

    internal val isSingleChoice
        get() = type == MD_SINGLE_CHOICE

    /**
     * 最终计算得到的宽度
     */
    internal val finalWidth
        get() = when (widthPx) {
            in 1..screenWidth -> widthPx
            else -> {
                if (isKeepPortraitWidth) {
                    widthPx = (widthScale * screenWidthOnPortrait).toInt()
                    widthPx
                } else (widthScale * screenWidth).toInt()
            }
        }

}