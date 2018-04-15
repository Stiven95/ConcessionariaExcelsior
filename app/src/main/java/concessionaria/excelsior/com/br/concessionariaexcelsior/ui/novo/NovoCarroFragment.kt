package concessionaria.excelsior.com.br.concessionariaexcelsior.ui.novo


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import concessionaria.excelsior.com.br.concessionariaexcelsior.R
import concessionaria.excelsior.com.br.concessionariaexcelsior.api.CarroAPI
import concessionaria.excelsior.com.br.concessionariaexcelsior.api.RetrofitClient
import concessionaria.excelsior.com.br.concessionariaexcelsior.model.Carro
import kotlinx.android.synthetic.main.fragment_novo_carro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NovoCarroFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_novo_carro, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btSalvar.setOnClickListener {
            val api = RetrofitClient
                    .getInstance("http://concessionariapistiven.herokuapp.com")
                    .create(CarroAPI::class.java)

            if (!validarCadastro()) {
                Toast.makeText(context, "Preencher todos os campos", Toast.LENGTH_LONG).show()
            } else {

                val carro = Carro(null,
                        inputMarca.editText?.text.toString(),
                        inputModelo.editText?.text.toString(),
                        inputAno.editText?.text.toString().toInt(),
                        inputPlaca.editText?.text.toString(),
                        inputUrlImagem.editText?.text.toString(),
                        inputPreco.editText?.text.toString().toInt(),
                        inputDescricao.editText?.text.toString())

                api.salvar(carro).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                        if (response?.isSuccessful == true) {
                            Toast.makeText(context,
                                    "Carro cadastrado com Sucesso",
                                    Toast.LENGTH_SHORT).show()
                            limparCampos()
                        } else {
                            Toast.makeText(context,
                                    "Não foi possivel cadastrar um carro",
                                    Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>?, t: Throwable?) {
                        Log.e("CARRO", t?.message)
                    }
                })
            }
        }
    }

    fun validarCadastro(): Boolean {
        var valor = true

        if(inputMarca.editText?.text.toString() == null || inputMarca.editText?.text.toString().equals("")) {
            valor = false
        }

        if(inputModelo.editText?.text.toString() == null || inputModelo.editText?.text.toString().equals("")){
            valor = false
        }

        //if(inputAno.editText?.text.toString().toInt() < 0 || inputAno.editText?.text.toString().toInt().equals("")){
            //valor = false
        //}

        //if(inputPreco.editText?.text.toString().toInt() < 0 || inputPreco.editText?.text.toString().toInt().equals("")){
            //valor = false
        //}

        return valor
    }

    private fun limparCampos() {
        inputMarca.editText?.setText("")
        inputModelo.editText?.setText("")
        inputAno.editText?.setText("")
        inputPlaca.editText?.setText("")
        inputUrlImagem.editText?.setText("")
        inputPreco.editText?.setText("")
        inputDescricao.editText?.setText("")
    }
}
