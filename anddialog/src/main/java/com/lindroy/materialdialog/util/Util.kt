package com.lindroy.iosdialog.util

import android.content.Context
import android.content.res.Configuration
import android.graphics.Point
import android.graphics.Typeface
import android.os.Build
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.lindroy.materialdialog.MaterialDialog

/**
 * @author Lin
 * @date 2019/5/18
 * @function 工具类
 * @Description
 */

internal val screenHeight: Int
    get() {
        val wm = MaterialDialog.appContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point = Point()
        when (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            true -> wm.defaultDisplay.getRealSize(point)
            false -> wm.defaultDisplay.getSize(point)
        }
        return point.y
    }

internal val screenWidth: Int
    get() {
        val wm = MaterialDialog.appContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point = Point()
        when (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            true -> wm.defaultDisplay.getRealSize(point)
            false -> wm.defaultDisplay.getSize(point)
        }
        return point.x
    }

internal fun dp2px(dpValue: Float): Float {
    val scale = MaterialDialog.appContext.resources.displayMetrics.density
    return (dpValue * scale + 0.5F)
}

internal fun px2dp(pxValue: Float):Float{
    val scale = MaterialDialog.appContext.resources.displayMetrics.density
    return (pxValue / scale + 0.5F)
}

internal fun px2sp(pxValue: Float): Float {
    val fontScale = MaterialDialog.appContext.resources.displayMetrics.scaledDensity
    return (pxValue / fontScale + 0.5F)
}

internal fun sp2px(spValue: Float): Float {
    val fontScale = MaterialDialog.appContext.resources.displayMetrics.density
    return (spValue * fontScale + 0.5F)
}

internal fun getResSp(dimenId: Int) =
    px2sp(MaterialDialog.appContext.resources.getDimensionPixelSize(dimenId).toFloat())

internal fun getResPx(dimenId: Int) = MaterialDialog.appContext.resources.getDimensionPixelSize(dimenId)

internal fun getResColor(@ColorRes colorId: Int) =
    ContextCompat.getColor(MaterialDialog.appContext, colorId)

internal fun getResDrawable(@DrawableRes resId: Int) =
    ContextCompat.getDrawable(MaterialDialog.appContext, resId)

internal fun getResString(@StringRes stringId: Int) = MaterialDialog.appContext.getString(stringId)


/**
 * 不随屏幕方向变化的宽度
 */
internal val screenWidthOnPortrait
    get() = if (isPortrait) screenWidth else screenHeight

/**
 * 是否是竖屏
 */
internal val isPortrait
    get() = MaterialDialog.appContext.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

internal val View.isGone
    get() = visibility == View.GONE

internal val View.isVisible
    get() = visibility == View.VISIBLE

internal fun View.setGone() {
    if (!isGone) {
        visibility = View.GONE
    }
}

internal fun View.setVisible() {
    if (!isVisible) {
        visibility = View.VISIBLE
    }
}

internal fun TextView.setBold(isBold:Boolean){
    if (isBold){
        typeface = Typeface.create("sans-serif-light",Typeface.BOLD)
    }
}

internal val Int.isRawId
    get() = this == 0

internal val Int.isNotRawId
    get() = this != 0
