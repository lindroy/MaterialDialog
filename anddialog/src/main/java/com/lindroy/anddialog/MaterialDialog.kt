package com.lindroy.anddialog

import android.app.Application
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.lindroy.anddialog.params.AlertParams
import com.lindroy.anddialog.params.BottomMenuParams
import com.lindroy.anddialog.params.BottomParams
import com.lindroy.anddialog.params.ComParams

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
        internal lateinit var buildParams: AlertParams

        internal val appContext: Context
            get() = application.applicationContext

        @JvmStatic
        fun init(app: Application): Configs {
            application = app
            globalParams =
                Configs.config()
            return globalParams
        }

        @JvmStatic
        fun alert(activity: FragmentActivity): AlertParams {
            buildParams =
                AlertParams.build(activity.supportFragmentManager)
            return buildParams
        }

        @JvmStatic
        fun alert(fragment: Fragment): AlertParams {
            buildParams =
                AlertParams.build(fragment.childFragmentManager)
            return buildParams
        }

        @JvmStatic
        fun bottom(activity: FragmentActivity) =
            BottomParams.build(activity.supportFragmentManager)

        @JvmStatic
        fun bottom(fragment: Fragment) =
            BottomParams.build(fragment.childFragmentManager)

        @JvmStatic
        fun bottomList(activity: FragmentActivity) =
            BottomMenuParams.build(activity.supportFragmentManager)

        @JvmStatic
        fun bottomList(fragment: Fragment) =
            BottomMenuParams.build(fragment.childFragmentManager)
    }

    /**
     * 全局配置
     */
    class Configs private constructor() : ComParams<AlertParams>() {
        /**
         * 设置默认的tag
         */
        fun setTag(tag: String) = this.apply { this.tag = tag }

        companion object {
            fun config() = Configs()
        }
    }



}