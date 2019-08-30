package com.lindroy.anddialog.params

import android.content.DialogInterface
import android.support.v4.app.FragmentManager
import android.view.Gravity
import android.view.View
import com.lindroid.anddialog.R
import com.lindroy.anddialog.MaterialDialog
import com.lindroy.anddialog.adapter.MDAdapter
import com.lindroy.anddialog.constants.MD_BOTTOM_LIST
import com.lindroy.anddialog.dialog.BottomMenuDialog
import com.lindroy.anddialog.listener.OnItemChildClickListener
import com.lindroy.anddialog.listener.OnListItemClickListener
import com.lindroy.iosdialog.util.getResColor
import com.lindroy.iosdialog.util.getResSp
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

    init {
        MaterialDialog.bottomListP.also {
            maxHeight = it.maxHeight
            fullExpanded = it.fullExpanded
            dimAmount = it.dimAmount
            itemParams = it.itemParams.copy()
        }
    }

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

@Suppress("UNCHECKED_CAST")
open class ComBottomListParams<T : ComBottomListParams<T>>(
    internal var itemParams: TextParams = TextParams(
        textSize = getResSp(R.dimen.md_list_item_text_size),
        textColor = getResColor(R.color.md_list_item_text_color),
        gravity = Gravity.CENTER_VERTICAL
    )
) : BaseBottomParams<T>(type = MD_BOTTOM_LIST) {

    /**
     * item的最小高度
     */
    fun setItemMinHeight(minHeight: Int) = this.apply { itemParams.height = minHeight } as T

    @JvmOverloads
    fun setItemTextStyle(
        textSize: Float = itemParams.textSize,
        textColor: Int = itemParams.textColor
    ) = this.apply {
        itemParams.also {
            it.textSize = textSize
            it.textColor = textColor
        }
    } as T
}