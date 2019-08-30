package com.lindroy.anddialog.params

import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.v4.app.FragmentManager
import android.view.View
import com.lindroy.anddialog.MaterialDialog
import com.lindroy.anddialog.adapter.MDAdapter
import com.lindroy.anddialog.constants.MD_BOTTOM_Grid
import com.lindroy.anddialog.dialog.BottomMenuDialog
import com.lindroy.anddialog.listener.OnGridItemClickListener
import com.lindroy.anddialog.listener.OnItemChildClickListener

/**
 * @author Lin
 * @date 2019/8/24
 * @function 底部表格对话框item参数
 * @Description
 */

data class BottomGridParams(
    internal var iconMaxSize: Int = 0,
    internal var viewIds: IntArray? = null,
    internal var items: MutableList<MDGridItem> = mutableListOf(),
    internal var itemClickListener: OnGridItemClickListener? = null,
    internal var childClickListener: OnItemChildClickListener<*>? = null,
    internal var adapter: MDAdapter<*>? = null
) : ComBottomGridParams<BottomGridParams>() {

    init {
        MaterialDialog.bottomGridP.also {
            spanCount = it.spanCount
            maxHeight = it.maxHeight
            fullExpanded = it.fullExpanded
            dimAmount = it.dimAmount
        }
    }

    fun addItem(text: String, @DrawableRes iconId: Int) =
        this.apply { items.add(MDGridItem(text, iconId = iconId)) }

    fun addItem(text: String, icon: Drawable) =
        this.apply { items.add(MDGridItem(text, icon = icon)) }

    fun <T : Any> setAdapter(adapter: MDAdapter<T>) =
        this.apply { this.adapter = adapter }

    fun setOnItemClickListener(listener: OnGridItemClickListener) =
        this.apply { itemClickListener = listener }

    fun setOnItemClickListener(listener: (position: Int, item: MDGridItem, dialog: DialogInterface) -> Unit) =
        setOnItemClickListener(object : OnGridItemClickListener() {
            override fun onClick(position: Int, item: MDGridItem, dialog: DialogInterface) {
                listener.invoke(position, item, dialog)
            }
        })

    /**
     * 自定义Item布局的点击事件
     */
    fun <T : Any> setOnItemChildClickListener(
        listener: OnItemChildClickListener<T>,
        vararg viewIds: Int
    ) =
        this.apply {
            this.viewIds = viewIds
            childClickListener = listener
        }

    /**
     * 自定义Item布局的点击事件
     */
    fun <T : Any> setOnItemChildClickListener(
        vararg viewIds: Int,
        listener: (adapter: MDAdapter<*>, position: Int, item: T, view: View, dialog: DialogInterface) -> Unit
    ) = setOnItemChildClickListener(object : OnItemChildClickListener<T>() {
        override fun onClick(
            adapter: MDAdapter<*>,
            position: Int,
            item: T,
            view: View,
            dialog: DialogInterface
        ) {
            listener.invoke(adapter, position, item, view, dialog)
        }
    }, *viewIds)

    @JvmOverloads
    fun show(tag: String = this.tag) {
        BottomMenuDialog.showGridDialog(this, fm, tag)
    }

   /* override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BottomGridParams

        if (viewIds != null) {
            if (other.viewIds == null) return false
            if (!viewIds!!.contentEquals(other.viewIds!!)) return false
        } else if (other.viewIds != null) return false

        return true
    }

    override fun hashCode(): Int {
        return viewIds?.contentHashCode() ?: 0
    }*/


    companion object {
        fun build(fm: FragmentManager) =
            BottomGridParams().apply {
                this.fm = fm
            }
    }
}

open class ComBottomGridParams<T : ComBottomGridParams<T>>(
    internal var spanCount: Int = 3
) :
    BaseBottomParams<T>(type = MD_BOTTOM_Grid) {
    /**
     * 设置列数
     */
    fun setSpanCount(count: Int) = this.apply { spanCount = count } as T
}
