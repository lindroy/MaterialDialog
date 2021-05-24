package com.lindroy.materialdialog.dialog

import android.content.Context
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatCheckBox
import android.view.*
import android.widget.RadioButton
import androidx.fragment.app.FragmentManager
import com.lindroid.anddialog.R
import com.lindroy.materialdialog.adapter.MDAdapter
import com.lindroy.materialdialog.params.AlertBuilder
import com.lindroy.materialdialog.params.CheckItemParams
import com.lindroy.materialdialog.util.*
import com.lindroy.materialdialog.util.getResColor
import com.lindroy.materialdialog.util.isGone
import com.lindroy.materialdialog.util.screenHeight
import com.lindroy.materialdialog.util.setGone
import com.lindroy.materialdialog.viewholder.RecyclerViewHolder
import kotlinx.android.synthetic.main.dialog_md_alert.*
import kotlinx.android.synthetic.main.layout_md_button_panel.*
import kotlinx.android.synthetic.main.layout_md_list_panel.*
import kotlinx.android.synthetic.main.layout_md_title_message_panel.*

/**
 * @author Lin
 * @date 2019/8/13
 * @function
 * @Description
 */
private const val KEY_MATERIAL_PARAMS = "material_params"

internal class AlertDialog : androidx.fragment.app.DialogFragment() {

