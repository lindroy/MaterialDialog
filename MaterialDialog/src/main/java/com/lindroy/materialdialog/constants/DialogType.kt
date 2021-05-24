package com.lindroy.materialdialog.constants

import androidx.annotation.IntDef

/**
 * @author Lin
 * @date 2019/8/15
 * @function 对话框类型
 * @Description
 */
const val MD_ALERT = 0x001
const val MD_SINGLE_CHOICE = 0x002
const val MD_MULTI_CHOICE = 0x003
const val MD_BOTTOM = 0x004
const val MD_BOTTOM_LIST = 0x005
const val MD_BOTTOM_Grid = 0x006
@Target(
        AnnotationTarget.CLASS, AnnotationTarget.FUNCTION,
        AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.EXPRESSION
)
@Retention(AnnotationRetention.SOURCE)
@IntDef(MD_ALERT,
        MD_SINGLE_CHOICE,
        MD_MULTI_CHOICE,
        MD_BOTTOM,
        MD_BOTTOM_LIST,
        MD_BOTTOM_Grid
)
annotation class DialogType