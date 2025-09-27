package com.example.sautiyamilimani.domain.model
sealed class Resource<out T>{
    object Loading: Resource<Nothing>()

    data class Success<out T>(val data:T?): Resource<T>()
    data class Failure(val message:String?=null,val throwable: Throwable?=null): Resource<Nothing>()
}

//out in generic types allows use of specific(subclasses) of a class to be used where a more general class is needed.Bassically saying allow all subsets of this type
//since unit is a subset of any,it will be accepted,without out,unit and any would be treated as differnt classes and we wont be able to use unit as an instance of the expected class tpye

//out T makes your Resource covariant.
//
//Resource.Success<out T> allows any subtype of T (like Unit) to be used where a more general type is expected (like Any).
//
//Resource.Loading and Resource.Failure use Nothing, which is compatible with all types because Nothing is the bottom type in Kotlin.

//1. What “covariant” means
//
//Covariance is about type compatibility in generics.
//
//When a generic class is covariant in T, it means you can use a more specific type wherever a more general type is expected.
//
//In Kotlin, covariance is declared with out:
//
//class Box<out T>(val value: T)
//
//
//This means:
//
//Box<Cat> can be used wherever Box<Animal> is expected, because Cat is a subtype of Animal.

