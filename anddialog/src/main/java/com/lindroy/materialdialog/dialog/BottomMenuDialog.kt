package com.lindroy.materialdialog.dialog

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.lindroid.anddialog.R
import com.lindroy.materialdialog.adapter.MDAdapter
import com.lindroy.materialdialog.base.BaseBottomDialog
import com.lindroy.materialdialog.params.*
import com.lindroy.materialdialog.viewholder.RecyclerViewHolder
import com.lindroy.iosdialog.util.getResPx
import com.lindroy.iosdialog.util.screenWidth
import kotlinx.android.synthetic.main.dialog_md_bottom_menu.*
import kotlin.properties.Delegates

/**
 * @author Lin
 * @date 2019/8/22
 * @function 底部选项（分列表和表格两种）对话框
 * @Description
 */
internal class BottomMenuDialog : BaseBottomDialog() {

    private var listParams: BottomListParams? by Delegates.observable(null) { property, oldValue: BottomListParams?, newValue: BottomListParams? ->
        if (newValue != null) {
            bottomParams = newValue
        }
    }

    private var gridParams: BottomGridBuilder? by Delegates.observable(null) { property, oldValue: BottomGridBuilder?, newValue: BottomGridBuilder? ->
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
                if (p.adapter != null) {
                    adapter = p.adapter
                    if (p.viewIds?.isNotEmpty() == true) {
                        p.adapter?.setOnChildClickListener(p.viewIds!!) { position, view, any ->
                            p.childClickListener?.onClick(p.adapter!!, position, any, view, dialog)
                        }
                    }
                    return
                }
                adapter = object : MDAdapter<MDListItem>(
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
                            it.minimumHeight =
                                if (p.itemParams.minHeight > 0) p.itemParams.minHeight else getResPx(
                                    R.dimen.md_list_item_height
                                )
                            it.text = item.text
                            it.textSize = p.itemParams.textSize
                            it.gravity = p.itemParams.gravity
                            it.setPadding(
                                item.paddingLeft,
                                item.paddingTop,
                                item.paddingRight,
                                item.paddingBottom
                            )
                            it.setTextColor(p.itemParams.textColor)
                        }
                        holder.getView<LinearLayout>(R.id.llItem).also {
                            it.setOnClickListener {
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
                /* if (p.adapter != null) {
                     adapter = p.adapter
                     p.adapter?.setOnChildClickListener() { position, item ->
                         p.itemClickListener?.onClick(position, item as MDGridItem, dialog)
                     }
                     return
                 }*/
                adapter = object : MDAdapter<MDGridItem>(
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
                            it.textSize = p.itemParams.textSize
                            it.gravity = p.itemParams.gravity
                            it.maxLines = p.itemParams.maxLines
                            it.setTextColor(p.itemParams.textColor)
                        }
                        holder.getImageView(R.id.ivGrid).also {
                            it.scaleType = p.iconParams.scaleType
                            if (p.iconParams.iconMaxSize > 0) {
                                it.maxHeight = p.iconParams.iconMaxSize
                                it.maxWidth = p.iconParams.iconMaxSize
                            }
                            if (p.iconParams.iconSize > 0) {
                                val lp = it.layoutParams
                                lp.height = p.iconParams.iconSize
                                lp.width = p.iconParams.iconSize
                                it.layoutParams = lp
                            }
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
                            it.setOnClickListener {
                                p.itemClickListener?.onClick(position, item, dialog)
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

        internal fun showGridDialog(builder: BottomGridBuilder, fm: FragmentManager, tag: String) =
            BottomMenuDialog().apply {
                gridParams = builder
                show(fm, tag)
            }
    }
}