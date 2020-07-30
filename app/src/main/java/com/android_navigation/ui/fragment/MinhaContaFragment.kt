package com.android_navigation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.android_navigation.R
import com.android_navigation.viewmodel.MinhaContaViewModel
import kotlinx.android.synthetic.main.minha_conta.*
import org.koin.android.viewmodel.ext.android.viewModel

class MinhaContaFragment : BaseFragment() {

    private val viewModel by viewModel<MinhaContaViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.minha_conta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.usuario.observe(viewLifecycleOwner, Observer {
            it?.let { usuario ->
                minha_conta_email.text = usuario.email
            }
        })
    }
}