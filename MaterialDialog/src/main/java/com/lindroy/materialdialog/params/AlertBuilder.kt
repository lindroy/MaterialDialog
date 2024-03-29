package com.lindroy.materialdialog.params

import android.annotation.SuppressLint
import android.content.DialogInterface
import androidx.annotation.*
import android.view.KeyEvent
import com.lindroy.materialdialog.util.getResColor
import com.lindroy.materialdialog.util.getResPx
import com.lindroy.materialdialog.util.getResString
import com.lindroy.materialdialog.util.px2sp
import com.lindroy.materialdialog.MaterialDialog
import com.lindroy.materialdialog.constants.MD_MULTI_CHOICE
import com.lindroy.materialdialog.constants.MD_SINGLE_CHOICE
import com.lindroy.materialdialog.dialog.AlertDialog
import com.lindroy.materialdialog.listener.*

/**
 * @author Lin
 * @date 2019/8/22
 * @function Alert类对话框参数
 * @Description
 */
@SuppressLint("ParcelCreator")
open class AlertBuilder(
    internal val itemList: MutableList<CheckItemParams> = mutableListOf(),
    internal var dismissListener: OnDismissListener? = null,
    internal var keyListener: OnKeyListener? = null,
    internal var singleChoiceListener: OnSingleChoiceListener? = null,
    internal var multiChoiceListener: OnMultiChoiceListener? = null
) : ComAlertParams<AlertBuilder>() {

    init {
        MaterialDialog.alertP.also {
            tag = it.tag
            animStyle = it.animStyle
            dimAmount = it.dimAmount
            backgroundAlpha = it.backgroundAlpha
            radius = it.radius
        }
    }

    /**
     * 点击对话框外部关闭对话框
     */
    fun setCancelableOutside(isCancelable: Boolean) = this.apply {
        cancelableOutside = isCancelable
    }

    /**
     * 点击对话框上的按钮是否可以关闭对话框，默认为true
     */
    fun setDismissible(dismissible: Boolean) = this.apply { this.dismissible = dismissible }

    /**
     * 设置对话框标题
     * @param title:标题文字
     */
    fun setTitle(title: String) = this.apply { titleParams.text = title }

    /**
     * @see setTitle(String)
     * @param stringId:标题文字Id
     */
    fun setTitle(@StringRes stringId: Int) = this.apply { setTitle(getResString(stringId)) }

    /**
     * 设置对话框上的信息文字
     * @param msg:信息文字
     */
    fun setMessage(msg: String) = this.apply { msgParams.text = msg }

    /**
     * @see setMessage(String)
     * @param stringId:信息文字Id
     */
    fun setMessage(@StringRes stringId: Int) = setMessage(getResString(stringId))

    /**
     * 设置Neutral按钮（即最左侧的按钮）文字
     */
    fun setNeutralText(text: String) = this.apply { this.neuButtonParams.text = text }

    /**
     * @see setNeutralText(String)
     */
    fun setNeutralText(@StringRes stringId: Int) = setNeutralText(getResString(stringId))

    /**
     * 是否显示Negative按钮，默认为true，显示
     */
    fun setNegButtonVisible(isVisible: Boolean) =
        this.apply { this.negButtonParams.isVisible = isVisible }

    /**
     * Positive按钮的点击监听
     * 用于屏幕旋转保存状态和Java调用
     */
    fun setOnPositiveClickListener(listener: OnDialogClickListener) =
        this.apply { this.posButtonParams.clickListener = listener }

    /**
     * Positive按钮的点击监听
     */
    fun setOnPositiveClickListener(listener: (dialog: DialogInterface) -> Unit) =
        setOnPositiveClickListener(object : OnDialogClickListener() {
            override fun onClick(dialog: DialogInterface) {
                listener.invoke(dialog)
            }
        })

    /**
     * Negative按钮的点击监听
     * 用于屏幕旋转保存状态和Java调用
     */
    fun setOnNegativeClickListener(listener: OnDialogClickListener) =
        this.apply { negButtonParams.clickListener = listener }

    /**
     * Negative按钮的点击监听
     */
    fun setOnNegativeClickListener(listener: (dialog: DialogInterface) -> Unit) =
        setOnNegativeClickListener(object : OnDialogClickListener() {
            override fun onClick(dialog: DialogInterface) {
                listener.invoke(dialog)
            }
        })

    /**
     * Neutral按钮的点击监听
     * 用于屏幕旋转保存状态和Java调用
     */
    fun setOnNeutralClickListener(listener: OnDialogClickListener) =
        this.apply { neuButtonParams.clickListener = listener }

    /**
     * Neutral按钮的点击监听
     */
    fun setOnNeutralClickListener(listener: (dialog: DialogInterface) -> Unit) =
        setOnNeutralClickListener(object : OnDialogClickListener() {
            override fun onClick(dialog: DialogInterface) {
                listener.invoke(dialog)
            }
        })

    /**
     * 对话框消失监听
     * 用于屏幕旋转保存状态和Java调用
     */
    fun setOnDismissListener(listener: OnDismissListener) =
        this.apply { dismissListener = listener }

    /**
     * 对话框消失监听
     */
    fun setOnDismissListener(listener: (dialog: DialogInterface?) -> Unit) =
        setOnDismissListener(object : OnDismissListener() {
            override fun onDismiss(dialog: DialogInterface?) {
                listener.invoke(dialog)
            }
        })

    /**
     * 设置Positive按钮信息和点击事件
     */
    @JvmOverloads
    fun setPositiveButton(
        text: String = posButtonParams.text,
        @ColorInt textColor: Int = posButtonParams.textColor,
        textSize: Float = posButtonParams.textSize,
        listener: OnDialogClickListener? = null
    ) = this.apply {
        posButtonParams.also {
            it.text = text
            it.textColor = textColor
            it.textSize = textSize
            it.clickListener = listener
        }
    }

    /**
     * @see setPositiveButton
     */
    fun setPositiveButton(
        text: String = posButtonParams.text,
        @ColorInt textColor: Int = posButtonParams.textColor,
        textSize: Float = posButtonParams.textSize,
        listener: ((dialog: DialogInterface) -> Unit)? = null
    ) = setPositiveButton(text, textColor, textSize, object : OnDialogClickListener() {
        override fun onClick(dialog: DialogInterface) {
            listener?.invoke(dialog)
        }
    })

    /**
     * 设置Negative按钮信息和点击事件
     */
    @JvmOverloads
    fun setNegativeButton(
        text: String = negButtonParams.text,
        @ColorInt textColor: Int = negButtonParams.textColor,
        textSize: Float = negButtonParams.textSize,
        listener: OnDialogClickListener? = null
    ) = this.apply {
        negButtonParams.also {
            it.text = text
            it.textColor = textColor
            it.textSize = textSize
            it.clickListener = listener
        }
    }

    /**
     * @see setNegativeButton
     */
    fun setNegativeButton(
        text: String = negButtonParams.text,
        @ColorInt textColor: Int = negButtonParams.textColor,
        textSize: Float = negButtonParams.textSize,
        listener: ((dialog: DialogInterface) -> Unit)? = null
    ) = setNegativeButton(text, textColor, textSize, object : OnDialogClickListener() {
        override fun onClick(dialog: DialogInterface) {
            listener?.invoke(dialog)
        }
    })

    /**
     * 设置Neutral按钮
     */
    fun setNeutralButton(
        text: String = neuButtonParams.text,
        @ColorInt textColor: Int = neuButtonParams.textColor,
        textSize: Float = neuButtonParams.textSize,
        listener: OnDialogClickListener? = null
    ) = this.apply {
        neuButtonParams.also {
            it.text = text
            it.textColor = textColor
            it.textSize = textSize
            it.clickListener = listener
        }
    }

    /**
     * @see setNeutralButton
     */
    fun setNeutralButton(
        text: String = neuButtonParams.text,
        @ColorInt textColor: Int = neuButtonParams.textColor,
        textSize: Float = neuButtonParams.textSize,
        listener: ((dialog: DialogInterface) -> Unit)? = null
    ) = setNeutralButton(text, textColor, textSize, object : OnDialogClickListener() {
        override fun onClick(dialog: DialogInterface) {
            listener?.invoke(dialog)
        }
    })

    /**
     * 设置单选列表
     * @param items:选项数组
     * @param checkedItem:默认选中的item，小于0时表示没有默认选中
     * @param listener:点击监听
     */
    fun setSingleChoiceItems(
        items: Array<String>,
        checkedItem: Int = -1,
        listener: OnSingleChoiceListener
    ) = this.apply {
        type = MD_SINGLE_CHOICE
        itemList.addAll(items.map { CheckItemParams(text = it) })
        if (checkedItem >= 0) {
            itemList[checkedItem].isChecked = true
        }
        this.singleChoiceListener = listener
    }

    /**
     * @see setSingleChoiceItems
     */
    fun setSingleChoiceItems(
        items: Array<String>, checkedItem: Int = -1,
        listener: (dialog: DialogInterface, checked: Int, preChecked: Int) -> Unit
    ) =
        setSingleChoiceItems(items, checkedItem, object : OnSingleChoiceListener() {
            /**
             * @param checked:当前选中的item
             * @param oldChecked:先前选中的item
             */
            override fun onChecked(dialog: DialogInterface, checked: Int, oldChecked: Int) {
                listener.invoke(dialog, checked, oldChecked)
            }

        })

    /**
     * 设置单选列表
     * @param items:选项数组
     * @param checkedArray:默认选中的item的位置数组，为null时全不选中
     * @param listener:点击监听
     */
    @JvmOverloads
    fun setMultiChoiceItems(
        items: Array<String>,
        checkedArray: IntArray? = null,
        listener: OnMultiChoiceListener
    ) = this.apply {
        type = MD_MULTI_CHOICE
        itemList.addAll(items.map { CheckItemParams(text = it) })
        checkedArray?.forEach {
            itemList[it].isChecked = true
        }
        multiChoiceListener = listener
    }

    /**
     * @see setMultiChoiceItems
     */
    @JvmOverloads
    fun setMultiChoiceItems(
        items: Array<String>,
        checkedArray: IntArray? = null,
        listener: (
            position: Int,
            isChecked: Boolean,
            checkedArray: IntArray?,
            dialog: DialogInterface
        ) -> Unit
    ) = setMultiChoiceItems(items, checkedArray, object : OnMultiChoiceListener() {
        /**
         * @param position:当前点击的item位置
         * @param isChecked:点击后是否选中
         * @param checkedArray:选中的item下标数组，为null时没有选中项
         */
        override fun onChecked(
            position: Int,
            isChecked: Boolean,
            checkedArray: IntArray?,
            dialog: DialogInterface
        ) {
            listener.invoke(position, isChecked, checkedArray, dialog)
        }

    })

    /**
     * 按键点击监听事件
     */
    fun setOnKeyListener(listener: OnKeyListener) = this.apply {
        keyListener = listener
    }

    /**
     * 按键点击监听事件
     */
    fun setOnKeyListener(listener: (dialog: DialogInterface, keyCode: Int, event: KeyEvent) -> Boolean) =
        setOnKeyListener(object : OnKeyListener() {
            override fun onKey(dialog: DialogInterface, keyCode: Int, event: KeyEvent): Boolean {
                return listener.invoke(dialog, keyCode, event)
            }
        })



    /**
     * 显示对话框
     * @param tag:DialogFragment的Tag，默认为“MaterialDialog”
     */
    fun show(tag: String = MaterialDialog.alertP.tag) {
        this.tag = tag
        AlertDialog.showDialog(
            fm,
            tag,
            this
        )
    }

    companion object {
        private lateinit var fm: androidx.fragment.app.FragmentManager
        @JvmStatic
        fun build(fm: androidx.fragment.app.FragmentManager): AlertBuilder {
            Companion.fm = fm
            return AlertBuilder()
        }
    }

}

