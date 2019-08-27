package com.lindroy.anddialog.listener

import android.content.DialogInterface
import android.os.Parcel
import android.os.Parcelable

/**
 * @author Lin
 * @date 2019/8/21
 * @function 列表item点击事件
 * @Description
 */
abstract class OnListItemClickListener <out T>: Parcelable {
    constructor()

    private constructor(source: Parcel)

    abstract fun onClick(position: Int, item:@UnsafeVariance T, dialog: DialogInterface)

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<OnListItemClickListener<*>> =
            object : Parcelable.Creator<OnListItemClickListener<*>> {
                override fun createFromParcel(source: Parcel): OnListItemClickListener<*> =
                    object : OnListItemClickListener<Any>(source) {
                        override fun onClick(
                            position: Int,
                            item:Any,
                            dialog: DialogInterface
                        ) {
                        }
                    }

                override fun newArray(size: Int): Array<OnListItemClickListener<*>?> = arrayOfNulls(size)
            }
    }
}