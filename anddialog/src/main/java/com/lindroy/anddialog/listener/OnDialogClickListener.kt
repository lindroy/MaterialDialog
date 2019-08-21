package com.lindroy.anddialog.listener

import android.content.DialogInterface
import android.os.Parcel
import android.os.Parcelable

/**
 * @author Lin
 * @date 2019/7/29
 * @function 对话框按钮点击监听
 * @Description
 */
abstract class OnDialogClickListener : Parcelable {

    constructor()

    private constructor(source: Parcel)

    abstract fun onClick(dialog: DialogInterface)

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<OnDialogClickListener> =
            object : Parcelable.Creator<OnDialogClickListener> {
                override fun createFromParcel(source: Parcel): OnDialogClickListener =
                    object : OnDialogClickListener(source) {
                        override fun onClick(dialog: DialogInterface) {}
                    }

                override fun newArray(size: Int): Array<OnDialogClickListener?> = arrayOfNulls(size)
            }
    }

}