/**
 * 公共配置方法
 */
@Suppress("UNCHECKED_CAST")
open class ComAlertParams<T : ComAlertParams<T>> : BaseAlertParams<T>() {

    /**
     * 设置对话框的动画样式
     */
    fun setAnimStyle(@StyleRes styleId: Int) = this.apply { animStyle = styleId } as T

    /**
     * 窗口明暗程度，0~1.0，1全不透明，系统默认值为0.32F
     */
    fun setDimAmount(@FloatRange(from = 0.0, to = 1.0) dimAmount: Float) =
        this.apply { this.dimAmount = dimAmount } as T

    /**
     * 设置对话框宽度
     * @param width：宽度值，单位为px
     */
    fun setWidth(width: Int) = this.apply { widthPx = width }

    /**
     * 设置宽度与屏幕宽度比例
     * 如果已经设置了具体宽度则该方法无效
     * @param scale : 范围为0~1.0，为1时占满宽度
     * @see setWidth
     */
    fun setWidthScale(@FloatRange(from = 0.0, to = 1.0) scale: Float) =
        this.apply { widthScale = scale } as T

    /**
     * 设置高度与屏幕高度比例
     * @param scale : 范围为0~1.0，为1时占满高度
     */
    fun setHeightScale(@FloatRange(from = 0.0, to = 1.0) scale: Float) =
        this.apply { heightScale = scale } as T

