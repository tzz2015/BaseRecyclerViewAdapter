package com.hangzhou.sz.baser

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.View
import android.widget.Toast
import com.hangzhou.sz.baser.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Presenter {
    lateinit var binding: ActivityMainBinding
    val javaFragment:JavaFragment by lazy {JavaFragment()}
    val kotlinFragment:KotlinFragment by lazy { KotlinFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setVariable(BR.presenter, this)
        supportFragmentManager.inTransaction {
            add(R.id.fl_context,kotlinFragment)
            add(R.id.fl_context,javaFragment)
            hide(kotlinFragment)
            show(javaFragment)
        }

    }

    /**
     * 点击事件
     */
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.bt_java -> {
                supportFragmentManager.inTransaction {
                    hide(kotlinFragment)
                    show(javaFragment)
                }
            }
            R.id.bt_kotlin -> {
                supportFragmentManager.inTransaction {
                    hide(javaFragment)
                    show(kotlinFragment)
                }
            }

        }
    }
}
