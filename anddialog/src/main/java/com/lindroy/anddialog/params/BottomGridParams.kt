package com.lindroy.anddialog.params

import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.v4.app.FragmentManager
import com.lindroy.anddialog.adapter.MDRecyclerViewAdapter
import com.lindroy.anddialog.constants.MD_BOTTOM_Grid
import com.lindroy.anddialog.dialog.BottomMenuDialog
import com.lindroy.anddialog.listener.OnGridItemClickListener

/**
 * @author Lin
 * @date 2019/8/24
 * @function 底部表格对话框item参数
 * @Description
 */

data class BottomGridParams(
    internal var spanCount: Int = 3,
    internal var items: MutableList<MDGridItem> = mutableListOf(),
    internal var itemClickListener: OnGridItemClickListener? = null,
    internal var adapter: MDRecyclerViewAdapter<*>? = null
) : BaseBottomParams<BottomGridParams>(
    type = MD_BOTTOM_Grid
) {
    /**
     * 设置列数
     */
    fun setSpanCount(count: Int) = this.apply { spanCount = count }

    fun addItem(text: String, @DrawableRes iconId: Int) =
        this.apply { items.add(MDGridItem(text, iconId = iconId)) }

    fun addItem(text: String, icon: Drawable) =
        this.apply { items.add(MDGridItem(text, icon = icon)) }

    fun <T : Any> setAdapter(adapter: MDRecyclerViewAdapter<T>) =
        this.apply { this.adapter = adapter }

    fun setOnItemClickListener(listener: OnGridItemClickListener) =
        this.apply { itemClickListener = listener }

    fun setOnItemClickListener(listener: (position: Int, item: MDGridItem, dialog: DialogInterface) -> Unit) =
        setOnItemClickListener(object : OnGridItemClickListener() {
            override fun onClick(position: Int, item: MDGridItem, dialog: DialogInterface) {
                listener.invoke(position, item, dialog)
            }
        })

    @JvmOverloads
    fun show(tag: String = this.tag) {
        BottomMenuDialog.showGridDialog(this, fm, tag)
    }

    companion object {
        fun build(fm: FragmentManager) =
            BottomGridParams().apply {
                this.fm = fm
            }
    }
}