    /**
     * 横屏时是否保持竖屏时的宽度，默认为true
     */
    fun setKeepPortraitWidth(isKeep: Boolean) =
        this.apply { isKeepPortraitWidth = isKeep }

    /**
     * 设置背景圆角半径
     */
    fun setCornerRadius(radius: Float) = this.apply { this.radius = radius } as T

    /**
     * @see setCornerRadius
     */
    fun setCornerRadius(@DimenRes dimenId: Int) = setCornerRadius(getResPx(dimenId).toFloat())

    /**
     * 设置对话框背景色
     */
    fun setBackgroundColor(@ColorInt color: Int) =
        this.apply { backgroundColor = color } as T

    /**
     * 设置对话框背景色
     * @param colorId:颜色资源Id
     */
    fun setBackgroundColorRes(@ColorRes colorId: Int) = setBackgroundColor(getResColor(colorId))

    /**
     * 设置背景透明度
     * 范围为0.0~1.0，0为全透明，1为不透明
     */
    fun setBackgroundAlpha(@FloatRange(from = 0.0, to = 1.0) alpha: Float) =
        this.apply { backgroundAlpha = alpha } as T

    /**
     * 设置标题文字颜色
     */
    fun setTitleColor(color: Int) = this.apply { titleParams.textColor = color } as T

