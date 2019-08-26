package com.lindroy.anddialog.dialog

import android.content.Context
import android.content.res.ColorStateList
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
import android.support.v7.widget.AppCompatCheckBox
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.RadioButton
import com.lindroid.anddialog.R
import com.lindroy.anddialog.MaterialDialog
import com.lindroy.anddialog.adapter.MDRecyclerViewAdapter
import com.lindroy.anddialog.params.AlertParams
import com.lindroy.anddialog.params.CheckItemParams
import com.lindroy.anddialog.viewholder.RecyclerViewHolder
import com.lindroy.iosdialog.util.*
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

class MaterialController : DialogFragment() {

    private lateinit var mContext: Context
    private lateinit var mdParams: AlertParams

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.apply {
            mdParams = getParcelable(KEY_MATERIAL_PARAMS) ?: MaterialDialog.buildParams
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
        mdParams.titleParams.also {
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
        mdParams.msgParams.also {
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
            if (mdParams.dismissible) {
                dismiss()
            }
        }
        mdParams.posButtonParams.also {
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
        mdParams.negButtonParams.also {
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
        mdParams.neuButtonParams.also {
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
        if (mdParams.itemList.isNotEmpty()) {
            spaceButton.setGone()
            viewStubList.setVisible()
            rvChoices.apply {
                layoutManager = LinearLayoutManager(mContext)
                adapter = when (mdParams.isSingleChoice) {
                    true -> setSingleChoiceAdapter()
                    false -> setMultiChoiceAdapter()
                }
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        val layoutManager = recyclerView.layoutManager
                        if (layoutManager is LinearLayoutManager) {
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

    private fun setSingleChoiceAdapter() = object : MDRecyclerViewAdapter<CheckItemParams>(
        mContext,
        R.layout.item_md_single_choice_list,
        mdParams.itemList
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
                            Color.DKGRAY,
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
                for ((i, checkItem) in mdParams.itemList.withIndex()) {
                    if (checkItem.isChecked) {
                        oldCheckedIndex = i
                        checkItem.isChecked = false
                        break
                    }
                }
                item.isChecked = true
                notifyItemChanged(oldCheckedIndex)
                notifyItemChanged(position)
//                notifyDataSetChanged()
                mdParams.singleChoiceListener?.onChecked(dialog, position, oldCheckedIndex)
            }
        }
    }

    private fun setMultiChoiceAdapter() = object : MDRecyclerViewAdapter<CheckItemParams>(
        mContext,
        R.layout.item_md_multiple_choice_list,
        mdParams.itemList
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
                            Color.DKGRAY,
                            getResColor(R.color.md_single_choice_radio_button_color)
                        )
                    )
                    buttonTintList = colorStateList
                }
            }
            holder.setOnClickListener(R.id.llMultiple) {
                item.isChecked = !item.isChecked
                val checkedList = mutableListOf<Int>()
                mdParams.itemList.forEachIndexed { index, item ->
                    if (item.isChecked) {
                        checkedList.add(index)
                    }
                }
                mdParams.multiChoiceListener?.onChecked(
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
        dialog.window?.apply {
            val params = attributes
            params.gravity = Gravity.CENTER
            //去除白色的背景
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            //设置窗体动画
            setWindowAnimations(android.R.style.Animation_Dialog)
            params.width = mdParams.finalWidth
            params.dimAmount = mdParams.dimAmount
            val maxHeight = 0.65 * screenHeight
            decorView.viewTreeObserver.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    if (decorView.height > maxHeight) {
                        params.height = maxHeight.toInt()
                    }
                    attributes = params
                    decorView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })
        }
    }

    /**
     * 设置对话框背景
     */
    private fun initBackgroundDrawable(): ShapeDrawable {
        val radius = mdParams.radius
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
            paint.color = mdParams.backgroundColor
            paint.style = Paint.Style.FILL
            paint.alpha = (255 * mdParams.backgroundAlpha).toInt()
            this
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_MATERIAL_PARAMS, mdParams)
    }

    companion object {
        fun showDialog(fm: FragmentManager, tag: String, params: AlertParams) =
            MaterialController().apply {
                this.mdParams = params
                show(fm, tag)
            }
    }


}