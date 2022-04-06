
fun main(){


    // 1. Time Complexity
    // is the measurement of the time required to run an algorithm as the input size increases

    // 2. Constant time
    // When a function the same amount of time indistinct of the input size
    // (takes the same number of steps/operations no matter the size of input)
    // then its time complexity is 'Constant Time' and is denoted by 'O(1)'

    fun checkFirst(names: List<String>){
        if(names.firstOrNull() != null){
            println(names.first())
        } else {
            println("no names")
        }
    }

    // these 2 take the same time
    checkFirst(listOf("Name 1", "Name 2", "Name 3"))
    checkFirst(listOf("Name 1", "Name 2", "Name 3", "Name 4", "Name 5"))


    // 3. Linear time
    // When the input data increases the time increases by the same amount
    // usually this happens when there is just one or more sequential (not nested) iterations  depending on the input data
    // this time complexity is denoted by 'O(n)'
    // if there are lets say 3 sequential iterations you don't write 'O(3n)', only 'O(n)'
    // Constants are dropped

    fun printNames(names: List<String>){
        for(name in names){
            println(name)
        }
    }

    // the first one takes less time than the second
    printNames(listOf("Name 1", "Name 2", "Name 3"))
    printNames(listOf("Name 1", "Name 2", "Name 3", "Name 4", "Name 5"))

    // 4. Quadratic Time
    // When the algorithm takes time proportional to the square of the input size
    // we say its time complexity is quadratic and is denoted by 'O(n^2)'
    // this happens when you 2 nested iterations depending on the input data
    // we say 'depending on the input data' because the input might not be explicitly a collection

    fun multiplicationTable(size: Int){
        for(number in 1..size){
            print(" | ")
            for(otherNumber in 1..size){
                print("$number * $otherNumber = ${number*otherNumber} | ")
            }
            println()
        }
    }

    // this will iterate 2 times, 2 times
    multiplicationTable(2)

    // 5. Logarithmic Time
    // When the time your function will take gets halved at each step
    // You have a logarithmic complexity, and it is denoted by 'O(log n)'
    // for example, if you want to find an integer in a sorted list of integers,
    // you just need to look at the middle and compare if that number is larger or smaller that the one you want
    // then you take the left or right half and repeat the process until you find your number


    fun pseudoBinaryContains(value: Int, numbers: List<Int>) : Boolean{
        if(numbers.isEmpty()){
            return false
        }

        if(numbers.size == 1){
            return numbers[0] == value
        }

        val middleIndex = numbers.size / 2

        if(numbers[middleIndex] == value){
            return true
        }

        return if(numbers[middleIndex] > value){
            pseudoBinaryContains(value, numbers.slice(0 ..middleIndex))
        } else {
            pseudoBinaryContains(value, numbers.slice(middleIndex  until numbers.size))
        }
    }

    print(pseudoBinaryContains(12, listOf(0,1,2,3,4,5,6,7,8,9)))


    // 6. Quasilinear Time
    // is not as good as linear but dramatically better thant quadratic
    // is denoted by 'O(n log n)
    // there are some sort methods, including Kotlin's sort() that falls into this category
    // is one of the most common time complexities in sorting and algorithms


    // 7. Space Complexity
    // if you don't require allocating extra copies of data or create them based on the intput data size
    // then your space complexity is constant 'O(n)'
    // if you need to allocate more variables directly related to the size of you input then you have 'O(n)' space complexity

    fun printSorted(numbers: List<Int>){
        val sorted = numbers.sorted()
        for(element in sorted){
            println(element)
        }
    }

    // this will generate a collection of size n
    // then this is 'O(n)' space complexity
    printSorted(listOf(0,1,2,3,4,5,6,7,8,9))

}
