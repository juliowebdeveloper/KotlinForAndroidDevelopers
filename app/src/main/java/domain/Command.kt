package domain

/**
 * Created by Shido on 19/06/2016.
 */


public interface Command<T> {

//Esse command executará uma ação e retornará um objeto de uma classe especifica
    //Em Kotlin todas as funções retornam algo
    fun execute(): T


}