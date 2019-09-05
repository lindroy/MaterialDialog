package com.lindroid.sample

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.lindroid.androidutilskt.app.AndUtil
import com.lindroid.androidutilskt.extension.dp2px
import com.lindroid.androidutilskt.extension.shortToast
import com.lindroid.sample.bean.ListItemBean
import com.lindroy.materialdialog.MaterialDialog
import com.lindroy.materialdialog.adapter.MDAdapter
import com.lindroy.materialdialog.viewholder.RecyclerViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mContext: Context
    private lateinit var cities: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndUtil.init(this.application).setLogGlobalConfig { }
        mContext = this
        cities = resources.getStringArray(R.array.cities)
        MaterialDialog.init(this.application)
        //提示对话框
//            .initAlert {
//                setAnimStyle(0)                 //设置对话框的动画样式
//                setDimAmount(0F)                //窗口明暗程度，0~1.0，1全不透明，默认为0.32
//                setWidth(0)                     //设置对话框宽度，单位为px
//                setWidthScale(0.85F)            //设置宽度与屏幕宽度比例， 如果已经设置了具体宽度则该方法无效
//                setHeightScale(0F)              //设置高度与屏幕高度比例
//                setKeepPortraitWidth(true)      //横屏时是否保持竖屏时的宽度，默认为true
//                setCornerRadius(0F)             //设置背景圆角半径，默认为4dp
//                setBackgroundColor(0)           //设置背景颜色
//                setBackgroundColorRes(0)        //设置背景颜色，参数为资源Id
//                setBackgroundAlpha(1F)          //设置背景透明度
//                setTitleColor(0)                //设置标题文字颜色
//                setTitleColorRes(0)             //设置标题文字颜色，参数为资源Id
//                setTitleSize(0)                 //设置标题文字大小
//                setMessageColor(0)              //设置信息文字颜色
//                setMessageColorRes(0)           //设置信息文字颜色，参数为资源Id
//                setMessageSize(0)               //设置信息文字大小
//                setPositiveText("Ok")           //设置Positive按钮文字，为空时不显示
//                setNegativeText("Cancel")       //设置Negative按钮文字，为空时不显示
//                setPositiveTextColor(0)         //设置Positive按钮的文字颜色
//                setPositiveTextColorRes(0)      //设置Positive按钮的文字颜色，参数为资源Id
//                setNegativeTextColor(0)         //设置Negative按钮的文字颜色
//                setNegativeTextColorRes(0)      //设置Negative按钮的文字颜色，参数为资源Id
//                setNeutralTextColor(0)          //设置Neutral按钮的文字颜色
//                setNeutralTextColorRes(0)       //设置Neutral按钮的文字颜色，参数为资源Id
//            }
//            .initBottom {
//                setDimAmount(0F)                //窗口明暗程度，0~1.0，1全不透明，默认为0.32
//                setCancelableOutside(true)      //点击对话框外部是否关闭对话框
//                setMaxHeight(0)                 //设置最大高度
//                setFullExpanded(false)          //是否完全展开，受限于setMaxHeight
//                setPeekHeight(0)                //初始高度，默认最大为屏幕高度的一半，受限于setMaxHeight和setFullExpanded
//            }
//            .initBottomList {
//                setDimAmount(0F)                //窗口明暗程度，0~1.0，1全不透明，默认为0.32
//                setCancelableOutside(true)      //点击对话框外部是否关闭对话框
//                setMaxHeight(0)                 //设置最大高度
//                setFullExpanded(false)          //是否完全展开，受限于setMaxHeight
//                setPeekHeight(0)                //初始高度，默认最大为屏幕高度的一半，受限于setMaxHeight和setFullExpanded
//                setCancelableOutside(true)      //对话框外部是否关闭对话框
//                setItemMinHeight(0)             //列表子布局的最小高度
//                setItemTextStyle()              //设置列表子布局文字的大小、颜色等样式
//
//            }
//            .initBottomGrid {
//                setDimAmount(0F)                //窗口明暗程度，0~1.0，1全不透明，默认为0.32
//                setCancelableOutside(true)      //点击对话框外部是否关闭对话框
//                setSpanCount(3)                 //表格列数
//                setMaxHeight(0)                 //设置最大高度
//                setFullExpanded(false)          //是否完全展开，受限于setMaxHeight
//                setPeekHeight(0)                //初始高度，默认最大为屏幕高度的一半，受限于setMaxHeight和setFullExpanded
//                setItemTextStyle()              //设置列表子布局文字的大小、颜色等样式
//                setItemIconStyle(               //图片的样式
//                    iconSize = 0,               //图片大小，小于0则为wrap_content
//                    iconMaxSize = 0,            //图片最大尺寸，默认为60dp
//                    scaleType = CENTER_CROP     //图片放缩类型，默认为ImageView.ScaleType.CENTER_CROP
//
//                )
//            }
        btnAlert.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("这是一个提示对话框")
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", null)
//                    .setSingleChoiceItems()
                .show()
        }

        btnInfo.setOnClickListener {
            MaterialDialog.alert(this)
                .setMessage(R.string.location_services_msg)
                .show()
        }

        btnMaterial.setOnClickListener {
            MaterialDialog.alert(this)
                .setTitle(R.string.location_services_title)
                .setMessage(R.string.location_services_msg)
                .setPositiveText(R.string.agree)
                .setOnPositiveClickListener {
                    Toast.makeText(mContext, R.string.agree, Toast.LENGTH_LONG).show()
                }
                .setNegativeText(R.string.disagree)
                .setOnNegativeClickListener {
                    Toast.makeText(mContext, R.string.disagree, Toast.LENGTH_LONG).show()
                }
                .show()
        }
        btnAllButton.setOnClickListener {
            MaterialDialog.alert(this)
                .setTitle(R.string.location_services_title)
                .setMessage(R.string.location_services_msg)
                .setDismissible(true)       //点击按钮是否默认关闭对话框，默认为true
                .setPositiveText(R.string.agree)
                .setOnPositiveClickListener {
                    Toast.makeText(mContext, R.string.agree, Toast.LENGTH_LONG).show()
                }
                .setNegativeText(R.string.disagree)
                .setOnNegativeClickListener {
                    Toast.makeText(mContext, R.string.disagree, Toast.LENGTH_LONG).show()
                }
                .setNeutralText(R.string.learn_more)
                .setOnNeutralClickListener {
                    Toast.makeText(mContext, R.string.learn_more, Toast.LENGTH_LONG).show()
                }
                .setOnDismissListener {
                    //对话框消失监听
                }
                .show()
        }
        btnSingle.setOnClickListener {
            MaterialDialog.alert(this)
                .setTitle("请选择一个城市")
                .setSingleChoiceItems(
                    cities,                   //选项列表数组
                    0              //预先选中项
                ) { dialog, checked, preChecked ->
                    Toast.makeText(
                        mContext,
                        "之前选中${cities[preChecked]},当前选中${cities[checked]}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                .setPositiveButton(text = getString(R.string.ok)) {
                    Toast.makeText(mContext, R.string.ok, Toast.LENGTH_LONG).show()
                    it.dismiss()
                }
                .setNegativeButton(text = getString(R.string.cancel)) {
                    Toast.makeText(mContext, R.string.cancel, Toast.LENGTH_LONG).show()
                    it.dismiss()
                }
                .show()
        }
        btnMultiple.setOnClickListener {
            MaterialDialog.alert(this)
                .setTitle("请选择你喜欢的城市")
                .setMultiChoiceItems(
                    cities,             //选项列表数组
                    null    //选中项的下标数组
                ) { position, isChecked, checkedArray, dialog ->
                    Toast.makeText(
                        mContext,
                        "你点击了${cities[position]}，选中：$isChecked",
                        Toast.LENGTH_LONG
                    ).show()
                }
                .setPositiveButton(text = getString(R.string.ok)) {
                    Toast.makeText(mContext, R.string.ok, Toast.LENGTH_LONG).show()
                    it.dismiss()
                }
                .setNegativeButton(text = getString(R.string.cancel)) {
                    Toast.makeText(mContext, R.string.cancel, Toast.LENGTH_LONG).show()
                    it.dismiss()
                }
                .show()
        }
        btnMDCus1.setOnClickListener {
            MaterialDialog.alert(this)
                .setCornerRadius(dp2px(16).toFloat())
                .setTitle("Back to Listings?")
                .setMessage("if you leave the checkout your passenger information will not be saved.")
                .setTitleColor(Color.parseColor("#DE000000"))
                .setMessageColor(Color.parseColor("#99000000"))
                .setPositiveTextColor(Color.parseColor("#5C1349"))
                .setNegativeTextColor(Color.parseColor("#5C1349"))
                .setPositiveText(R.string.ok)
                .setNegativeText(R.string.cancel)
                .show()
        }
        btnBottom.setOnClickListener {
            MaterialDialog.bottom(this)
                .setView(R.layout.layout_custon_bottom) //传入自定义的布局Id
                .setOnViewHandler { holder, dialog ->
                    //处理自定义布局上的控件
                    holder.setOnClickListener(R.id.tvCancel) {

                        dialog.dismiss()
                    }
                }
                .setOnDismissListener {
                    shortToast("对话框关闭")
                }
                .show()
        }
        btnBottomList.setOnClickListener {
            MaterialDialog.bottomList(this)
//                .addItem()                  //往列表中添加一项
                .addItems(cities.toList())  //添加列表集合
                .setOnItemClickListener { position, item, dialog ->
                    //item的点击事件
                    shortToast("你选择了${item.text}")
                }
                .show()
        }
        btnCusListItem.setOnClickListener {
            val list = cities.map { ListItemBean(it, false) }
            MaterialDialog.bottomList(this)
                //传入一个MDAdapter的匿名对象，依次设置自定义的子布局Id和数据
                .setAdapter(object : MDAdapter<ListItemBean>(mContext, R.layout.item_list, list) {
                    override fun onConvert(
                        holder: RecyclerViewHolder,
                        position: Int,
                        item: ListItemBean
                    ) {
                        holder.setText(R.id.tvItem, item.text)
                            .setBackgroundRes(
                                R.id.ivItem,
                                if (item.isSelected) R.drawable.ic_selected else R.drawable.ic_unselected
                            )
                    }
                })
                //子布局中的控件点击事件
                .setOnItemChildClickListener<ListItemBean>(
                    R.id.llRoot,
                    R.id.tvItem
                ) { adapter, position, item, view, dialog ->
                    when (view.id) {
                        R.id.llRoot -> {
                            shortToast("你选择了${item.text}")
                            item.isSelected = !item.isSelected
                            adapter.notifyItemChanged(position)
                        }
                        R.id.tvItem -> shortToast("${item.text}")
                    }
                }
                .show()
        }
        btnBottomGrid.setOnClickListener {
            MaterialDialog.bottomGrid(this)
                .setSpanCount(3)
                .addItem("item1", R.mipmap.ic_launcher_round)
                .addItem("item2", R.mipmap.ic_launcher_round)
                .addItem("item3", R.mipmap.ic_launcher_round)
                .addItem("item4", R.mipmap.ic_launcher_round)
                .addItem("item5", R.mipmap.ic_launcher_round)
                .addItem("item6", R.mipmap.ic_launcher_round)
                .addItem("item7", R.mipmap.ic_launcher_round)
                .setOnItemClickListener { position, item, dialog ->
                    shortToast("你点击了${item.text}")
                }
                .show()
        }
    }
}
