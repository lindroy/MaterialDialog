package com.lindroy.materialdialog.listener

import android.content.DialogInterface
import android.os.Parcel
import android.os.Parcelable

/**
 * @author Lin
 * @date 2019/7/29
 * @function 对话框消失监听
 * @Description
 */
abstract class OnDismissListener : Parcelable {
    constructor()

    private constructor(source: Parcel)

    abstract fun onDismiss(dialog: DialogInterface?)

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<OnDismissListener> =
            object : Parcelable.Creator<OnDismissListener> {
                override fun createFromParcel(source: Parcel): OnDismissListener =
                    object : OnDismissListener(source) {
                        override fun onDismiss(dialog: DialogInterface?) {}

                    }

                override fun newArray(size: Int): Array<OnDismissListener?> = arrayOfNulls(size)
            }
    }
}