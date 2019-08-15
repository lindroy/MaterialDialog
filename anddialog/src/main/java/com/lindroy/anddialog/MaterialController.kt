package com.lindroy.anddialog

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lindroid.anddialog.R
import com.lindroy.iosdialog.util.getResColor
import com.lindroy.iosdialog.util.screenWidth
import com.lindroy.iosdialog.util.setBold
import kotlinx.android.synthetic.main.dialog_material.*
import kotlinx.android.synthetic.main.layout_md_button_panel.*
import kotlinx.android.synthetic.main.layout_md_title_message_panel.*

/**
 * @author Lin
 * @date 2019/8/13
 * @function
 * @Description
 */
class MaterialController : DialogFragment() {

    private lateinit var mContext: Context
    private lateinit var params: MaterialDialog.Builder
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.dialog_material, container, false)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView.background = initBackgroundDrawable()
        setupTitle()
        setupMessage()
        setupButtons()
    }

    /**
     * 设置标题
     */
    private fun setupTitle() {
        params.titleParams.also {
            tvTitle.apply {
                visibility = when (it.text.isNotEmpty()) {
                    true -> {
                        text = it.text
                        setBold(it.isBold)
                        setTextColor(getResColor(R.color.md_dialog_title_text_color))
                        textSize = it.textSize
                        View.VISIBLE
                    }
                    false -> View.GONE
                }
            }
        }

    }

    /**
     * 设置信息文字
     */
    private fun setupMessage() {
        params.msgParams.also {
            tvMessage.apply {
                visibility = when (it.text.isNotEmpty()) {
                    true -> {
                        text = it.text
                        setBold(it.isBold)
                        setTextColor(it.textColor)
                        textSize = it.textSize
                        View.VISIBLE
                    }
                    false -> View.GONE
                }
            }
        }

    }

    /**
     * 设置按钮
     */
    private fun setupButtons() {
        //Positive Button
        params.posButtonParams.also {
            btnPos.apply {
                visibility = when (it.text.isNotEmpty()) {
                    true -> {
                        text = it.text
                        setTextColor(it.textColor)
                        textSize = it.textSize
                        View.VISIBLE
                    }
                    false -> View.GONE
                }
            }
        }
        //Negative Button
        params.negButtonParams.also {
            btnNeg.apply {
                visibility = when (it.text.isNotEmpty()) {
                    true -> {
                        text = it.text
                        setTextColor(it.textColor)
                        textSize = it.textSize
                        View.VISIBLE
                    }
                    false -> View.GONE
                }
            }
        }
        //Neutral Button
        params.neuButtonParams.also {
            btnNeu.apply {
                visibility = when (it.text.isNotEmpty()) {
                    true -> {
                        text = it.text
                        setTextColor(it.textColor)
                        textSize = it.textSize
                        View.VISIBLE
                    }
                    false -> View.GONE
                }
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context!!
    }


    override fun onStart() {
        super.onStart()
        dialog.window?.apply {
            val params = attributes
            params.gravity = Gravity.CENTER
            //去除白色的背景
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            //设置窗体动画
            setWindowAnimations(android.R.style.Animation_Dialog)
            params.width = (screenWidth * 0.85).toInt()
            params.dimAmount = 0.32F
            /*if (baseParams.widthPx > 0) {
                params.width = baseParams.widthPx
            } else if (baseParams.widthScale > 0) {
                params.width = (screenWidth * baseParams.widthScale).toInt()
            }*/
            attributes = params
        }
    }

    /**
     * 设置对话框背景
     */
    private fun initBackgroundDrawable(): ShapeDrawable {
        val radius = params.radius
        val roundRectShape = RoundRectShape(
                floatArrayOf(
                        radius,
                        radius,
                        radius,
                        radius,
                        radius,
                        radius,
                        radius,
                        radius
                ), null, null
        )
        return with(ShapeDrawable(roundRectShape)) {
            paint.color = params.backgroundColor
            paint.style = Paint.Style.FILL
            paint.alpha = (255 * params.backgroundAlpha).toInt()
            this
        }
    }

    companion object {
        fun showDialog(fm: FragmentManager, tag: String, params: MaterialDialog.Builder) =
                MaterialController().apply {
                    this.params = params
                    show(fm, tag)
                }
    }


}