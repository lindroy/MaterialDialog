package com.lindroy.anddialog.params

import android.os.Parcelable
import android.support.annotation.LayoutRes
import android.support.v4.app.FragmentManager
import com.lindroy.anddialog.constants.DialogType
import com.lindroy.anddialog.constants.MD_BOTTOM
import com.lindroy.anddialog.constants.MD_BOTTOM_LIST
import com.lindroy.anddialog.listener.OnDismissListener
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

/**
 * @author Lin
 * @date 2019/8/22
 * @function 底部对话框基本参数
 * @Description
 */
@Parcelize
open class BaseBottomParams<T : BaseBottomParams<T>>(
    @DialogType internal var type: Int = MD_BOTTOM,
    @LayoutRes internal var layoutId: Int = 0,
    internal var tag: String = "BottomDialog",
    internal var cancelableOutside: Boolean = true,
    internal var fullExpanded: Boolean = false, //是否完全展开
    internal var dimAmount:Float = 0.32F,
    internal var dismissListener: OnDismissListener? = null
) : Parcelable{

    @IgnoredOnParcel
    protected lateinit var fm: FragmentManager


    val isBottom
        get() = type == MD_BOTTOM

    val isBottomList
        get() = type == MD_BOTTOM_LIST
}