    /**
     * 设置标题文字颜色
     * @param colorId：颜色资源Id
     */
    fun setTitleColorRes(@ColorRes colorId: Int) = setTitleColor(getResColor(colorId))

    /**
     * 设置标题文字大小
     * 单位为sp
     */
    fun setTitleSize(titleSize: Float) = this.apply { titleParams.textSize = titleSize } as T

    /**
     * 设置信息文字大小
     * @param dimenId:单位为sp的资源文件Id
     */
    fun setTitleSize(@DimenRes dimenId: Int) = setTitleSize(getResPx(dimenId).toFloat())

    /**
     * 设置信息文字颜色
     */
    fun setMessageColor(@ColorInt color: Int) = this.apply { msgParams.textColor = color } as T

    /**
     * 设置信息文字颜色
     * @param colorId:颜色资源Id
     */
    fun setMessageColorRes(@ColorRes colorId: Int) =
        this.apply { setMessageColor(getResColor(colorId)) } as T

    /**
     * 设置信息文字大小
     * @param messageSize:单位为sp
     */
    fun setMessageSize(messageSize: Float) =
        this.apply { msgParams.textSize = px2sp(messageSize) } as T

    /**
     * 设置信息文字大小
     * @param dimenId:单位为sp的资源文件Id
     */
    fun setMessageSize(@DimenRes dimenId: Int) = setMessageSize(getResPx(dimenId).toFloat())

    /**
     * 设置Positive按钮（即右侧的“确认”）文字
     */
    fun setPositiveText(text: String) = this.apply { this.posButtonParams.text = text } as T

    /**
     * @see setPositiveText(String)
     */
    fun setPositiveText(@StringRes stringId: Int) = setPositiveText(getResString(stringId))

    /**
     * 设置Negative按钮（即中间的“取消”）文字
     */
    fun setNegativeText(text: String) = this.apply { this.negButtonParams.text = text } as T

    /**
     * 设置Negative（即中间的“取消”）按钮文字
     */
    fun setNegativeText(@StringRes stringId: Int) = setNegativeText(getResString(stringId))

    /**
     * 设置Positive按钮的文字颜色
     * @param color:颜色值
     */
    fun setPositiveTextColor(@ColorInt color: Int) =
        this.apply { this.posButtonParams.textColor = color } as T

    /**
     * 设置Positive按钮的文字颜色
     * @param colorId: 颜色资源Id
     * @see setPositiveTextColor(Int)
     */
    fun setPositiveTextColorRes(@ColorRes colorId: Int) = setPositiveTextColor(getResColor(colorId))

    /**
     * 设置Negative按钮的文字颜色
     * @param color:颜色值
     */
    fun setNegativeTextColor(@ColorInt color: Int) =
        this.apply { this.negButtonParams.textColor = color } as T

    /**
     *
     * 设置Negative按钮的文字颜色
     * @param colorId:颜色资源Id
     * @see setNegativeTextColor
     */
    fun setNegativeTextColorRes(@ColorRes colorId: Int) = setNegativeTextColor(getResColor(colorId))

    /**
     * 设置Neutral按钮的文字颜色
     * @param color : 颜色值
     */
    fun setNeutralTextColor(@ColorInt color: Int) =
        this.apply { this.neuButtonParams.textColor = color }

    /**
     * 设置Neutral按钮的文字颜色
     * @param colorId:颜色资源Id
     */
    fun setNeutralTextColorRes(@ColorRes colorId: Int) = setNeutralTextColor(getResColor(colorId))
}
