package com.lindroid.sample

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.lindroid.androidutilskt.extension.dp2px
import com.lindroy.anddialog.MaterialDialog
import com.lindroy.anddialog.listener.OnSingleChoiceListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mContext: Context
    private lateinit var cities: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
            MaterialDialog.build(this)
                .setMessage(R.string.location_services_msg)
                .show()
        }

        btnMaterial.setOnClickListener {
            MaterialDialog.build(this)
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
            MaterialDialog.build(this)
                .setTitle(R.string.location_services_title)
                .setMessage(R.string.location_services_msg)
                .setPositiveText(R.string.agree)
                .setNegativeText(R.string.disagree)
                .setNeutralText(R.string.learn_more)
                .show()
        }
        btnSingle.setOnClickListener {
            MaterialDialog.build(this)
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
            MaterialDialog.build(this)
                .setTitle("请选择你喜欢的城市")
                .setMultiChoiceItems(cities, null) { dialog, position, isChecked ->
                    Toast.makeText(
                        mContext,
                        "你点击了${cities[position]}，选中：$isChecked",
                        Toast.LENGTH_LONG
                    ).show()
                }
                .setPositiveText(R.string.ok)
                .setNegativeText(R.string.cancel)
                .show()
        }
        btnMDCus1.setOnClickListener {
            MaterialDialog.build(this)
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
    }
}
