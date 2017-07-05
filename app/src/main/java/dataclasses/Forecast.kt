package dataclasses

import java.util.*

/**
 * Created by Shido on 10/06/2016.
 */

//Data classes substituem os POJOs em Java
data class Forecast(val dt: Long, val temp: Temperature, val pressure: Float,
                    val humidity: Int, val weather: List<Weather>, val speed: Float, val deg: Int, val clouds: Int, val rain: Float) {


    /*Data class ja tras os metodos :
    equals()
    hashcode()
    copy()

    * */


}