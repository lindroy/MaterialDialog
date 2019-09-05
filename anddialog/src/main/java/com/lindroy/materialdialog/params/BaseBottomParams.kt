package com.lindroy.materialdialog.params

import android.os.Parcelable
import android.support.annotation.FloatRange
import android.support.annotation.IntRange
import android.support.annotation.LayoutRes
import android.support.v4.app.FragmentManager
import com.lindroid.anddialog.R
import com.lindroy.iosdialog.util.screenHeight
import com.lindroy.materialdialog.constants.DialogType
import com.lindroy.materialdialog.constants.MD_BOTTOM
import com.lindroy.materialdialog.listener.OnDismissListener
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

/**
 * @author Lin
 * @date 2019/8/22
 * @function 底部对话框基本参数
 * @Description
 */
@Suppress("UNCHECKED_CAST")
@Parcelize
open class BaseBottomParams<T : BaseBottomParams<T>>(
    @DialogType internal var type: Int = MD_BOTTOM,
    @LayoutRes internal var layoutId: Int = R.layout.dialog_md_bottom_menu,
    internal var tag: String = "BottomDialog",
    internal var cancelableOutside: Boolean = true,
    internal var peekHeight: Int = 0,
    internal var maxHeight: Int = 0,    //最大高度
    internal var fullExpanded: Boolean = false, //是否完全展开
    internal var dimAmount: Float = 0.32F,
    internal var dismissListener: OnDismissListener? = null
) : Parcelable {

    @IgnoredOnParcel
    protected lateinit var fm: FragmentManager

    /**
     * 窗口明暗程度，0~1.0，1全不透明，系统默认值为0.32F
     */
    fun setDimAmount(@FloatRange(from = 0.0, to = 1.0) dimAmount: Float) =
        this.apply { this.dimAmount = dimAmount } as T

    /**
     * 设置最大高度
     */
    fun setMaxHeight(@IntRange(from = 0) maxHeight: Int) =
        this.also {
            it.maxHeight = if (maxHeight > screenHeight) screenHeight else maxHeight
        } as T

    /**
     * 是否完全展开，默认false
     */
    fun setFullExpanded(fullExpanded: Boolean) = this.also { it.fullExpanded = fullExpanded } as T

    /**
     * 对话框弹出的初始高度
     */
    fun setPeekHeight(peekHeight: Int) = this.also { it.peekHeight = peekHeight } as T

    /**
     * 对话框外部是否关闭对话框
     */
    fun setCancelableOutside(isCancelable: Boolean) = this.apply {
        cancelableOutside = isCancelable
    } as T

    internal val isBottom
        get() = type == MD_BOTTOM

}