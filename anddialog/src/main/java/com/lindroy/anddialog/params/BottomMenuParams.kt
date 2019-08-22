package com.lindroy.anddialog.params

import android.content.DialogInterface
import android.support.v4.app.FragmentManager
import android.widget.TextView
import com.lindroy.anddialog.adapter.MDListAdapter
import com.lindroy.anddialog.constants.MD_BOTTOM_LIST
import com.lindroy.anddialog.dialog.BottomMenuDialog
import com.lindroy.anddialog.listener.OnItemClickListener
import kotlinx.android.parcel.Parcelize

/**
 * @author Lin
 * @date 2019/8/22
 * @function 底部选项对话框
 * @Description
 */
@Parcelize
class BottomMenuParams(
    internal var items: MutableList<ListItemBean> = mutableListOf(),
    internal var itemClickListener: OnItemClickListener? = null,
    internal var adapter: MDListAdapter<*>? = null
):BaseBottomParams<BottomMenuParams>(type = MD_BOTTOM_LIST) {

    fun addItem(text: String) = this.apply { items.add(ListItemBean(text)) }

    fun addItems(items: List<String>) = this.also {
        items.forEach {
            addItem(it)
        }
    }

    fun <T : Any> setListAdapter(adapter: MDListAdapter<T>) = this.also { it.adapter = adapter }

    fun setOnItemClickListener(listener: OnItemClickListener) =
        this.apply { itemClickListener = listener }

    fun setOnItemClickListener(listener: (position: Int, text: String, itemView: TextView, dialog: DialogInterface) -> Unit) =
        setOnItemClickListener(object : OnItemClickListener() {
            override fun onClick(
                position: Int,
                text: String,
                itemView: TextView,
                dialog: DialogInterface
            ) {
                listener.invoke(position, text, itemView, dialog)
            }

        })

    @JvmOverloads
    fun show(tag: String = this.tag) {
        BottomMenuDialog.showDialog(this, fm, tag)
    }

    companion object {
        fun build(fm: FragmentManager) =
            BottomMenuParams().apply {
                this.fm = fm
            }
    }
}