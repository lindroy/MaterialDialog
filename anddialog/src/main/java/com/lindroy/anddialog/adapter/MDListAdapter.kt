package com.lindroy.anddialog.adapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.LayoutRes
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.lindroy.anddialog.viewholder.ViewHolder

/**
 * @author Lin
 * @date 2019/8/21
 * @function
 * @Description
 */
abstract class MDListAdapter<T : Any> private constructor() : BaseAdapter(), Parcelable {

    private lateinit var mContext: Context
    @LayoutRes
    private var layoutId: Int = 0
    private lateinit var items: List<T>


    constructor(mContext: Context, layoutId: Int, items: List<T>) : this() {
        this.mContext = mContext
        this.layoutId = layoutId
        this.items = items
    }

    private constructor(source: Parcel) : this()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vh: ViewHolder = ViewHolder.getInstance(mContext, convertView, layoutId)
        onConvert(vh, position, items[position])
        return vh.convertView
    }

    override fun getItem(position: Int) = items[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = items.size

    abstract fun onConvert(holder: ViewHolder, position: Int, item: T)

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {

    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MDListAdapter<*>> =
            object : Parcelable.Creator<MDListAdapter<*>> {
                override fun createFromParcel(source: Parcel): MDListAdapter<*> =
                    object : MDListAdapter<Any>(source) {
                        override fun onConvert(holder: ViewHolder, position: Int, item: Any) {}
                    }

                override fun newArray(size: Int): Array<MDListAdapter<*>?> = arrayOfNulls(size)
            }
    }


}