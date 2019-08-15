package com.lindroy.anddialog.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import com.lindroid.anddialog.R
import com.lindroy.anddialog.bean.ListItemParams
import com.lindroy.iosdialog.util.getResColor

/**
 * @author Lin
 * @date 2019/8/15
 * @function 多选列表适配器
 * @Description
 */
class MultipleChoiceAdapter(private val mContext: Context, private val items: List<ListItemParams>) : BaseAdapter() {

    private var checkedListener: ((checked: Int, oldChecked: Int) -> Unit)? = null

    private var oldCheckedPos = -1

    init {
        for ((index, item) in items.withIndex()) {
            if (item.isChecked) {
                oldCheckedPos = index
                break
            }
        }
    }

    override fun getCount() = items.size

    override fun getItemId(position: Int) = position.toLong()

    override fun getItem(position: Int) = items[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val vh: ViewHolder
        var itemView = convertView
        when (itemView) {
            null -> {
                vh = ViewHolder()
                itemView = View.inflate(mContext, R.layout.item_md_multiple_choice_list, null) as View
                vh.llMultiple = itemView.findViewById(R.id.llMultiple)
                vh.cbMultiple = itemView.findViewById(R.id.cbMultiple)
                vh.tvMultiple = itemView.findViewById(R.id.tvMultiple)
                itemView.tag = vh
            }
            else -> vh = itemView.tag as ViewHolder
        }
        val item = items[position]
        vh.tvMultiple.apply {
            text = item.text
            textSize = item.textSize
            setTextColor(item.textColor)
        }
        vh.cbMultiple.apply {
            isChecked = item.isChecked
            //设置选中颜色
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val colorStateList = ColorStateList(arrayOf(intArrayOf(-android.R.attr.state_checked),
                        intArrayOf(android.R.attr.state_checked)), intArrayOf(Color.DKGRAY, getResColor(R.color.md_single_choice_radio_button_color)))
                buttonTintList = colorStateList
            }
        }
        vh.llMultiple.setOnClickListener {
            item.isChecked = !item.isChecked
            checkedListener?.invoke(position, oldCheckedPos)
            notifyDataSetChanged()
        }
        return itemView
    }

    /**
     * 选中监听
     */
    fun setOnCheckedListener(listener: (checked: Int, oldChecked: Int) -> Unit) {
        checkedListener = listener
    }

    class ViewHolder {
        lateinit var llMultiple: LinearLayout
        lateinit var cbMultiple: CheckBox
        lateinit var tvMultiple: TextView
    }
}