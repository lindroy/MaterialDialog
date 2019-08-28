package com.lindroy.anddialog

import android.app.Application
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.lindroy.anddialog.params.*

/**
 * @author Lin
 * @date 2019/8/14
 * @function
 * @Description
 */
class MaterialDialog private constructor() {

    companion object {

        private lateinit var application: Application
        private lateinit var globalParams: Configs
        internal lateinit var buildParams: AlertParams
        lateinit var alertP: ComAlertParams<*>

        internal val appContext: Context
            get() = application.applicationContext


        @JvmStatic
        fun init(app: Application): Configs {
            application = app
            globalParams =
                Configs.config()
            alertP = ComAlertParams<AlertParams>()
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
            BottomListParams.build(activity.supportFragmentManager)

        @JvmStatic
        fun bottomList(fragment: Fragment) =
            BottomListParams.build(fragment.childFragmentManager)

        @JvmStatic
        fun bottomGrid(activity: FragmentActivity) =
            BottomGridParams.build(activity.supportFragmentManager)

        @JvmStatic
        fun bottomGrid(fragment: Fragment) =
            BottomGridParams.build(fragment.childFragmentManager)
    }

    /**
     * 全局配置
     */
    class Configs private constructor()  {

        companion object {
            fun config() = Configs()
        }

        fun initAlert(block: ComAlertParams<*>.() -> Unit) =
            this.apply { alertP.apply(block) }

        fun initBottom(block: BottomParams.() -> Unit) = this.apply { BottomParams().apply(block) }
    }


}