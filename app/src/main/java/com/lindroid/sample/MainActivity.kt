package com.lindroid.sample

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.lindroid.androidutilskt.app.AndUtil
import com.lindroid.androidutilskt.extension.dp2px
import com.lindroid.androidutilskt.extension.logcat.d
import com.lindroid.androidutilskt.extension.shortToast
import com.lindroy.anddialog.MaterialDialog
import com.lindroy.anddialog.listener.OnSingleChoiceListener
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
                .setPositiveText(R.string.agree)
                .setNegativeText(R.string.disagree)
                .setNeutralText(R.string.learn_more)
                .show()
        }
        btnSingle.setOnClickListener {
            MaterialDialog.alert(this)
                .setTitle("请选择一个城市")
                .setSingleChoiceItems(cities, listener = object : OnSingleChoiceListener() {
                    override fun onChecked(dialog: DialogInterface, checked: Int, oldChecked: Int) {
                        Toast.makeText(mContext, "你选择了${cities[checked]}", Toast.LENGTH_LONG).show()
                    }
                })
                .setPositiveText(R.string.ok)
                .setNegativeText(R.string.cancel)
                .show()
        }
        btnMultiple.setOnClickListener {
            MaterialDialog.alert(this)
                .setTitle("请选择你喜欢的城市")
                .setMultiChoiceItems(cities, null) { position, isChecked, checkedArray, dialog ->
                    Toast.makeText(
                        mContext,
                        "你点击了${cities[position]}，选中：$isChecked",
                        Toast.LENGTH_LONG
                    ).show()
//                    "当前选中：${Arrays.toString(checkedArray)}".d()
                    checkedArray.d()
                }
                .setPositiveText(R.string.ok)
                .setNegativeText(R.string.cancel)
                .show()
        }
        btnMDCus1.setOnClickListener {
            MaterialDialog.alert(this)
                .setCornerRadius(dp2px(16).toFloat())
                .setTitle("Back to Listings?")
                .setMessage("if you leave the checkout your passenger information will not be saved.")
                .setTitleColor(Color.parseColor("#DE000000"))
                .setMessageColor(Color.parseColor("#99000000"))
                .setPosTextColor(Color.parseColor("#5C1349"))
                .setNegTextColor(Color.parseColor("#5C1349"))
                .setPositiveText(R.string.ok)
                .setNegativeText(R.string.cancel)
                .show()
        }

        btnBottom.setOnClickListener {
            MaterialDialog.bottom(this)
                .setView(R.layout.layout_custon_bottom)
                .setOnViewHandler { holder, dialog ->
                    holder.setOnClickListener(R.id.tvCancel) {
                        shortToast("关闭底部对话框")
                        dialog.dismiss()
                    }
                }
                .show()
        }
        btnBottomList.setOnClickListener {
            MaterialDialog.bottomList(this)
                .addItems(cities.toList())
                .setOnItemClickListener { position, item, dialog ->
                    shortToast("你选择了${item.text}")
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
