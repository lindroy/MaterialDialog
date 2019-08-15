package com.lindroy.anddialog

import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.support.annotation.*
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.lindroy.anddialog.bean.ComParams
import com.lindroy.anddialog.listener.OnDialogClickListener
import com.lindroy.iosdialog.util.getResColor
import com.lindroy.iosdialog.util.getResPx
import com.lindroy.iosdialog.util.getResString
import com.lindroy.iosdialog.util.px2sp

/**
 * @author Lin
 * @date 2019/8/14
 * @function
 * @Description
 */
class MaterialDialog private constructor() {

    companion object {
        private lateinit var application: Application
        internal lateinit var globalParams: Configs
        internal lateinit var buildParams: Builder

        internal val appContext: Context
            get() = application.applicationContext

        @JvmStatic
        fun init(app: Application): Configs {
            application = app
            globalParams = Configs.config()
            return globalParams
        }

        @JvmStatic
        fun build(activity: FragmentActivity): Builder {
            buildParams = Builder.build(activity.supportFragmentManager)
            return buildParams
        }

        @JvmStatic
        fun build(fragment: Fragment): Builder {
            buildParams = Builder.build(fragment.childFragmentManager)
            return buildParams
        }
    }

    /**
     * 全局配置
     */
    class Configs private constructor() : ComParams<Builder>() {
        /**
         * 设置默认的tag
         */
        fun setTag(tag: String) = this.apply { this.tag = tag }

        companion object {
            fun config() = Configs()
        }
    }

    /**
     * 单次配置
     */
    class Builder private constructor(): ComParams<Builder>() {

