package com.lindroy.anddialog.listener

import android.content.DialogInterface
import android.os.Parcel
import android.os.Parcelable

/**
 * @author Lin
 * @date 2019/8/16
 * @function 多选点击监听
 * @Description
 */
abstract class OnMultiChoiceListener :Parcelable{
    /**
     * @param position:当前点击的item位置
     * @param isChecked:点击后是否选中
     */
    abstract fun onChecked(dialog: DialogInterface, position: Int, isChecked: Boolean)

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<OnMultiChoiceListener> =
                object : Parcelable.Creator<OnMultiChoiceListener> {
                    override fun createFromParcel(source: Parcel): OnMultiChoiceListener =
                            object : OnMultiChoiceListener() {
                                /**
                                 * @param position:当前点击的item位置
                                 * @param isChecked:点击后是否选中
                                 */
                                override fun onChecked(dialog: DialogInterface, position: Int, isChecked: Boolean) {
                                }
                            }

                    override fun newArray(size: Int): Array<OnMultiChoiceListener?> = arrayOfNulls(size)
                }
    }
}