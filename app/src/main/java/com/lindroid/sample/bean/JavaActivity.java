package com.lindroid.sample.bean;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lindroy.materialdialog.MaterialDialog;
import com.lindroy.materialdialog.adapter.MDAdapter;
import com.lindroy.materialdialog.listener.OnGridItemClickListener;
import com.lindroy.materialdialog.listener.OnItemChildClickListener;
import com.lindroy.materialdialog.params.AlertBuilder;
import com.lindroy.materialdialog.params.ComAlertParams;
import com.lindroy.materialdialog.params.ComBottomParams;
import com.lindroy.materialdialog.params.MDGridItem;

import org.jetbrains.annotations.NotNull;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;


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
        MaterialDialog.init(this.getApplication())
                .initAlert(new Function1<ComAlertParams<?>, Unit>() {
                    @Override
                    public Unit invoke(ComAlertParams<?> comAlertParams) {
                        return null;
                    }
                })
                .initBottom(new Function1<ComBottomParams<?>, Unit>() {
                    @Override
                    public Unit invoke(ComBottomParams<?> comBottomParams) {
                        return null;
                    }
                });

        MaterialDialog.bottomGrid(this)
                .setOnItemChildClickListener(new OnItemChildClickListener<MDGridItem>() {
                    @Override
                    public void onClick(@NotNull MDAdapter<?> adapter, int position, MDGridItem item, @NotNull View view, @NotNull DialogInterface dialog) {
                    }

                })
                .show();
        MaterialDialog.bottomGrid(this)
                .setOnItemClickListener(new OnGridItemClickListener() {
                    @Override
                    public void onClick(int position, MDGridItem item, @NotNull DialogInterface dialog) {

                    }
                })
                .show();

        AlertBuilder builder = MaterialDialog.alert(this);
    }
}
