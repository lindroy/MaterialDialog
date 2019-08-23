package com.lindroy.anddialog.params

import android.content.DialogInterface
import android.support.annotation.LayoutRes
import android.support.v4.app.FragmentManager
import com.lindroy.anddialog.constants.MD_BOTTOM
import com.lindroy.anddialog.dialog.BottomDialog
import com.lindroy.anddialog.listener.OnDismissListener
import com.lindroy.anddialog.listener.OnViewHandlerListener
import com.lindroy.anddialog.viewholder.ViewHolder
import kotlinx.android.parcel.Parcelize

/**
 * @author Lin
 * @date 2019/8/21
 * @function 底部对话框参数
 * @Description
 */
@Parcelize
data class BottomParams(

//        var peekHeight:Int = 0, //
    internal var viewHandler: OnViewHandlerListener? = null
) : BaseBottomParams<BottomParams>(type = MD_BOTTOM) {


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
        fun build(fm: FragmentManager) =
            BottomParams().apply {
                this.fm = fm
            }
    }

    @JvmOverloads
    fun show(tag: String = this.tag) {
        BottomDialog.showDialog(this, fm, tag)
    }
}


