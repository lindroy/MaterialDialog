package com.lindroy.anddialog.dialog

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.lindroid.anddialog.R
import com.lindroy.anddialog.adapter.MDRecyclerViewAdapter
import com.lindroy.anddialog.base.BaseBottomDialog
import com.lindroy.anddialog.params.BaseBottomParams
import com.lindroy.anddialog.params.BottomMenuParams
import com.lindroy.anddialog.params.ItemBean
import com.lindroy.anddialog.viewholder.RecyclerViewHolder
import com.lindroy.iosdialog.util.dp2px
import kotlinx.android.synthetic.main.dialog_md_bottom_menu.*
import kotlin.properties.Delegates

/**
 * @author Lin
 * @date 2019/8/22
 * @function 底部选项对话框
 * @Description
 */
class BottomMenuDialog : BaseBottomDialog() {

    private var mParams: BottomMenuParams by Delegates.observable(BottomMenuParams()) { property, oldValue, newValue ->
        bottomParams = newValue
    }

    override var bottomParams: BaseBottomParams<*> = mParams


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvMenu.apply {
            layoutManager = LinearLayoutManager(mContext)
            adapter = object : MDRecyclerViewAdapter<ItemBean>(
                mContext,
                R.layout.item_md_bottom_list,
                mParams.items
            ) {
                override fun onConvert(
                    holder: RecyclerViewHolder,
                    position: Int,
                    item: ItemBean
                ) {
                    holder.getTextView(R.id.tvItem).also {
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
                    holder.getView<LinearLayout>(R.id.llItem).also {
                        it.minimumHeight = dp2px(50F).toInt()
                        setOnClickListener {
                            mParams.itemClickListener?.onClick(position, item, dialog)
                        }
                    }
                }
            }
        }

    }


    companion object {
        internal fun showDialog(params: BottomMenuParams, fm: FragmentManager, tag: String) =
            BottomMenuDialog().apply {
                mParams = params
                show(fm, tag)
            }
    }
}