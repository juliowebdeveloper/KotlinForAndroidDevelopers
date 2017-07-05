package com.ui.utils

import android.content.Context
import android.view.View

/**
 * Created by Shido on 22/06/2016.
 */


/*Anko deixa o codigo mais simples adicionando contexto para activities e fragments, que retorna o contexto
* mas falta essa propriedade para views, adicionando esse pedaço, podemos adicionar essa propriedade extendida e retornar o contexto
* e serve como exemplo de propriedades extendidas*/
val View.ctx: Context
get() = context