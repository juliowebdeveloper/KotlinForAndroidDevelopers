package model

/**
 * Created by Shido on 09/06/2016.
 */
class Person {

    var name: String = ""
    get() = field.toUpperCase()


    //Gets e sets customizados


    var desc: String = ""
    get() = field.toUpperCase()
    set(value) {field = "Descrição: $value"}


}