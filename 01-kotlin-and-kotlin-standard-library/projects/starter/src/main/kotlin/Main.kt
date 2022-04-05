/*
 * Copyright (c) 2021 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

fun main() {


    // 1. Variables and Types
    // In kotlin there are 2 types of variables, 'val' and 'var'

    val name = "Bill Clinton" // not reassignable
    var country = "Romania" // reassignable

    // Compile error
    // name = "Matei Suica"

    country = "Pakistan"

    // You can think of 'var' as a 'variable' and 'val' as a 'value'

    // Numbers -> Double, Float, Long, Int, Short, Byte
    // Characters -> Char, String
    // Other -> Boolean, Array


    // 2. Null safety
    // In kotlin you can assign null to a variable whenever you want to signal that it doesn't have a value

    var car: Car? = null

    car = Car("Mercedes-Benz")
    println("This is a $car")

    car = null
    println("This is nothing: $car")

    // the problem is that you might want to use some function or attribute of a nullable variable in any given moment
    // this might cause a NPE (null pointer exception)
    // you can use the safe-call operator '?' to prevent the NPE and execute the following statement only if
    // such variable is not null

    car?.drive()

    car = Car("Nissan")

    car?.drive()

    // You can use the Elvis operator '?:' as a fallback in case that the variable is null

    car = null
    val realCar: Car = car ?: Car("Porsche")
    realCar.drive()

    // If you want to enforce an exception in some point because a var should not be null at some point
    // you can use the not-null assertion operator '!!' to throw a NPE

    realCar!!.drive()


    // 3. Conditional statements
    // Kotlin uses the same well-known conditional blocks of many other languages
    // if-else, if-elseif-else
    // instead of a 'switch' block , Kotlin uses a 'when' block

    val groupSize = 3

    when(groupSize){

        1 -> println("Single")
        2 -> println("Pair")
        3 -> {
            println("Trio")
        }
        else -> {
            println("This is the same as 'default' in Java")
        }
    }

    // 4. Loops
    // Kotlin uses the same loop blocks of many other languages
    // for, while, do-while...
    // but it also includes a 'repeat' block

    repeat(10){
        println("This is cool!")
    }


    // 5. Functions.
    // Functions are small units of code that can be abstracted and reused
    // they define 'parameters' to fulfill, and get called providing 'arguments'
    // they can be pure (always return the same result for the same input)
    // or not-pure and return or not values

    printMax(1, 2)

    // 6. Generics.
    // Generics are a great way to abstract your code whenever you can manipulate multiple types in the same way

    val box = Box<Int>()

    box.put(4)

    val boolBox = Box<Boolean>()
    boolBox.put(true)
    println(boolBox.isEmpty())

    // 7. The Kotlin Standard Library
    // You have a lot of classes and functions out of the box with Kotlin for
    // text manipulation, math, streams, multithreading, annotations, collections and more

    // 8. Package Kotlin
    // Contains many helpful high-order functions and rge most basic classes , Any, Int, Array, Exception
    // the most interesting thing in this package are the scoping functions 'let', 'also', 'run', 'with', 'apply'

    // 9. Let
    // 'let' helps you with null-checks and creates a new local scope with the instance you called it on
    // is passed as 'it'. 'let' is a transformational function where the evaluation
    // of the last sentence of the block is returned
    car = Car("Mazda")

    fun printCar1(car: Car?){
        val isCoupe = car?.let{
            it.doors <= 2
        }

        if(isCoupe == true){
            println("coupes are awesome")
        }
    }
    printCar1(car)

    // 10. Run
    // 'run' is similar to 'let', but it provides the target instance as 'this'
    // and isolates the block from outer scope
    fun printCar2(car: Car?){
        val isCoupe = car?.run{
            // this is now isolated, it won't refer the enclosing class
            // but the object where run is called
            this.doors <= 2
        }

        if(isCoupe == true){
            println("coupes are awesome")
        }
    }
    printCar2(car)

    // 11. Also
    // 'also' is not transformational
    // it will return the target instance, with all the applied modifications inside the scope
    // the target instance is passed as 'it' as in 'let'
    // so, you can chain more operations because the instance is always returned at the end

    fun printCar3(car: Car?){
         car?.also{
            // this is now isolated, it won't refer the enclosing class
            // but the object where run is called
            it.doors <= 2
         }.let{
             if(it?.doors != null && it.doors <= 2){
                 println("coupes are awesome")
             }
         }


        val thisIsTheSameCar = car?.also{
            it.stereo = true
        }

        println(thisIsTheSameCar)
        println(car)
    }

    printCar3(car)

    // 12. Apply
    // This is for 'also' what 'run' is for 'let'
    // creates an isolated scope and the target instance is passed as 'this'
    // as 'also' is not transformational and returns the instance

    fun printCar4(car: Car?){
        car?.apply{
            // this is now isolated, it won't refer the enclosing class
            // but the object where run is called
            this.doors <= 2
        }.let{
            if(it?.doors != null && it.doors <= 2){
                println("coupes are awesome")
            }
        }


        val thisIsTheSameCar = car?.apply{
            this.stereo = false
        }

        println(thisIsTheSameCar)
        println(car)
    }

    printCar4(car)

    // 13. List
    // The second most important package in the Standard Library is 'kotlin.collections'
    // A list is a generic container (it holds an indicated generic type)
    // it implements 'Iterable' (so you can iterate/traverse the instance at least once)
    // and extends 'Collection' (this means you can traverse the instance in a not destructive manner,
    // some other implementations consumes/extracts the values when traversing the instance)
    // lists are not mutable, if you are talking of adding or removing elements to a list,
    // then you are talking of a mutableList

    val places = listOf("Paris", "London", "Bucharest")

    // places.add("This won't work")

    val mutablePlaces = mutableListOf("Paris", "London", "Bucharest")
    mutablePlaces.add("Mexico")

    // Lists are ordered, (ordered doesn't mean sorted) they have a zero-base index
    // that order won't change unless the collection is modified
    
    println(places[0]) // always returns Paris until modified
    
    // Lists provide random-access, you can directly access any element at any time by knowing its index.
    // other structures need to traverse previous elements until finding the one desired like trees and linked-lists
    // This provides 'constant time access; O(1)'
    
    // 14. Lists performance
    // Adding an element to the end of the list is also a constant time operation
    // Adding an element to at specific location will take time proportional to the length of the list
    // because it means the elements must be moved back one at a time starting from the new element location
    
    mutablePlaces.add("New York") // constant time, it also has a 'capacity' workaround under the hood
    mutablePlaces.add(0, "Kiev") // will take 'n' steps to move all the elements where 'n' = list.size
    
    // 15. Map
    // it is also a generic container
    // There is no restriction to the Key type, but be aware that the hashCode() function is used to identify 
    // each key as unique so be careful when using your custom types as keys
    // Maps are unordered, you can obtain a collection of keys to traverse the map
    // but this collection will change when the map is modified
    
    val scores = mutableMapOf(
        "Eric" to 9,
        "Mark" to 12,
        "Wayne" to 1)
    
    // maps offer constant time in adding and accessing elements
    scores["Andrew"] = 0
    
    println(scores)

    // 16. Mutable vs Read-Only
    // List and Maps are Read-Only (immutable) you cannot modify them
    // they are used for safety i.e. as the result of api responses, database query results, etc...
    // MutableLists and MutableMaps are mor general purpose and are used to generate more values from them
    
}

class Car(val brand: String){

    var doors = 2
    var stereo = false

    fun drive(){
        println("$brand is being driven")
    }

    override fun toString(): String {
        return "Car(brand='$brand', doors=$doors, stereo=$stereo)"
    }


}

// A pure function that returns the max number among two numbers
fun max(a: Int, b: Int): Int{
    return if (a > b) a else b
}

// A pure function that doesn't return anything
fun printMax(a:Int, b: Int){
    val maxValue = max(a,b)
    println(maxValue)
}


// You use a Generic Type '<T>' in the declaration instead of using 'Any'
// Because all types are subtypes of 'Any' so you will lose precision about what can you do with the content of your Box
// Using a generic you can be more specialized in case you have a Box<Int>, Box<String>, Box<Car> etc...

class Box<T>{
    var content: T? = null

    fun put(content: T){
        this.content = content
    }

    fun retrieve(): T?{
        return content
    }

    fun isEmpty():Boolean{
        return content == null
    }
}

