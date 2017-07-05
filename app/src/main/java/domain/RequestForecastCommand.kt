package domain

import request.ForecastRequest

/**
 * Created by Shido on 19/06/2016.
*/
class RequestForecastCommand (val zipCode: String) : Command<ForecastList> {

    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }


}
