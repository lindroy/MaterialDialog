package com.lindroy.anddialog.listener

import android.content.DialogInterface
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.Button

/**
 * @author Lin
 * @date 2019/7/29
 * @function 对话框控件点击监听
 * @Description
 */
abstract class OnDialogClickListener<T:View> : Parcelable {

    constructor()

    private constructor(source: Parcel)

    abstract fun onClick(view: T,dialog: DialogInterface)

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<OnDialogClickListener<*>> =
            object : Parcelable.Creator<OnDialogClickListener<*>> {
                override fun createFromParcel(source: Parcel): OnDialogClickListener<*> =
                    object : OnDialogClickListener<View>(source) {
                        override fun onClick(view: View,dialog: DialogInterface) {}
                    }

                override fun newArray(size: Int): Array<OnDialogClickListener<*>?> = arrayOfNulls(size)
            }
    }
}

abstract class OnButtonClickListener:OnDialogClickListener<Button>()