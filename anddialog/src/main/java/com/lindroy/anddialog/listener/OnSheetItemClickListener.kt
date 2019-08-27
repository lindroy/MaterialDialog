package com.lindroy.anddialog.listener

import android.content.DialogInterface
import android.os.Parcel
import android.os.Parcelable

/**
 * @author Lin
 * @date 2019/8/27
 * @function
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