package com.lindroy.anddialog.constants

import android.support.annotation.IntDef
import com.lindroy.anddialog.MaterialDialog

/**
 * @author Lin
 * @date 2019/8/15
 * @function
 * @Description
 */
@Target(
        AnnotationTarget.CLASS, AnnotationTarget.FUNCTION,
        AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.EXPRESSION
)
@Retention(AnnotationRetention.SOURCE)
@IntDef(MaterialDialog.ALERT,MaterialDialog.SINGLE_CHOICE,MaterialDialog.MULTI_CHOICE,MaterialDialog.BOTTOM)
annotation class DialogType