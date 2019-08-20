package com.lindroy.anddialog

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Parcelable
import android.support.annotation.LayoutRes
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.lindroy.anddialog.listener.OnViewHandlerListener
import com.lindroy.anddialog.viewholder.ViewHolder
import com.lindroy.iosdialog.util.screenHeight
import kotlinx.android.parcel.Parcelize


/**
 * @author Lin
 * @date 2019/8/20
 * @function 底部对话框
 * @Description
 */
class BottomController : BottomSheetDialogFragment() {

    private lateinit var mContext: Context
    private val mParams = BottomParams()
    private lateinit var fm: FragmentManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        //去除Android4.4的标题栏
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(mParams.layoutId, container)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context!!
    }

    override fun onStart() {
        super.onStart()
        //去除白色的背景
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val bottomSheet = dialog.findViewById<View>(android.support.design.R.id.design_bottom_sheet)
        bottomSheet.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        view?.let { v ->
            v.post {
                val parent = v.parent as View
                val params = parent.layoutParams as CoordinatorLayout.LayoutParams
                val behavior = params.behavior
                val bottomSheetBehavior = behavior as BottomSheetBehavior<*>?
                bottomSheetBehavior?.let {
                    it.peekHeight = if (mParams.fullExpanded) v.measuredHeight else screenHeight / 2
                    it.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                        override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        }

                        override fun onStateChanged(bottomSheet: View, newState: Int) {
                        }

                    })
                }
                parent.setBackgroundColor(Color.WHITE)
            }
        }
    }



    /**
     * 处理对话框中的View
     * 用于屏幕旋转保存状态和Java调用
     */
    fun setOnViewHandler(listener: OnViewHandlerListener) =
        this.apply { mParams.viewHandler = listener }

    /**
     * 处理对话框中的View
     */
    fun setOnViewHandler(viewHandler: (holder: ViewHolder, dialog: DialogInterface) -> Unit) =
        setOnViewHandler(object : OnViewHandlerListener() {
            override fun onConvert(holder: ViewHolder, dialog: DialogInterface) {
                viewHandler.invoke(holder, dialog)
            }
        })

    @Parcelize
    data class BottomParams(
        @LayoutRes var layoutId: Int = 0,
        var tag: String = "BottomDialog",
        var isCancelable: Boolean = true,
//        var peekHeight:Int = 0, //
        var fullExpanded: Boolean = false, //是否完全展开
        var items: List<String> = listOf(),
        var viewHandler: OnViewHandlerListener? = null
    ) : Parcelable {
        private lateinit var fm: FragmentManager

        /**
         * 设置对话框布局
         * @param layoutId:对话框布局Id
         */
        fun setView(@LayoutRes layoutId: Int) = this.apply { this.layoutId = layoutId }

        companion object {
            fun build(fm: FragmentManager) = BottomParams().apply { this.fm = fm }
        }

        @JvmOverloads
        fun show(tag: String = this.tag) {
            BottomController.showDialog(fm, tag)
        }
    }

    companion object {

        fun showDialog(fm: FragmentManager, tag: String) = BottomController().show(fm, tag)
    }

}