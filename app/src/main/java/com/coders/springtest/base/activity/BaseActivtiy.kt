package com.coders.springtest.base.activity;

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.coders.springtest.base.viewmodel.BaseViewModel

/**
 * Base class used by Activities implementing the MVVM pattern.
 * @param [VM] the type of the View Mode
 * @param [DB] the type of the Data Binding
 * get View model [viewModelClass] from factory
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding>(private val viewModelClass: Class<VM>) :
    AppCompatActivity() {

    /**
     * @return [Int] get id for the layout resource
     */
    @LayoutRes
    abstract fun getLayoutRes(): Int

    /**
     * @return view model factory
     */
    abstract fun getVMFactory(): ViewModelProvider.Factory

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }

    val viewModel by lazy {
        ViewModelProviders.of(this, getVMFactory()).get(viewModelClass)
    }

    /**
     * If you want to inject Dependency Injection
     * on your activity, you can override this.
     */
    open fun onInject() {}


    /**
     * If you want to do some initialization of resources, setting listeners
     * on your activity, you can override this.
     */

    open fun init() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        onInject()
        super.onCreate(savedInstanceState)
        initViewModel(viewModel)
    }

    /**
     *  You need override this method.
     *  Initialize view model listen to live data of [viewModel]
     *  And you need to set viewModel to binding: binding.viewModel = viewModel
     *
     */
    abstract fun initViewModel(viewModel: VM)
}