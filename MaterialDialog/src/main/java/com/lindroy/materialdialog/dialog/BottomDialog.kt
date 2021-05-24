package com.lindroy.materialdialog.dialog

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import android.view.View
import com.lindroy.materialdialog.base.BaseBottomDialog
import com.lindroy.materialdialog.params.BaseBottomParams
import com.lindroy.materialdialog.params.BottomBuilder
import com.lindroy.materialdialog.viewholder.ViewHolder
import kotlin.properties.Delegates


/**
 * @author Lin
 * @date 2019/8/20
 * @function 底部对话框
 * @Description
 */
internal class BottomDialog : BaseBottomDialog() {

    private  var mParams: BottomBuilder by Delegates.observable(BottomBuilder()){ property, oldValue, newValue ->
        bottomParams = mParams
    }

    override lateinit var bottomParams: BaseBottomParams<*>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (mParams.isBottom) {
            mParams.viewHandler?.onConvert(ViewHolder(view), dialog)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context!!
    }

    companion object {
        internal fun showDialog(builder: BottomBuilder, fm: androidx.fragment.app.FragmentManager, tag: String) =
            BottomDialog().apply {
                mParams = builder
                show(fm, tag)
            }
    }

}