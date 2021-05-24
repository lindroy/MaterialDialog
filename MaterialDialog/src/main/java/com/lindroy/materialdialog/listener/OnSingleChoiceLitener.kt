package com.lindroy.materialdialog.listener

import android.content.DialogInterface
import android.os.Parcel
import android.os.Parcelable

/**
 * @author Lin
 * @date 2019/8/15
 * @function 单选点击监听
 * @Description
 */
abstract class OnSingleChoiceListener : Parcelable {
    constructor()

    private constructor(source: Parcel)
    /**
     * @param checked:当前选中的item
     * @param oldChecked:先前选中的item
     */
    abstract fun onChecked(dialog: DialogInterface, checked: Int, oldChecked: Int)

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<OnSingleChoiceListener> =
                object : Parcelable.Creator<OnSingleChoiceListener> {
                    override fun createFromParcel(source: Parcel): OnSingleChoiceListener =
                            object : OnSingleChoiceListener(source) {
                                override fun onChecked(dialog: DialogInterface, checked: Int, oldChecked: Int) {}
                            }

                    override fun newArray(size: Int): Array<OnSingleChoiceListener?> = arrayOfNulls(size)
                }
    }
}