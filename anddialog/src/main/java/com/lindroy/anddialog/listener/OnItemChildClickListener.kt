package com.lindroy.anddialog.listener

import android.content.DialogInterface
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import com.lindroy.anddialog.adapter.MDRecyclerViewAdapter

/**
 * @author Lin
 * @date 2019/8/27
 * @function 自定义Item布局上的控件点击事件
 * @Description
 */
abstract class OnItemChildClickListener<out T>:Parcelable {
    constructor()

    private constructor(source: Parcel)

    abstract fun onClick(adapter:MDRecyclerViewAdapter<*>,position: Int, item:@UnsafeVariance T, view: View, dialog: DialogInterface)

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    companion object{
        val CREATOR: Parcelable.Creator<OnItemChildClickListener<*>> =
            object : Parcelable.Creator<OnItemChildClickListener<*>> {
                override fun createFromParcel(source: Parcel): OnItemChildClickListener<*> =
                    object : OnItemChildClickListener<Any>(source) {
                        override fun onClick(
                            adapter:MDRecyclerViewAdapter<*>,
                            position: Int,
                            item:Any,
                            view: View,
                            dialog: DialogInterface
                        ) {
                        }
                    }

                override fun newArray(size: Int): Array<OnItemChildClickListener<*>?> = arrayOfNulls(size)
            }
    }
}