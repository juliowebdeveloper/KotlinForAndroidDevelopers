package delegates

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by Shido on 31/08/2016.
 */
private class NotNullSingleValueVar<T>(): ReadWriteProperty<Any?, T> {

    private var value: T? = null


    //O getter vai retornar um valor se estiver atribuido, se não vai joga exception
    override fun getValue(thisRef: Any?, property: KProperty<*>): T{
       return value?: throw IllegalStateException("not initialized")
    }

    //o setter vai atribuir um valor se estiver nulo, se não vai jogar exception
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T){
        this.value = if (this.value == null) value
        else throw IllegalStateException("Already Initialized")
    }

    object  DelegatesExt{
        fun <T> notNullSingleValue(): ReadWriteProperty<Any?, T> = NotNullSingleValueVar()
    }

}