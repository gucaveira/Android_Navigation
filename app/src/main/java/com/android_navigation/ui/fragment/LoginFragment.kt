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
import com.android_navigation.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.login.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val controller by lazy {
        findNavController()
    }

    private val viewModel: LoginViewModel by viewModel()
    private val estadoAppViewModel: EstadoAppViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        estadoAppViewModel.temComponentes = ComponentesVisuais()
        login_botao_logar.setOnClickListener {
            viewModel.loga()
            vaiParaListaProdutos()
        }
        login_botao_cadastrar_usuario.setOnClickListener {
            val directions =
                LoginFragmentDirections.actionLoginToCadastroUsuario()
            controller.navigate(directions)
        }
    }

    private fun vaiParaListaProdutos() {
        val diresion =
            LoginFragmentDirections.actionLoginToListaProdutos()
        controller.navigate(diresion)
    }
}