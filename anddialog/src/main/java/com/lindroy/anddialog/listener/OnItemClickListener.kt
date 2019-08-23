package com.lindroy.anddialog.listener

import android.content.DialogInterface
import android.os.Parcel
import android.os.Parcelable
import com.lindroy.anddialog.params.ItemBean

/**
 * @author Lin
 * @date 2019/8/21
 * @function 列表item点击事件
 * @Description
 */
abstract class OnItemClickListener : Parcelable {
    constructor()

    private constructor(source: Parcel)

    abstract fun onClick(position: Int, item: ItemBean, dialog: DialogInterface)

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<OnItemClickListener> =
            object : Parcelable.Creator<OnItemClickListener> {
                override fun createFromParcel(source: Parcel): OnItemClickListener =
                    object : OnItemClickListener(source) {
                        override fun onClick(
                            position: Int,
                            item:ItemBean,
                            dialog: DialogInterface
                        ) {
                        }
                    }

                override fun newArray(size: Int): Array<OnItemClickListener?> = arrayOfNulls(size)
            }
    }
}