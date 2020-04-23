package com.android_navigation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android_navigation.R
import com.android_navigation.viewmodel.ComponentesVisuais
import com.android_navigation.viewmodel.EstadoAppViewModel
import kotlinx.android.synthetic.main.cadrastro_usuario.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CadrastroUsuario : Fragment() {

    private val controller by lazy {
        findNavController()
    }

    private val estadoAppViewModel: EstadoAppViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cadrastro_usuario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        estadoAppViewModel.temComponentes = ComponentesVisuais()
        cadastro_usuario_botao_cadastrar.setOnClickListener {
            controller.popBackStack()
        }
    }
}