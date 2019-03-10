package com.hangzhou.sz.baser.utils

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.Gravity
import android.widget.Toast

/**
 * 创建 by 刘宇飞 on 2019/3/10.
 * 描述：
 */
fun Context.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    val mToast = Toast.makeText(this, msg, length)
    mToast.setGravity(Gravity.CENTER, 0, 0)
    mToast.show()

}
fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}