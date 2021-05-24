package com.lindroy.materialdialog

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.lindroy.materialdialog.params.*

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

        internal lateinit var alertP: ComAlertParams<*>
        internal lateinit var bottomP: ComBottomParams<*>
        internal lateinit var bottomListP: ComBottomListParams<*>
        internal lateinit var bottomGridP: ComBottomGridParams<*>

        internal val appContext: Context
            get() = application.applicationContext


        @JvmStatic
        fun init(app: Application): Configs {
            application = app
            globalParams = Configs.config()
            alertP = ComAlertParams<AlertBuilder>()
            bottomP = ComBottomParams<BottomBuilder>()
            bottomListP = ComBottomListParams<BottomListParams>()
            bottomGridP = ComBottomGridParams<BottomGridBuilder>()
            return globalParams
        }

        @JvmStatic
        fun alert(activity: FragmentActivity) = AlertBuilder.build(activity.supportFragmentManager)

        @JvmStatic
        fun alert(fragment:Fragment) = AlertBuilder.build(fragment.childFragmentManager)

        @JvmStatic
        fun bottom(activity: FragmentActivity) =
            BottomBuilder.build(activity.supportFragmentManager)

        @JvmStatic
        fun bottom(fragment: Fragment) =
            BottomBuilder.build(fragment.childFragmentManager)

        @JvmStatic
        fun bottomList(activity: FragmentActivity) =
            BottomListParams.build(activity.supportFragmentManager)

        @JvmStatic
        fun bottomList(fragment: Fragment) =
            BottomListParams.build(fragment.childFragmentManager)

        @JvmStatic
        fun bottomGrid(activity: FragmentActivity) =
            BottomGridBuilder.build(activity.supportFragmentManager)

        @JvmStatic
        fun bottomGrid(fragment: Fragment) =
            BottomGridBuilder.build(fragment.childFragmentManager)
    }

    /**
     * 全局配置
     */
    class Configs private constructor() {

        companion object {
            fun config() = Configs()
        }

        fun initAlert(block: ComAlertParams<*>.() -> Unit) =
            this.apply { alertP.apply(block) }

        fun initBottom(block: ComBottomParams<*>.() -> Unit) = this.apply { bottomP.apply(block) }

        fun initBottomList(block: ComBottomListParams<*>.() -> Unit) =
            this.apply { bottomListP.apply(block) }

        fun initBottomGrid(block: ComBottomGridParams<*>.() -> Unit) =
            this.apply { bottomGridP.apply(block) }
    }

}