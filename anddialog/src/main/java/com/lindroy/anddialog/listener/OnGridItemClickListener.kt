package com.lindroy.anddialog.listener

import android.content.DialogInterface
import android.os.Parcel
import android.os.Parcelable
import com.lindroy.anddialog.params.MDGridItem

/**
 * @author Lin
 * @date 2019/8/21
 * @function 列表item点击事件
 * @Description
 */
@Deprecated("由OnSheetItemClickListener替代")
abstract class OnGridItemClickListener : Parcelable {
    constructor()

    private constructor(source: Parcel)

    abstract fun onClick(position: Int, item: MDGridItem, dialog: DialogInterface)

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<OnGridItemClickListener> =
            object : Parcelable.Creator<OnGridItemClickListener> {
                override fun createFromParcel(source: Parcel): OnGridItemClickListener =
                    object : OnGridItemClickListener(source) {
                        override fun onClick(
                            position: Int,
                            item:MDGridItem,
                            dialog: DialogInterface
                        ) {
                        }
                    }

                override fun newArray(size: Int): Array<OnGridItemClickListener?> = arrayOfNulls(size)
            }
    }
}