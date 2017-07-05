package request

import android.util.Log
import com.google.gson.Gson
import dataclasses.ForecastResult
import java.net.URL
/**
 * Created by Shido on 19/06/2016.
 */
class ForecastRequest(val zipCode: String) {


    /*Em Kotlin não é possivel criar atributos e funções estaticas
    * então precisamos criar esse companion object dentro da classe e criar suas propriedades como privadas*/
    companion object{
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/" +
                "forecast/daily?mode=json&units=metric&cnt=7"
         private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="



    }

    fun execute(): ForecastResult{
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        Log.d("JSONSTR", "Chegou aqui")
        Log.d("JSONSTR", forecastJsonStr)
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }


}