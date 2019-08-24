package com.lindroy.anddialog.dialog

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.lindroid.anddialog.R
import com.lindroy.anddialog.adapter.MDRecyclerViewAdapter
import com.lindroy.anddialog.base.BaseBottomDialog
import com.lindroy.anddialog.params.*
import com.lindroy.anddialog.viewholder.RecyclerViewHolder
import com.lindroy.iosdialog.util.dp2px
import com.lindroy.iosdialog.util.screenWidth
import kotlinx.android.synthetic.main.dialog_md_bottom_menu.*
import kotlin.properties.Delegates

/**
 * @author Lin
 * @date 2019/8/22
 * @function 底部选项对话框
 * @Description
 */
class BottomMenuDialog : BaseBottomDialog() {

    private var listParams: BottomListParams? by Delegates.observable(null) { property, oldValue: BottomListParams?, newValue: BottomListParams? ->
        if (newValue != null) {
            bottomParams = newValue
        }
    }

    private var gridParams: BottomGridParams? by Delegates.observable(null) { property, oldValue: BottomGridParams?, newValue: BottomGridParams? ->
        if (newValue != null) {
            bottomParams = newValue
        }
    }

    override var bottomParams: BaseBottomParams<*> = BottomListParams()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listParams?.let { p ->
            rvMenu.apply {
                layoutManager = LinearLayoutManager(mContext)
                adapter = object : MDRecyclerViewAdapter<MDListItem>(
                    mContext,
                    R.layout.item_md_bottom_list,
                    p.items
                ) {
                    override fun onConvert(
                        holder: RecyclerViewHolder,
                        position: Int,
                        item: MDListItem
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
                                p.itemClickListener?.onClick(position, item, dialog)
                            }
                        }
                    }
                }
            }
        }

        gridParams?.let { p ->
            rvMenu.apply {
                layoutManager = GridLayoutManager(mContext, p.spanCount)
                adapter = object : MDRecyclerViewAdapter<MDGridItem>(
                    mContext,
                    R.layout.item_md_bottom_grid,
                    p.items
                ) {
                    override fun onConvert(
                        holder: RecyclerViewHolder,
                        position: Int,
                        item: MDGridItem
                    ) {
                        holder.getTextView(R.id.tvGrid).also {
                            it.text = item.text
                            it.textSize = item.textSize
                            it.gravity = item.gravity
                            it.setTextColor(item.textColor)
                        }
                        holder.getImageView(R.id.ivGrid).also {
                            if (item.iconId != 0) {
                                it.setImageResource(item.iconId)
                            } else if (item.icon == null) {
                                it.setImageDrawable(item.icon)
                            }
                        }
                        holder.getView<LinearLayout>(R.id.llGrid).also {
                            val layoutParams = it.layoutParams
                            layoutParams.width = screenWidth / p.spanCount
                            it.setPadding(0, item.paddingTopBottom, 0, item.paddingTopBottom)
                            setOnClickListener {

                            }
                        }
                    }

                }
            }
        }


    }


    companion object {
        internal fun showListDialog(params: BottomListParams, fm: FragmentManager, tag: String) =
            BottomMenuDialog().apply {
                listParams = params
                show(fm, tag)
            }

        internal fun showGridDialog(params: BottomGridParams, fm: FragmentManager, tag: String) =
            BottomMenuDialog().apply {
                gridParams = params
                show(fm, tag)
            }
    }
}