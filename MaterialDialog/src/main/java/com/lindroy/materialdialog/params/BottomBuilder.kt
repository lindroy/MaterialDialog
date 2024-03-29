package com.lindroy.materialdialog.params

import android.content.DialogInterface
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentManager
import com.lindroy.materialdialog.MaterialDialog
import com.lindroy.materialdialog.constants.MD_BOTTOM
import com.lindroy.materialdialog.dialog.BottomDialog
import com.lindroy.materialdialog.listener.OnDismissListener
import com.lindroy.materialdialog.listener.OnViewHandlerListener
import com.lindroy.materialdialog.viewholder.ViewHolder
import kotlinx.android.parcel.Parcelize

/**
 * @author Lin
 * @date 2019/8/21
 * @function 底部对话框参数
 * @Description
 */
@Parcelize
data class BottomBuilder(
    internal var viewHandler: OnViewHandlerListener? = null
) : ComBottomParams<BottomBuilder>() {

    init {
        MaterialDialog.bottomP.also {
            maxHeight = it.maxHeight
            fullExpanded = it.fullExpanded
            dimAmount = it.dimAmount
        }
    }

    /**
     * 设置对话框布局
     * @param layoutId:对话框布局Id
     */
    fun setView(@LayoutRes layoutId: Int) = this.also { it.layoutId = layoutId }

    fun setOnViewHandler(viewHandler: OnViewHandlerListener) =
        this.also { it.viewHandler = viewHandler }

    fun setOnViewHandler(viewHandler: (holder: ViewHolder, dialog: DialogInterface) -> Unit) =
        setOnViewHandler(object : OnViewHandlerListener() {
            override fun onConvert(holder: ViewHolder, dialog: DialogInterface) {
                viewHandler.invoke(holder, dialog)
            }
        })

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
        fun build(fm: androidx.fragment.app.FragmentManager) =
            BottomBuilder().apply {
                this.fm = fm
            }
    }

    @JvmOverloads
    fun show(tag: String = this.tag) {
        BottomDialog.showDialog(this, fm, tag)
    }
}

/**
 * 底部对话框公共配置方法
 */
open class ComBottomParams<T : ComBottomParams<T>> : BaseBottomParams<T>(type = MD_BOTTOM)


