package com.hangzhou.sz.baser

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.hangzhou.sz.baser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Presenter {
   private lateinit var binding: ActivityMainBinding
   private val javaFragment:JavaFragment by lazy {JavaFragment()}
   private val kotlinFragment:KotlinFragment by lazy { KotlinFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setVariable(BR.presenter, this)
        supportFragmentManager.inTransaction {
            add(R.id.fl_context,kotlinFragment)
            add(R.id.fl_context,javaFragment)
        }
        hideShowFragment(kotlinFragment,javaFragment)

    }

    /**
     * 点击事件
     */
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.bt_java -> {
                hideShowFragment(kotlinFragment,javaFragment)
            }
            R.id.bt_kotlin -> {
               hideShowFragment(javaFragment,kotlinFragment)
            }

        }
    }

    private fun hideShowFragment(hide:Fragment, show:Fragment){
        supportFragmentManager.inTransaction {
            hide(hide)
            show(show)
        }
    }
}
