package com.android_navigation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.android_navigation.R
import com.android_navigation.viewmodel.CadastroUsuarioViewModel
import com.android_navigation.viewmodel.ComponentesVisuais
import com.android_navigation.viewmodel.EstadoAppViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.cadrastro_usuario.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CadrastroUsuarioFragment : Fragment() {

    private val controller by lazy {
        findNavController()
    }

    private val estadoAppViewModel: EstadoAppViewModel by sharedViewModel()
    private val viewModel: CadastroUsuarioViewModel by viewModel()

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
            val email = cadastro_usuario_email.editText?.text.toString()
            val senha = cadastro_usuario_senha.editText?.text.toString()
            viewModel.cadrastra(email, senha).observe(viewLifecycleOwner, Observer {
                it?.let { cadastrado ->
                    if (cadastrado) {
                        Snackbar.make(view, "Usuario cadastrado", Snackbar.LENGTH_LONG).show()
                        controller.popBackStack()
                    } else {
                        Snackbar.make(view, "Erro ao cadastrar usuario", Snackbar.LENGTH_LONG)
                            .show()
                    }
                }
            })

        }
    }
}