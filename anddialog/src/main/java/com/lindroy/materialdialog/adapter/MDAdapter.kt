package com.lindroy.materialdialog.adapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.lindroy.materialdialog.viewholder.RecyclerViewHolder

/**
 * @author Lin
 * @date 2019/8/22
 * @function
 * @Description
 */
abstract class MDAdapter<T : Any>() : RecyclerView.Adapter<RecyclerViewHolder>(), Parcelable {

    private lateinit var mContext: Context
    @LayoutRes
    private var layoutId: Int = 0
    private lateinit var items: List<T>
    private var clickListener: ((position: Int, view: View, item: T) -> Unit)? = null
    private var childIds: IntArray? = null

    private constructor(source: Parcel) : this()

    constructor(mContext: Context, layoutId: Int, items: List<T>) : this() {
        this.mContext = mContext
        this.layoutId = layoutId
        this.items = items
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        RecyclerViewHolder.getInstance(mContext, layoutId, viewGroup)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        onConvert(holder, position, items[position])
        childIds?.forEach {
            holder.itemView.findViewById<View>(it).setOnClickListener { view ->
                clickListener?.invoke(position, view, items[position])
            }
        }
    }

    protected abstract fun onConvert(holder: RecyclerViewHolder, position: Int, item: T)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(layoutId)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<MDAdapter<*>> {
        override fun createFromParcel(parcel: Parcel): MDAdapter<*> =
            object : MDAdapter<Any>(parcel) {
                override fun onConvert(holder: RecyclerViewHolder, position: Int, item: Any) {}
            }

        override fun newArray(size: Int): Array<MDAdapter<*>?> {
            return arrayOfNulls(size)
        }
    }

    internal fun setOnChildClickListener(
        ids: IntArray,
        listener: (position: Int, view: View, item: T) -> Unit
    ) {
        childIds = ids
        clickListener = listener
    }
}