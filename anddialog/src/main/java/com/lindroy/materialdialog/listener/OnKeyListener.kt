package com.lindroy.materialdialog.listener

import android.content.DialogInterface
import android.os.Parcel
import android.os.Parcelable
import android.view.KeyEvent

/**
 * @author Lin
 * @date 2019/8/27
 * @function 按键监听事件
 * @Description
 */
abstract class OnKeyListener : Parcelable {
    constructor()

    private constructor(source: Parcel)

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    abstract fun onKey(dialog: DialogInterface, keyCode: Int, event: KeyEvent): Boolean

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<OnKeyListener> =
            object : Parcelable.Creator<OnKeyListener> {
                override fun createFromParcel(source: Parcel): OnKeyListener =
                    object : OnKeyListener(source) {
                        override fun onKey(
                            dialog: DialogInterface,
                            keyCode: Int,
                            event: KeyEvent
                        ): Boolean {
                            return false
                        }

                    }

                override fun newArray(size: Int): Array<OnKeyListener?> =
                    arrayOfNulls(size)
            }
    }
}