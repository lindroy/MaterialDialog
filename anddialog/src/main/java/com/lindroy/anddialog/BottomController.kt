package com.lindroy.anddialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import com.lindroid.anddialog.R
import com.lindroy.anddialog.adapter.MDListAdapter
import com.lindroy.anddialog.params.BottomParams
import com.lindroy.anddialog.params.ListItemBean
import com.lindroy.anddialog.viewholder.ViewHolder
import com.lindroy.iosdialog.util.dp2px
import com.lindroy.iosdialog.util.screenHeight
import kotlinx.android.synthetic.main.dialog_md_bottom_list.*


/**
 * @author Lin
 * @date 2019/8/20
 * @function 底部对话框
 * @Description
 */
class BottomController : BottomSheetDialogFragment() {

    private lateinit var mContext: Context
    private lateinit var mParams: BottomParams

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (mParams.isBottom) {
            mParams.viewHandler?.onConvert(ViewHolder(view), dialog)
        }
        listView.apply {
            adapter = object :
                MDListAdapter<ListItemBean>(mContext, R.layout.item_md_bottom_list, mParams.items) {
                override fun onConvert(holder: ViewHolder, position: Int, item: ListItemBean) {
                    holder.getTextView(R.id.tvItem).also {
                        it.minHeight = dp2px(50F).toInt()
                        it.text = item.text
                        it.textSize = item.textSize
                        it.gravity = item.gravity
                        it.setPadding(
                            item.paddingLeft,
                            item.paddingTop,
                            item.paddingRight,
                            item.paddingBottom
                        )
                        it.setTextColor(item.textColor)
                    }
                }
            }
            setOnItemClickListener { parent, view, position, id ->
                mParams.itemClickListener?.onClick(
                    position,
                    mParams.items[position].text,
                    view as TextView,
                    dialog
                )
            }
        }
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

    companion object {
        internal fun showDialog(params: BottomParams, fm: FragmentManager, tag: String) =
            BottomController().apply {
                mParams = params
                show(fm, tag)
            }
    }

}