package adapter

import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.ui.utils.ctx
import domain.Forecast
import domain.ForecastList
import julio.com.br.kotlin3.R
import kotlinx.android.synthetic.main.item_forecast.*
import org.jetbrains.anko.find
import kotlinx.android.synthetic.main.item_forecast.view.*
/**
 * Created by Shido on 09/06/2016.
 */
//Criando a classe com o construtor inicial sendo uma lista de strings e retornando um RecyclerView Adapter com o view holder
class ForecastListAdapter(val weekForecast: ForecastList, val itemClick: (Forecast)->Unit): RecyclerView.Adapter<ForecastListAdapter.ViewHolder>(){


    //Click Listener do adapter

//Adicionando lambda com uma function que irá receber um Forecast e irá retornar nada (Unit)
    //mudando o val itemClick: ForecastListAdapter.OnItemClickListener  por (Forecast) -> Unit
   /* interface OnItemClickListener{
        operator fun invoke(forecast: Forecast)
    }*/


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
            val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false);
        return ViewHolder(view, itemClick)


         }


     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         //function with:
         //Recebe um objeto e uma função extendia como parametrose faz o objeto executar a função
         //Todo o codigo dentro das chaves agem como uma função extendidado objeto que especificamos como parametro
         //E pode usar todas suas funções publicas e propriedades - simplifica o codigo
        /* with(weekForecast.dailyForecast[position]) {
            holder.textView.text = "$date - $description - $high/$low"
            // holder.data1.text = "${weekForecast.country}"
             }*/


         //Por ter criado o operator func na ForecastList, pode-se simplificar aqui já que lá já tem um operador que retorna o item da lista, recebendo a position
        // with(weekForecast[position]) {
            // holder.textView.text = "$date - $description - $high/$low"
             // holder.data1.text = "${weekForecast.country}"
        // }


         //Tudo acima foi mudado, o bind está por conta do viewholder agora
         holder.bindForecast(weekForecast[position])




         }


    //override fun getItemCount(): Int = weekForecast.dailyForecast.size

    override  fun getItemCount(): Int = weekForecast.size()

    class ViewHolder(view: View, val itemClick:(Forecast) -> Unit) : RecyclerView.ViewHolder(view){
        //pegando as propriedades do item_forecast layout igual no java
       /* private val iconView: ImageView
        private val dateView: TextView
        private val descriptionView: TextView
        private val maxTemperatureView: TextView
        private val minTemperatureView: TextView
        init{
            iconView = view.find(R.id.icon)
            dateView = view.find(R.id.date)
            descriptionView = view.find(R.id.description)
            maxTemperatureView = view.find(R.id.maxTemperature)
            minTemperatureView = view.find(R.id.minTemperature)

        }*/
        fun bindForecast(forecast: Forecast){

            with(forecast){
                //itemView = Da RecyclerView

                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text = date
                itemView.description.text = description
                itemView.maxTemperature.text = "${high.toString()}"
                itemView.minTemperature.text = "${low.toString()}"
                //Setando o clickable a partir da interface
                itemView.setOnClickListener { itemClick(this) }
               // itemView.setOnClickListener { itemClick.invoke(this) }

            }

        }


    }



















/*
    //Passando o contexto do ViewGround
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       // val v: View = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)



       return ViewHolder(TextView(parent.context))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position]
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)

*/






}