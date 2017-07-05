package julio.com.br.kotlin3

import android.app.Application
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by Shido on 27/08/2016.
 */

//Criando um Singleton da classe aplication
class App: Application() {

    companion object{
        //Utilizando o lateinit delegate pois sabemos que nosso singlton nao será null, mas nao poder usar o construtor para definir a propriedade
       // lateinit var instance: App by
       // private set
        //Utilizamos private set para que nao possamos mudar o valor da instance em nenhum lgar no App
      //  private var instance: Application? = null
        //fun instance () = instance!!
        //Utilizando propriedade delegada (de forma lazy)
        //val database : SQLiteOpenHelper by lazy {  }
    }

    override fun onCreate(){
        super.onCreate()
       // instance = this
    }
}