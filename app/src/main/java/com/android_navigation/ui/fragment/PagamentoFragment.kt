package com.android_navigation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.android_navigation.R
import com.android_navigation.model.Pagamento
import com.android_navigation.model.Produto
import com.android_navigation.ui.activity.CHAVE_PRODUTO_ID
import com.android_navigation.ui.extensions.formatParaMoedaBrasileira
import com.android_navigation.viewmodel.PagamentoViewModel
import kotlinx.android.synthetic.main.pagamento.*
import org.koin.android.viewmodel.ext.android.viewModel

private const val FALHA_AO_CRIAR_PAGAMENTO = "Falha ao criar pagamento"
private const val COMPRA_REALIZADA = "Compra realizada"


class PagamentoFragment : Fragment() {

    private val produtoId by lazy {
        arguments?.getLong(CHAVE_PRODUTO_ID) ?: throw IllegalArgumentException(ID_PRODUTO_INVALIDO)
    }
    private val viewModel: PagamentoViewModel by viewModel()
    private lateinit var produtoEscolhido: Produto
    private val controller by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pagamento, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraBotaoConfirmaPagamento()
        buscaProduto()
    }

    private fun buscaProduto() {
        viewModel.buscaProdutoPorId(produtoId).observe(this, Observer {
            it?.let { produtoEncontrado ->
                produtoEscolhido = produtoEncontrado
                pagamento_preco.text = produtoEncontrado.preco.formatParaMoedaBrasileira()
            }
        })
    }

    private fun configuraBotaoConfirmaPagamento() {
        pagamento_botao_confirma_pagamento.setOnClickListener {
            criarPagamento()?.let(this::salva) ?: Toast.makeText(
                context,
                FALHA_AO_CRIAR_PAGAMENTO,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun salva(pagamento: Pagamento) {
        if (::produtoEscolhido.isInitialized) {
            viewModel.salva(pagamento).observe(this, Observer {
                it?.dado?.let {
                    Toast.makeText(context, COMPRA_REALIZADA, Toast.LENGTH_LONG).show()
                    vaiParaListaProdutos()
                }
            })
        }
    }

    private fun vaiParaListaProdutos() {
        controller.navigate(R.id.action_pagamento_to_listaProdutos)
    }

    private fun criarPagamento(): Pagamento? {
        val numeroCartao = pagamento_numero_cartao.editText?.text.toString()
        val dataValidade = pagamento_data_validade.editText?.text.toString()
        val cvc = pagamento_cvc.editText?.text.toString()
        return geraPagamento(numeroCartao, dataValidade, cvc)
    }

    private fun geraPagamento(numeroCartao: String, dataValidade: String, cvc: String): Pagamento? =
        try {
            Pagamento(
                numeroCartao = numeroCartao.toInt(),
                dataValidade = dataValidade,
                cvc = cvc.toInt(),
                produtoId = produtoId,
                preco = produtoEscolhido.preco
            )
        } catch (e: NumberFormatException) {
            null
        }
}
