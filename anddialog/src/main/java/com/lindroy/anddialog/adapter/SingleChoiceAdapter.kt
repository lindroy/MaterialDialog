package com.lindroy.anddialog.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import com.lindroid.anddialog.R
import com.lindroy.anddialog.bean.ListItemParams

/**
 * @author Lin
 * @date 2019/8/15
 * @function 单选列表适配器
 * @Description
 */
class SingleChoiceAdapter(private val mContext: Context, private val items: List<ListItemParams>) : BaseAdapter() {

    override fun getCount() = items.size

    override fun getItemId(position: Int) = position.toLong()

    override fun getItem(position: Int)=items[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val vh: ViewHolder
        var itemView = convertView
        when(itemView){
            null->{
                vh = ViewHolder()
                itemView = View.inflate(mContext,R.layout.item_md_single_choice_list,null) as View
                vh.llSingle = itemView.findViewById(R.id.llItemSingle)
                vh.rbSingle = itemView.findViewById(R.id.rbSingle)
                vh.tvSingle = itemView.findViewById(R.id.tvSingle)
                itemView.tag = vh
            }
            else-> vh = itemView.tag as ViewHolder
        }
        val item = items[position]
        vh.tvSingle.apply {
            Log.e("Tag","item.text=${item.text}")
            text = item.text
            textSize = item.textSize
            setTextColor(item.textColor)
        }
        vh.rbSingle.isChecked = item.isChecked
        vh.llSingle.setOnClickListener {
            if (item.isChecked){
                return@setOnClickListener
            }
            for (itemList in items) {
                itemList.isChecked = false
            }
            item.isChecked = true
            notifyDataSetChanged()
        }
        return itemView
    }

    class ViewHolder{
        lateinit var llSingle:LinearLayout
        lateinit var rbSingle: RadioButton
        lateinit var tvSingle: TextView
    }
}