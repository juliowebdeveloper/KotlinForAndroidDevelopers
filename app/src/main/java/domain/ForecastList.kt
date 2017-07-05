package domain

import com.squareup.moshi.Json
import domain.Forecast

/**
 * Created by Shido on 19/06/2016.
 */
data class ForecastList( val city: String, val country: String,
                        val dailyForecast:List<Forecast>){


    //operator fun que já retornará o objeto forecast a partir da posição que receberá como parametro - simplifica o onBindViewHolder um pouco
    operator fun get(position: Int): Forecast = dailyForecast[position]


    fun size(): Int = dailyForecast.size

    //pode ser escrito sem inferir o tipo caso o compilador possa computa-lo
    fun size1() = dailyForecast.size

}