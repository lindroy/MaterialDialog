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
abstract class OnMultiChoiceListener : Parcelable {
    constructor()

    private constructor(source: Parcel)

    /**
     * @param position:当前点击的item位置
     * @param isChecked:点击后是否选中
     * @param checkedArray:选中的item下标数组，为null时没有选中项
     */
    abstract fun onChecked(
        position: Int,
        isChecked: Boolean,
        checkedArray: IntArray?,
        dialog: DialogInterface
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<OnMultiChoiceListener> =
            object : Parcelable.Creator<OnMultiChoiceListener> {
                override fun createFromParcel(source: Parcel): OnMultiChoiceListener =
                    object : OnMultiChoiceListener(source) {
                        override fun onChecked(
                            position: Int,
                            isChecked: Boolean,
                            checkedArray: IntArray?,
                            dialog: DialogInterface
                        ) {
                        }
                    }

                override fun newArray(size: Int): Array<OnMultiChoiceListener?> = arrayOfNulls(size)
            }
    }
}