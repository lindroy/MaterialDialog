package com.lindroid.sample.bean;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lindroy.anddialog.MaterialDialog;
import com.lindroy.anddialog.adapter.MDAdapter;
import com.lindroy.anddialog.listener.OnItemChildClickListener;
import com.lindroy.anddialog.listener.OnSheetItemClickListener;
import com.lindroy.anddialog.params.ComAlertParams;
import com.lindroy.anddialog.params.ComBottomParams;
import com.lindroy.anddialog.params.MDGridItem;
import com.lindroy.anddialog.params.MDListItem;

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
        MaterialDialog.bottomList(this)
                .setOnItemClickListener(new OnSheetItemClickListener<MDListItem>() {
                    @Override
                    public void onClick(int position, MDListItem item, @NotNull DialogInterface dialog) {

                    }
                })
                .show();
        MaterialDialog.bottomGrid(this)
                .setOnItemChildClickListener(new OnItemChildClickListener<MDGridItem>() {
                    @Override
                    public void onClick(@NotNull MDAdapter<?> adapter, int position, MDGridItem item, @NotNull View view, @NotNull DialogInterface dialog) {
                    }

                })
                .show();

    }
}
