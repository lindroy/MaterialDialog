package com.lindroy.anddialog.dialog

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.View
import com.lindroy.anddialog.base.BaseBottomDialog
import com.lindroy.anddialog.params.BaseBottomParams
import com.lindroy.anddialog.params.BottomParams
import com.lindroy.anddialog.viewholder.ViewHolder
import kotlin.properties.Delegates


/**
 * @author Lin
 * @date 2019/8/20
 * @function 底部对话框
 * @Description
 */
class BottomController : BaseBottomDialog() {

    private  var mParams: BottomParams by Delegates.observable(BottomParams()){property, oldValue, newValue ->
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
        internal fun showDialog(params: BottomParams, fm: FragmentManager, tag: String) =
            BottomController().apply {
                mParams = params
                show(fm, tag)
            }
    }

}