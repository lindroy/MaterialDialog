package com.lindroy.anddialog.params

import android.content.DialogInterface
import android.os.Parcelable
import android.support.annotation.LayoutRes
import android.support.v4.app.FragmentManager
import android.widget.TextView
import com.lindroid.anddialog.R
import com.lindroy.anddialog.BottomController
import com.lindroy.anddialog.adapter.MDListAdapter
import com.lindroy.anddialog.constants.DialogType
import com.lindroy.anddialog.constants.MD_BOTTOM
import com.lindroy.anddialog.listener.OnDismissListener
import com.lindroy.anddialog.listener.OnItemClickListener
import com.lindroy.anddialog.listener.OnViewHandlerListener
import com.lindroy.anddialog.viewholder.ViewHolder
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

/**
 * @author Lin
 * @date 2019/8/21
 * @function 底部对话框参数
 * @Description
 */
@Parcelize
data class BottomParams(
    @DialogType internal var type: Int = MD_BOTTOM,
    @LayoutRes internal var layoutId: Int = 0,
    internal var tag: String = "BottomDialog",
    internal var isCancelable: Boolean = true,
//        var peekHeight:Int = 0, //
    internal var fullExpanded: Boolean = false, //是否完全展开
    internal var items: MutableList<ListItemBean> = mutableListOf(),
    internal var dismissListener: OnDismissListener? = null,
    internal var viewHandler: OnViewHandlerListener? = null,
    internal var itemClickListener: OnItemClickListener? = null,
    internal var adapter: MDListAdapter<*>? = null
) : Parcelable, BottomInterface<BottomParams> {

    init {
        layoutId = if (isBottom) layoutId else R.layout.dialog_md_bottom_list
    }

    val isBottom
        get() = type == MD_BOTTOM

    @IgnoredOnParcel
    private lateinit var fm: FragmentManager

    /**
     * 设置对话框布局
     * @param layoutId:对话框布局Id
     */
    fun setView(@LayoutRes layoutId: Int) = this.also { it.layoutId = layoutId }

    /**
     * 是否完全展开，默认false
     */
    fun setFullExpanded(fullExpanded: Boolean) = this.also { it.fullExpanded = fullExpanded }

    fun addItem(text: String) = this.apply { items.add(ListItemBean(text)) }

    fun addItems(items: List<String>) = this.also {
        items.forEach {
            addItem(it)
        }
    }

    fun setOnViewHandler(viewHandler: OnViewHandlerListener) =
        this.also { it.viewHandler = viewHandler }

    fun setOnViewHandler(viewHandler: (holder: ViewHolder, dialog: DialogInterface) -> Unit) =
        setOnViewHandler(object : OnViewHandlerListener() {
            override fun onConvert(holder: ViewHolder, dialog: DialogInterface) {
                viewHandler.invoke(holder, dialog)
            }
        })

    fun setOnItemClickListener(listener: OnItemClickListener) =
        this.apply { itemClickListener = listener }

    fun setOnItemClickListener(listener: (position: Int, text: String, itemView: TextView, dialog: DialogInterface) -> Unit) =
        setOnItemClickListener(object :OnItemClickListener(){
            override fun onClick(
                position: Int,
                text: String,
                itemView: TextView,
                dialog: DialogInterface
            ) {
                listener.invoke(position,text,itemView,dialog)
            }

        })


    fun <T : Any> setListAdapter(adapter: MDListAdapter<T>) = this.also { it.adapter = adapter }

    /**
     * 对话框消失监听
     * 用于屏幕旋转保存状态和Java调用
     */
    fun setOnDismissListener(listener: OnDismissListener) =
        this.apply { dismissListener = listener }

    /**
     * 对话框消失监听
     */
    fun setOnDismissListener(listener: (dialog: DialogInterface?) -> Unit) =
        setOnDismissListener(object : OnDismissListener() {
            override fun onDismiss(dialog: DialogInterface?) {
                listener.invoke(dialog)
            }
        })

    companion object {
        fun build(@DialogType type: Int, fm: FragmentManager) =
            BottomParams(type = type).apply { this.fm = fm }
    }

    @JvmOverloads
    fun show(tag: String = this.tag) {
        BottomController.showDialog(this, fm, tag)
    }
}

interface BottomInterface<T : BottomInterface<T>> {

}

