package julio.com.br.kotlin3

import adapter.ForecastListAdapter
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import domain.RequestForecastCommand
import junit.framework.Assert
import kotlinx.android.synthetic.main.activity_main.*
import model.Person
import org.jetbrains.anko.*
import request.ForecastRequest
import request.Request
import java.net.URL
import java.nio.charset.Charset
import java.util.concurrent.Executors
import domain.Forecast as ModelForecast

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mensagem.text = "Hello Kotlin"
        mensagem.setOnClickListener { somar(4, 5);

            //niceToast("hi there");
            //Snackbar.make(message,"dsadas", Snackbar.LENGTH_LONG)


            //passando a propria view(message usando IT) para chamar a função extendida
            it.snackShow2(message = "Snackbar appeared"){
                //Dentro da lambda que é o ultimo parametro, estamos passando uma outra function
                snackaction("action") {
                    //Passando o Toast como listener
                    toast("Actionss")
                }
            }

        }

        val person = Person()
        person.name = "julio"
        person.desc = "ola"
        // println(person.name)
        // println(person.desc)

        //message.text = person.name + person.desc


        //Chamando a function extended
       /// toastExt("Hello there", Toast.LENGTH_LONG)

        //Toast do Anko
       // toast("Hi")//Aceita resources também




        val forecast = find<RecyclerView>(R.id.forecast_list)

        forecast.layoutManager = LinearLayoutManager(this)

            Log.d("Go Async", "GO ASYNC")
        async() {
            Log.d("Async", "ASYNC")
            // val foreCastResult = ForecastRequest("321321312312").execute()


            val result = RequestForecastCommand("94043").execute()

          /*  uiThread{
                forecast.adapter = ForecastListAdapter(result, object : ForecastListAdapter.OnItemClickListener{
                    override fun invoke(forecast: ModelForecast){
                        toast(forecast.date)
                    }
                })
            }*/
            uiThread {
                //Setando a variavel forecast que é o RecyclerView com o resultado vindo do requestForecastCommand
                //Setando uma função como parametro que foi definida na ForecastListAdapter, que recebe um forecast e retorna um unit que é um toast exibindo a data daquele forecast
                forecast.adapter = ForecastListAdapter(result) {forecast -> toast(forecast.date)}
                //Simplificando ainda mais pois só tem um parametro e o retorno unit
                forecast.adapter = ForecastListAdapter(result){toast(it.date)}
            }


        }

        //forecast.adapter = ForecastListAdapter(items)



        //Anko executando numa thread separada sem precisar de AsyncTask
        //Vantagem, a parte UiThread nao será executada se o activity.isFinishing() retornar true, e não dará crash se a activity nao for mais valida
     /*   async(){
            Request("").run()
            uiThread{
                longToast("Request Performed")
            }
        }

        //Usando o proprio executor
        val executor = Executors.newScheduledThreadPool(4)
        async(executor) {
            /*Logica*/
        }*/
         //Async retorna um java Future, se quiser retornar um future com um resultado usar asyncResult


        //Utilizando o data class e modificando suas propriedades
/*
        val f1 = Forecast(Date(), 34.4f, "Dia ensolarado")
        //Fazendo o copy daquela val e somente trocando a temperatura = sem mudar o estado do objeto original!!
        val f2 = f1.copy(temperature = 40f)

        ///Atenção = em classes que supostamente nao devem ser mudadas (tipo Date) é necessario criar um wrap criando uma ImmutableDate class


        //Mapeando um objeto em variaveis
        val(date, temperature, details) = f1

        //O compilador fará o seguinte:
       /* val date = f1.component1()
        val temperature = f1.component2()
        val details = f1.component3()*/
