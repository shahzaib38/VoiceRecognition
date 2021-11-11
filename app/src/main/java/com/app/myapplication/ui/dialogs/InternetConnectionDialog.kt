package com.app.myapplication.ui.dialogs


import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.app.myapplication.BR
import com.app.myapplication.R
import com.app.myapplication.databinding.ConnectionDialogBinding
import com.app.myapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InternetConnectionDialog : BaseDialogFragment<MainViewModel, ConnectionDialogBinding>()  {

    private  var bind :ConnectionDialogBinding?=null
    private val mViewModel by viewModels<MainViewModel>()


    companion object{
        private const val TAG :String= "ConnectionFragmentDialog"
        fun getInstance(): InternetConnectionDialog {
            return InternetConnectionDialog() }

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind= getViewDataBinding()
        bind?.ok?.setOnClickListener { dismissDialog() }
    }






    override fun showDialog(fragment: FragmentManager) {
        super.show(fragment ,TAG) }


    override fun getBindingVariable(): Int {
        return  BR.viewModel
    }

    override fun getViewModel(): MainViewModel? =mViewModel

    override fun getLayoutId(): Int {
        return  R.layout.internet_coonection    }

    private   fun dismissDialog() {
        super.dismissDialog(TAG)

    }


}
