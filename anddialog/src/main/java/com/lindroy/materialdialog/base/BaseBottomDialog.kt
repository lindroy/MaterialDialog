package com.lindroy.materialdialog.base

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.CoordinatorLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.lindroy.iosdialog.util.screenHeight
import com.lindroy.materialdialog.params.BaseBottomParams

/**
 * @author Lin
 * @date 2019/8/22
 * @function 底部对话框基类
 * @Description
 */
abstract class BaseBottomDialog : BottomSheetDialogFragment() {

    abstract var bottomParams: BaseBottomParams<*>
    protected lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = bottomParams.cancelableOutside
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        //去除Android4.4的标题栏
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(bottomParams.layoutId, container)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context!!
    }

    override fun onStart() {
        super.onStart()
        //去除白色的背景
        dialog.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setDimAmount(bottomParams.dimAmount)
        }
        val bottomSheet = dialog.findViewById<View>(android.support.design.R.id.design_bottom_sheet)
        bottomSheet.layoutParams.height =
            if (bottomParams.maxHeight > 0) bottomParams.maxHeight else ViewGroup.LayoutParams.WRAP_CONTENT
        view?.let { v ->
            v.post {
                val parent = v.parent as View
                val params = parent.layoutParams as CoordinatorLayout.LayoutParams
                val behavior = params.behavior
                val bottomSheetBehavior = behavior as BottomSheetBehavior<*>?
                bottomSheetBehavior?.let {
                    it.peekHeight = when {
                        bottomParams.fullExpanded -> v.measuredHeight
                        bottomParams.peekHeight > 0 -> bottomParams.peekHeight
                        else -> screenHeight / 2
                    }
                    it.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                        override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        }

                        override fun onStateChanged(bottomSheet: View, newState: Int) {
                            //完全收起时自动关闭对话框
                            if ((BottomSheetBehavior.STATE_HIDDEN == newState)) {
                                dismiss()
                            }
                        }

                    })
                }
                parent.setBackgroundColor(Color.WHITE)
            }
        }
    }
}