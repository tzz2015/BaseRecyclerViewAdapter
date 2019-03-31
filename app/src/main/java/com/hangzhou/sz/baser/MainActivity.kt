package com.hangzhou.sz.baser

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import com.hangzhou.sz.baser.databinding.ActivityMainBinding
import com.hangzhou.sz.baser.fragment.GroupFragment
import com.hangzhou.sz.baser.fragment.JavaFragment
import com.hangzhou.sz.baser.fragment.KotlinFragment
import com.hangzhou.sz.baser.repository.Presenter
import com.hangzhou.sz.baser.utils.inTransaction

class MainActivity : AppCompatActivity(), Presenter {
    private lateinit var binding: ActivityMainBinding
    private val javaFragment: JavaFragment by lazy { JavaFragment() }
    private val kotlinFragment: KotlinFragment by lazy { KotlinFragment() }
    private val groupFragment: GroupFragment by lazy { GroupFragment() }
    private lateinit var mCurorFragment:Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setVariable(BR.presenter, this)
        supportFragmentManager.inTransaction {
            add(R.id.fl_context, kotlinFragment)
            add(R.id.fl_context, javaFragment)
            add(R.id.fl_context, groupFragment)
            hide(groupFragment)
        }
        hideShowFragment(kotlinFragment, javaFragment)

    }

    /**
     * 点击事件
     */
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.bt_java -> {
                hideShowFragment(mCurorFragment, javaFragment)
            }
            R.id.bt_kotlin -> {
                hideShowFragment(mCurorFragment, kotlinFragment)
            }
            R.id.bt_group -> {
                hideShowFragment(mCurorFragment, groupFragment)
            }

        }
    }

    private fun hideShowFragment(hide: Fragment, show: Fragment) {
        supportFragmentManager.inTransaction {
            hide(hide)
            show(show)
        }
        mCurorFragment=show
    }
}
