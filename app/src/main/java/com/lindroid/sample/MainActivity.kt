package com.lindroid.sample

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.lindroy.anddialog.MaterialDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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

        btnMaterial.setOnClickListener {
            MaterialDialog.build(this)
                    .setTitle("提示")
                    .setMessage("这是一个提示对话框")
                    .setPositiveText("Ok")
                    .setNegativeText("Cancel")
                    .show()
        }
    }
}