        init {
            globalParams.also {
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
            cancelableOutSide = isCancelable
        }

        /**
         * 点击对话框上的按钮是否可以关闭对话框，默认为true
         */
        fun setDismissible(dismissible: Boolean) = this.apply { this.dismissible = dismissible }

        /**
         * 设置对话框背景色
         */
        fun setBackgroundColor(@ColorInt color: Int) =
            this.apply { backgroundColor = color }

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
            this.apply { backgroundAlpha = alpha }

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
         * 设置标题文字颜色
         */
        fun setTitleColor(color: Int) = this.apply { titleParams.textColor = color }

        /**
         * 设置标题文字颜色
         * @param colorId：颜色资源Id
         */
        fun setTitleColorRes(@ColorRes colorId: Int) = setTitleColor(getResColor(colorId))

        /**
         * 设置标题文字大小
         * 单位为sp
         */
        fun setTitleSize(titleSize: Float) = this.apply { titleParams.textSize = titleSize }

        /**
         * 设置信息文字大小
         * @param dimenId:单位为sp的资源文件Id
         */
        fun setTitleSize(@DimenRes dimenId: Int) = setTitleSize(getResPx(dimenId).toFloat())

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
         * 设置信息文字颜色
         */
        fun setMessageColor(@ColorInt color: Int) = this.apply { msgParams.textColor = color }

        /**
         * 设置信息文字颜色
         * @param colorId:颜色资源Id
         */
        fun setMessageColorRes(@ColorRes colorId: Int) =
            this.apply { setMessageColor(getResColor(colorId)) }

        /**
         * 设置信息文字大小
         * @param messageSize:单位为sp
         */
        fun setMessageSize(messageSize: Float) = this.apply { msgParams.textSize = px2sp(messageSize) }

        /**
         * 设置信息文字大小
         * @param dimenId:单位为sp的资源文件Id
         */
        fun setMessageSize(@DimenRes dimenId: Int) = setMessageSize(getResPx(dimenId).toFloat())

        /**
         * 设置Positive按钮（即右侧的“确认”）文字
         */
        fun setPositiveText(text: String) = this.apply { this.posButtonParams.text = text }

        /**
         * @see setPositiveText(String)
         */
        fun setPositiveText(@StringRes stringId: Int) = setPositiveText(getResString(stringId))

        /**
         * 设置Negative按钮（即中间的“取消”）文字
         */
        fun setNegativeText(text: String) = this.apply { this.negButtonParams.text = text }

        /**
         * 设置Positive按钮（即右侧的“确认”）文字
         */
        fun setNegativeText(@StringRes stringId: Int) = setNegativeText(getResString(stringId))

        /**
         * 设置Neutral按钮（即最左侧的按钮）文字
         */
        fun setNeutralText(text: String) = this.apply { this.neuButtonParams.text = text }

        /**
         * @see setNeutralText(String)
         */
        fun setNeutralText(@StringRes stringId: Int) = setNeutralText(getResString(stringId))

        /**
         * 设置Positive按钮的文字颜色
         * @param color:颜色值
         */
        fun setPosTextColor(@ColorInt color: Int) = this.apply { this.posButtonParams.textColor = color }

        /**
         * 设置Positive按钮的文字颜色
         * @param colorId: 颜色资源Id
         * @see setPosTextColor(Int)
         */
        fun setPosTextColorRes(@ColorRes colorId: Int) =
            this.apply { setPosTextColor(getResColor(colorId)) }

        /**
         * 设置Negative按钮的文字颜色
         * @param color:颜色值
         */
        fun setNegTextColor(@ColorInt color: Int) = this.apply { this.negButtonParams.textColor = color }

        /**
         *
         * 设置Negative按钮的文字颜色
         * @param colorId:颜色资源Id
         * @see setNegTextColor
         */
        fun setNegTextColorRes(@ColorRes colorId: Int) =
            this.apply { setNegTextColor(getResColor(colorId)) }

        /**
         * 是否显示Negative按钮，默认为true，显示
         */
        fun setNegButtonVisible(isVisible: Boolean) = this.apply { this.negButtonParams.isVisible = isVisible }

        /**
         * 设置Neutral按钮的文字颜色
         * @param color : 颜色值
         */
        fun setNeuTextColor(@ColorInt color: Int) = this.apply { this.negButtonParams.textColor = color }

        /**
         * 设置Neutral按钮的文字颜色
         * @param colorId:颜色资源Id
         */
        fun setNeuTextColorRes(@ColorRes colorId: Int) =
            this.apply { setNeuTextColor(getResColor(colorId)) }

        /**
         * 是否显示Negative按钮，默认为true，显示
         */
        fun setNeuButtonVisible(isVisible: Boolean) = this.apply { this.negButtonParams.isVisible = isVisible }

        /**
         * Positive按钮的点击监听
         * 用于屏幕旋转保存状态和Java调用
         */
        fun setOnPosClickListener(listener: OnDialogClickListener) =
            this.apply { this.posButtonParams.clickListener = listener }

        /**
         * Positive按钮的点击监听
         */
        fun setOnPosClickListener(listener: (dialog: DialogInterface) -> Unit) =
            setOnPosClickListener(object : OnDialogClickListener() {
                override fun onClick(dialog: DialogInterface) {
                    listener.invoke(dialog)
                }
            })

        /**
         * Negative按钮的点击监听
         * 用于屏幕旋转保存状态和Java调用
         */
        fun setOnNegClickListener(listener: OnDialogClickListener) =
            this.apply { this.negButtonParams.clickListener = listener }

        /**
         * Negative按钮的点击监听
         */
        fun setOnNegClickListener(listener: (dialog: DialogInterface) -> Unit) =
            setOnNegClickListener(object : OnDialogClickListener() {
                override fun onClick(dialog: DialogInterface) {
                    listener.invoke(dialog)
                }
            })

        /**
         * Neutral按钮的点击监听
         * 用于屏幕旋转保存状态和Java调用
         */
        fun setOnNeuClickListener(listener: OnDialogClickListener) =
            this.apply { this.neuButtonParams.clickListener = listener }

        /**
         * Neutral按钮的点击监听
         */
        fun setOnNeuClickListener(listener: (dialog: DialogInterface) -> Unit) =
            setOnNeuClickListener(object : OnDialogClickListener() {
                override fun onClick(dialog: DialogInterface) {
                    listener.invoke(dialog)
                }
            })

        /**
         * 显示对话框
         * @param tag:DialogFragment的Tag，默认为“iOSDialog”
         */
        fun show(tag: String = globalParams.tag) {
            this.tag = tag
            MaterialController.showDialog(fm, tag, buildParams)
        }

        companion object {
            private lateinit var fm: FragmentManager
            @JvmStatic
            fun build(fm: FragmentManager): Builder {
                this.fm = fm
                return Builder()
            }
        }
    }
}