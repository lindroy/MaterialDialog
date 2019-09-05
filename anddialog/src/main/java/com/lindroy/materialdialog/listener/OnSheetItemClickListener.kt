package com.lindroy.materialdialog.listener

import android.content.DialogInterface
import android.os.Parcel
import android.os.Parcelable
import com.lindroy.materialdialog.params.MDGridItem
import com.lindroy.materialdialog.params.MDListItem

/**
 * @author Lin
 * @date 2019/8/27
 * @function 选项点击事件
 * @Description
 */

abstract class OnSheetItemClickListener<out T> : Parcelable {

    constructor()

    private constructor(source: Parcel)

    abstract fun onClick(position: Int, item: @UnsafeVariance T, dialog: DialogInterface)

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<OnSheetItemClickListener<*>> =
            object : Parcelable.Creator<OnSheetItemClickListener<*>> {
                override fun createFromParcel(source: Parcel): OnSheetItemClickListener<*> =
                    object : OnSheetItemClickListener<Any>(source) {
                        override fun onClick(
                            position: Int,
                            item: Any,
                            dialog: DialogInterface
                        ) {
                        }
                    }

                override fun newArray(size: Int): Array<OnSheetItemClickListener<*>?> =
                    arrayOfNulls(size)
            }
    }
}

/**
 * 列表选项点击事件
 */
abstract class OnListItemClickListener:OnSheetItemClickListener<MDListItem>()
/**
 * 表格选项点击事件
 */
abstract class OnGridItemClickListener:OnSheetItemClickListener<MDGridItem>()