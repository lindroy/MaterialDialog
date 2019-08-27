package com.lindroid.sample.bean;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lindroy.anddialog.MaterialDialog;
import com.lindroy.anddialog.listener.OnSheetItemClickListener;
import com.lindroy.anddialog.params.MDListItem;

import org.jetbrains.annotations.NotNull;


/**
 * @author Lin
 * @date 2019/8/27
 * @function
 * @Description
 */
public class JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MaterialDialog.bottomList(this)
                .setOnItemClickListener(new OnSheetItemClickListener<MDListItem>() {
                    @Override
                    public void onClick(int position, MDListItem item, @NotNull DialogInterface dialog) {

                    }
                })
                .show();

    }
}