    private lateinit var mContext: Context
    private lateinit var builder: AlertBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.apply {
            builder = getParcelable(KEY_MATERIAL_PARAMS) ?: AlertBuilder()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.dialog_md_alert, container, false)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView.background = initBackgroundDrawable()
        setupTitle()
        setupMessage()
        setupButtons()
        setupList()
    }

    /**
     * 设置标题
     */
    private fun setupTitle() {
        builder.titleParams.also {
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
        builder.msgParams.also {
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
        /*fun Button.setRippleDrawable(){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val rd =  RippleDrawable(
                    ColorStateList.valueOf(getResColor(R.color.md_dialog_button_text_color)),
                    //背景不能为透明
                    getResDrawable(R.drawable.shape_button_click_ripple),
                    null
                    )
                background = rd
            }
        }*/
        //Positive Button
        fun dismissDialog() {
            if (builder.dismissible) {
                dismiss()
            }
        }
        builder.posButtonParams.also {
            btnPos.apply {
                visibility = when (it.text.isNotEmpty()) {
                    true -> {
                        text = it.text
                        setTextColor(it.textColor)
                        textSize = it.textSize
                        setOnClickListener { _ ->
                            it.clickListener?.onClick(dialog)
                            dismissDialog()
                        }
                        View.VISIBLE
                    }
                    false -> View.GONE
                }
            }
        }
        //Negative Button
        builder.negButtonParams.also {
            btnNeg.apply {
                visibility = when (it.text.isNotEmpty()) {
                    true -> {
                        text = it.text
                        setTextColor(it.textColor)
                        textSize = it.textSize
                        setOnClickListener { _ ->
                            it.clickListener?.onClick(dialog)
                            dismissDialog()
                        }
                        View.VISIBLE
                    }
                    false -> View.GONE
                }
            }
        }
        //Neutral Button
        builder.neuButtonParams.also {
            btnNeu.apply {
                visibility = when (it.text.isNotEmpty()) {
                    true -> {
                        text = it.text
                        setTextColor(it.textColor)
                        textSize = it.textSize
                        setOnClickListener { _ ->
                            it.clickListener?.onClick(dialog)
                            dismissDialog()
                        }
                        View.VISIBLE
                    }
                    false -> View.GONE
                }
            }
        }
        if (btnPos.isGone && btnNeg.isGone && btnNeu.isGone) {
            buttonPanel.setGone()
        }
    }

    /**
     * 设置列表
     */
    private fun setupList() {
        if (builder.itemList.isNotEmpty()) {
            spaceButton.setGone()
            viewStubList.setVisible()
            rvChoices.apply {
                layoutManager =
                    androidx.recyclerview.widget.LinearLayoutManager(mContext)
                adapter = when (builder.isSingleChoice) {
                    true -> setSingleChoiceAdapter()
                    false -> setMultiChoiceAdapter()
                }
                addOnScrollListener(object :
                    androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
                    override fun onScrolled(
                        recyclerView: androidx.recyclerview.widget.RecyclerView,
                        dx: Int,
                        dy: Int
                    ) {
                        super.onScrolled(recyclerView, dx, dy)
                        val layoutManager = recyclerView.layoutManager
                        if (layoutManager is androidx.recyclerview.widget.LinearLayoutManager) {
                            val lastItemIndex =
                                layoutManager.findLastCompletelyVisibleItemPosition()
                            val firstItemIndex =
                                layoutManager.findFirstCompletelyVisibleItemPosition()
                            if (firstItemIndex == 0) {
                                viewDividerTop.setGone()
                            } else {
                                viewDividerTop.setVisible()
                            }

                            if (lastItemIndex + 1 == layoutManager.itemCount) {
                                viewDividerBottom.setGone()
                            } else {
                                viewDividerBottom.setVisible()
                            }
                        }
                    }
                })
            }
        }
    }

    private fun setSingleChoiceAdapter() = object : MDAdapter<CheckItemParams>(
        mContext,
        R.layout.item_md_single_choice_list,
        builder.itemList
    ) {
        override fun onConvert(
            holder: RecyclerViewHolder,
            position: Int,
            item: CheckItemParams
        ) {
            holder.getView<RadioButton>(R.id.rbSingle).apply {
                isChecked = item.isChecked
                //设置RadioButton颜色
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val colorStateList = ColorStateList(
                        arrayOf(
                            intArrayOf(-android.R.attr.state_checked),
                            intArrayOf(android.R.attr.state_checked)
                        ),
                        intArrayOf(
                            getResColor(R.color.md_multi_checkbox_unchecked),
                            getResColor(R.color.md_single_choice_radio_button_color)
                        )
                    )
                    buttonTintList = colorStateList
                }
            }
            holder.getTextView(R.id.tvSingle).apply {
                text = item.text
                textSize = item.textSize
                setTextColor(item.textColor)
            }
            holder.setOnClickListener(R.id.llItemSingle) {
                if (item.isChecked) {
                    return@setOnClickListener
                }
                var oldCheckedIndex = 0
                for ((i, checkItem) in builder.itemList.withIndex()) {
                    if (checkItem.isChecked) {
                        oldCheckedIndex = i
                        checkItem.isChecked = false
                        break
                    }
                }
                item.isChecked = true
                notifyItemChanged(oldCheckedIndex)
                notifyItemChanged(position)
                builder.singleChoiceListener?.onChecked(dialog, position, oldCheckedIndex)
            }
        }
    }

    private fun setMultiChoiceAdapter() = object : MDAdapter<CheckItemParams>(
        mContext,
        R.layout.item_md_multiple_choice_list,
        builder.itemList
    ) {
        override fun onConvert(
            holder: RecyclerViewHolder,
            position: Int,
            item: CheckItemParams
        ) {
            holder.getTextView(R.id.tvMultiple).apply {
                text = item.text
                textSize = item.textSize
                setTextColor(item.textColor)
            }
            holder.getView<AppCompatCheckBox>(R.id.cbMultiple).apply {
                isChecked = item.isChecked
                //设置选中颜色
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val colorStateList = ColorStateList(
                        arrayOf(
                            intArrayOf(-android.R.attr.state_checked),
                            intArrayOf(android.R.attr.state_checked)
                        ),
                        intArrayOf(
                            Color.DKGRAY, //未选中时的边框颜色
                            getResColor(R.color.md_single_choice_radio_button_color)
                        )
                    )
                    buttonTintList = colorStateList
                }
            }
            holder.setOnClickListener(R.id.llMultiple) {
                item.isChecked = !item.isChecked
                val checkedList = mutableListOf<Int>()
                builder.itemList.forEachIndexed { index, item ->
                    if (item.isChecked) {
                        checkedList.add(index)
                    }
                }
                builder.multiChoiceListener?.onChecked(
                    position,
                    item.isChecked,
                    if (checkedList.isEmpty()) null else checkedList.toIntArray(),
                    dialog
                )
                notifyItemChanged(position)
            }
        }

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context!!
    }

    override fun onStart() {
        super.onStart()
        dialog.setOnKeyListener { dialog, keyCode, event ->
            builder.keyListener?.onKey(dialog, keyCode, event) ?: false
        }
        dialog.window?.apply {
            val params = attributes
            params.gravity = Gravity.CENTER
            //去除白色的背景
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            //设置窗体动画
            setWindowAnimations(builder.animStyle)
            params.dimAmount = builder.dimAmount
//            val maxHeight = 0.65 * screenHeight
            params.width = builder.finalWidth
            attributes = params
            //Todo(监听布局绘制完成后再改变对话框宽高会影响动画)
            /*decorView.viewTreeObserver.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    if (decorView.height > maxHeight) {
                        params.height = maxHeight.toInt()
                    }
//                    attributes = params
                    decorView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })*/
//            attributes = params
        }
    }

    override fun onDismiss(dialog: DialogInterface?) {
        builder.dismissListener?.onDismiss(dialog)
        super.onDismiss(dialog)
    }

    /**
     * 设置对话框背景
     */
    private fun initBackgroundDrawable(): ShapeDrawable {
        val radius = builder.radius
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
            paint.color = builder.backgroundColor
            paint.style = Paint.Style.FILL
            paint.alpha = (255 * builder.backgroundAlpha).toInt()
            this
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_MATERIAL_PARAMS, builder)
    }

    companion object {
        fun showDialog(
            fm: FragmentManager,
            tag: String,
            builder: AlertBuilder
        ) =
            AlertDialog().apply {
                this.builder = builder
                show(fm, tag)
            }
    }


}