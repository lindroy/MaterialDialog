package com.lindroid.sample

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.lindroy.anddialog.MaterialDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MaterialDialog.init(this.application)

        btnAlert.setOnClickListener {
            AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("这是一个提示对话框")
                    .setPositiveButton("OK", null)
                    .setNegativeButton("Cancel", null)
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
                        Toast.makeText(mContext,R.string.agree,Toast.LENGTH_LONG).show()
                    }
                    .setNegativeText(R.string.disagree)
                    .setOnNegativeClickListener {
                        Toast.makeText(mContext,R.string.disagree,Toast.LENGTH_LONG).show()
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
    }
}
