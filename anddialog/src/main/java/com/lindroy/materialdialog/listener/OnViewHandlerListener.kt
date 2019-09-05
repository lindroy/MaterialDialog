package com.lindroy.materialdialog.listener

import android.content.DialogInterface
import android.os.Parcel
import android.os.Parcelable
import com.lindroy.materialdialog.viewholder.ViewHolder

/**
 * @author Lin
 * @date 2019/8/5
 * @function 处理对话框布局中的View
 * @Description
 */
abstract class OnViewHandlerListener: Parcelable {
    constructor()

    private constructor(source: Parcel)

    abstract fun onConvert(holder: ViewHolder, dialog:DialogInterface)

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<OnViewHandlerListener> =
            object : Parcelable.Creator<OnViewHandlerListener> {
                override fun createFromParcel(source: Parcel): OnViewHandlerListener =
                    object : OnViewHandlerListener(source) {
                        override fun onConvert(holder: ViewHolder, dialog: DialogInterface) {}
                    }

                override fun newArray(size: Int): Array<OnViewHandlerListener?> = arrayOfNulls(size)
            }
    }
}