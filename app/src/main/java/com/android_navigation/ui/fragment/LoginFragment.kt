package com.android_navigation.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.android_navigation.R
import com.android_navigation.model.Usuario
import com.android_navigation.ui.extensions.snackbar
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

        configuraBotaoLogin()

        configuraBotaoCadastro()
    }

    private fun configuraBotaoCadastro() {
        login_botao_cadastrar_usuario.setOnClickListener {
            val directions =
                LoginFragmentDirections.actionLoginToCadastroUsuario()
            controller.navigate(directions)
        }
    }

    private fun configuraBotaoLogin() {
        login_botao_logar.setOnClickListener {
            limpaCampos()
            val email = login_email.editText?.text.toString()
            val senha = login_senha.editText?.text.toString()

            if (validaCampos(email, senha)) {
                autentica(email, senha)
            }
        }
    }

    private fun limpaCampos() {
        login_email.error = null
        login_senha.error = null
    }

    private fun validaCampos(email: String, senha: String): Boolean {
        var valido = true

        if (email.isBlank()) {
            login_email.error = "E-mail é obrigatório"
            valido = false
        }

        if (senha.isBlank()) {
            login_senha.error = "Senha é obrigatório"
            valido = false
        }
        return valido
    }

    private fun autentica(email: String, senha: String) {
        viewModel.autentica(Usuario(email, senha)).observe(viewLifecycleOwner, Observer { it ->
            it?.let { resource ->
                if (resource.dado) {
                    vaiParaListaProdutos()
                } else {
                    val mensagemErro = resource.error ?: "Erro durante a autenticação"
                    view?.snackbar(mensagemErro)
                }
            }
        })
    }

    private fun vaiParaListaProdutos() {
        val diresion =
            LoginFragmentDirections.actionLoginToListaProdutos()
        controller.navigate(diresion)
    }
}