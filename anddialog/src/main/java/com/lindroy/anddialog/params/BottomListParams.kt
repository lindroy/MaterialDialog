package com.lindroy.anddialog.params

import android.content.DialogInterface
import android.support.v4.app.FragmentManager
import android.view.View
import com.lindroy.anddialog.adapter.MDAdapter
import com.lindroy.anddialog.constants.MD_BOTTOM_LIST
import com.lindroy.anddialog.dialog.BottomMenuDialog
import com.lindroy.anddialog.listener.OnItemChildClickListener
import com.lindroy.anddialog.listener.OnListItemClickListener
import kotlinx.android.parcel.Parcelize

/**
 * @author Lin
 * @date 2019/8/22
 * @function 底部选项对话框
 * @Description
 */
@Parcelize
class BottomListParams(
    internal var items: MutableList<MDListItem> = mutableListOf(),
    internal var viewIds: IntArray? = null,
    internal var itemClickListener: OnListItemClickListener? = null,
    internal var childClickListener: OnItemChildClickListener<*>? = null,
    internal var adapter: MDAdapter<*>? = null
) : ComBottomListParams<BottomListParams>() {

    fun addItem(text: String) =
        this.apply { items.add(MDListItem(text)) }

    /* @JvmOverloads
     fun addItem(text: String, icon: Drawable? = null) =
         this.apply { items.add(MDItemBean(text, icon = icon)) }*/

    fun addItems(items: List<String>) = this.also {
        items.forEach {
            addItem(it)
        }
    }

    fun <T : Any> setAdapter(adapter: MDAdapter<T>) =
        this.apply { this.adapter = adapter }

    fun setOnItemClickListener(listener: OnListItemClickListener) =
        this.apply { itemClickListener = listener }

    fun setOnItemClickListener(listener: (position: Int, item: MDListItem, dialog: DialogInterface) -> Unit) =
        setOnItemClickListener(object : OnListItemClickListener() {
            override fun onClick(
                position: Int,
                item: MDListItem,
                dialog: DialogInterface
            ) {
                listener.invoke(position, item, dialog)
            }
        })

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

    /**
     * 自定义Item布局的点击事件
     */
    fun <T : Any> setOnItemChildClickListener(
        listener: OnItemChildClickListener<T>,
        vararg viewIds: Int
    ) = this.apply {
        this.viewIds = viewIds
        childClickListener = listener
    }

    @JvmOverloads
    fun show(tag: String = this.tag) {
        BottomMenuDialog.showListDialog(this, fm, tag)
    }

    companion object {
        fun build(fm: FragmentManager) =
            BottomListParams().apply {
                this.fm = fm
            }
    }
}

open class ComBottomListParams<T : ComBottomListParams<T>> :
    BaseBottomParams<T>(type = MD_BOTTOM_LIST)