*/


        //Função extendidade de Map para recuperar seus Keys e values:

        val map: Map<String, String> = mapOf("Key1" to "Primeiro", "key 2" to "segundo")
        for((key, value) in map){
               Log.d("map","key:$key, value:$value")
        }


        //Operações em Collections
        val list = listOf(1,2,3,4,5,6)

        //Any: Retorna true se ao menos um elemento casar com o predicado dado
        Assert.assertTrue(list.any{it %2 == 0})
        Assert.assertFalse(list.any{it>10})

        //all: retorna true se todos os elementos caserem com o predicado dado
        Assert.assertTrue(list.all {it < 10})
        Assert.assertFalse(list.all { it % 2 == 0 })

        //count: retorna o numero de elementos dado o predicado
        Assert.assertEquals(3, list.count{it %2 == 0})

        //fold = Acumula o valor começando com um valor inicial e aplicando uma operação do primeiro ao ultimo elemento da collection
        Assert.assertEquals(25, list.fold(4) {total, next ->total + next})

        //foreach
        list.forEach { println(it) }

        //foreach indexed = mesmo que o forEach mas também traz o index do elemento
        list.forEachIndexed { index, value -> println("position $index contains a $value")  }

        //max: retorna o maior elemento ou nulo se não tiver elementos
        Assert.assertEquals(6, list.max())

        //maxby : reetorna o primeiro elemtno carregando o maior valor de uma determinada funciton ou nulo se nao tiver elementos
        //nesse caso o elemento maior negativo
        Assert.assertEquals(1, list.maxBy { -it} )


        //min: retorna o maior elemento ou nulo se não tiver elementos
        Assert.assertEquals(6, list.min())

        //minby : reetorna o primeiro elemtno carregando o maior valor de uma determinada funciton ou nulo se nao tiver elementos
        //nesse caso o elemento maior negativo
        Assert.assertEquals(1, list.minBy { -it} )


        //none? retorna true se nenhum elemento combinar com o predicado dado
        //nenhum elemento é divisivel por 7
        Assert.assertTrue(list.none{it % 7 == 0})

        val lista = Assert.assertEquals(listOf(2,4,5), list.slice(listOf(1,3,4)))


        //Designando o retorno de um if à uma variavel - se o tamanho da lista foi maior que 3 a variavel return lsit recebe true se não recebe false
        val returnList = if(list.size > 3) true else false
        val returnLis2 = if(list.size > 3) 3 else null

        //Com isso não precisa de ternario


        //Expressao When - similar a switch case - tentará comparar  com todos os possiveis branches até achar uma que satisfaça
        //a diferença é que o argumento pode ser literealmente qualquer coisa e as condições para os branches também
        val x = 2
        when (x){
            1 -> print("x==1")
            2-> print("x==2")
            else -> {
                print("I'm a block")
                print("x is neither 1 nor 2")
            }
        }
        //pode retornar um valor também - deve ver todos os casos possiveis ou nao irá compilar, ou ter uma expressao else
        val resultwhen = when(x) {
            0,1 -> "binary"
            else -> "error"
        }

        //Chegcando o tipo de argumento e tomar decisões baseado nisso - suponhamos que recebemos uma view generica
        val view = find<TextView>(R.id.mensagem)
        when (view ){
            is TextView -> view.setText("i'm a texview")
            is EditText -> toast("EditText value: ${view.getText()}")
            is ViewGroup ->toast("Number of childer: ${view.childCount}")
            else -> view.visibility = View.GONE
        }
        //O argumento é automaticamente feito um cast para a parte direita da condição então nao precisa se preocupar com casting

        //Checando se o argumento está dentro de um range

        val cost = when (x){
            in 1..10 -> "barato"
            in 10..100 ->"regular"
            in 100..1000 ->"caro"
           // in specialValues ->"special values!"
            else ->"não classificado"
        }

        //Pode-se livrar do arugmento e fazer qualquer check doido que porecisar sem precisar colocar o () após o when.
        val s = "Hello world"
        val res = when {
            x in 1..10 ->"barato"
            s.contains("Hello") -> "it is a wealcome"
            view is ViewGroup -> "child count ${view.childCount}"
            else -> ""

        }




        val vg: TextView = TextView(this.ctx)
        //For loops
     /*   for (index in 0..vg.hintTextColors - 1){
           // val view = viewGroup.getChildAt(index)
            //view.visibility = View.VISIBLE
        }*/

        //Ranges -  simplifica o codigo, convertendo
        val i = 3
        if( i>=0 && i <= 10){
            println(i)
        }
        //Nisso
        if(i in 0..10)
            print(i)


        for (i in 0..10)
            print (i)

        //para fazer regressivamente

        for (i in 10 downTo  0)
            print(i)

        for (i in 1..4 step 2)
            print(i)

        for (i in 0 until 4 )
            print(i) //irá imprimir de 0 a 3, pois exclui o ultimo membro , seria o mesmo que i in 0..3

        for (i in 0 until list.size) print(i)

        /* Range em viewGroup pegando todas suas views e adicionando para um map de views
        val views = (0 until viewGroup.childCount).map{
            viewGroup.getChildAt(it)
        }*/

        val views  = (0 until list.size).map{
            list.get(it)
        }


    }












    private val items = listOf("Mon 6/23 - Sunny - 31/17",
            "Tue 6/23 - Sunny - 31/17",
            "Wed 6/23 - Sunny - 31/17",
            "Thurs 6/23 - Sunny - 3d/17",
            "Fri 6/23 - Sunny - 332/7",
            "Sat 6/23 - Trapped in weatherstation - 32/7",
            "Sun 6/23 - Sunny - 20/07"

    )

    fun somar(x: Int, y: Int): Int = x+y

    fun toast(message: String, length: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this,message, length).show()
    }
    fun niceToast(message: String, tag:String = MainActivity::class.java.simpleName, length: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this, "[$tag]$message", length).show()
    }



    fun variableTypes(){

        //Convertendo char para int
        val c: Char = 'c'
        val i:Int = c.toInt()



        //Omitindo o tipo de variavel = pratica comum codigo mais limpo
        val i2 = 12 // int
        val iHex = 0x0f //Um int vindo de um hexadecimal literal
        val l = 3L //Long
        val d = 3.5 //Double
        val f = 3.5F //Float





        //String acessada como um array e iterada
        val s = "Example"
        val ca = s[2] //Char na posicao 2

        for (c in s){
            print(c)
        }


        //Var = Mutavel / val = imutavel - seu estado nao pode mudar após a instanciação se quiser mudança naquele objeto precisa-se criar um novo
        val actionBar = supportActionBar // Uma ActionBar no contexto de activity
        val context: Context


    }



    //Extension Functions - adiciona novo comportamente Maneira de estender classes

    //Função toast que não pede pelo contexto que pode ser usado qualquer contexto de objeto e os tipos que extends context como activity ou service
    fun Context.toastExt(message: CharSequence, duration: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this, message, duration).show()

    }


    fun View.snackShow(message: CharSequence, duration: Int = Snackbar.LENGTH_LONG){
        val snack = Snackbar.make(this, message, duration).show()
       /* snack.f()
        snack.show()*/

    }

    //Nessa fun é passado uma lambda como ultimo parametro que retorna um Unit(void) o snack irá executar essa função que é o make  e depois mostrar a si mesmo.
    //Nesse f:Snackbar devemos passar uma função, no caso iremos passar a actiond para ele ja colocar a action do snackbar
    fun View.snackShow2(message: CharSequence, duration: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit){
        val snack = Snackbar.make(this, message, duration)
         val result = snack.f()
        //Log.i("dasdasdasdas",result)
         snack.show()

    }

//No listener mandaremos uma view dentro de lambda (retornando void) com o que será feito após a action ser clicada
    fun Snackbar.snackaction(action: String, color: Int? = null, listener: (View) -> Unit){
        setAction(action, listener)
        color?.let { setActionTextColor(color) }
    }



    //Extension Properties
    public var TextView.Text: CharSequence
    get() = getText()
    set(v) = setText(v)


}
