package com.rousci.androidapp.widgetnote.viewPresenter.setting


/**
 * Created by rousci on 17-12-9.
 *
 * to parse string read from files
 */

val separateSymbol = "\\separate" //the symbol to separate notes

/**
 * @param string string read from files
 * after parse,notes parsed from files will insert to database
* */
fun parseFromString(string:String): List<String> {
    val symbolLength = separateSymbol.length

    /**
     * @param index the index to search string
     * @param collection the index collection that where separate locate
     * */
    tailrec fun indexIter(index:Int, collection: List<Int>): List<Int>{

        /**
         * this function compare the substring begins somewhere of string
         * with the separateSymbol
         * @param index where the compare begin from the string
         * @param s the string you want to compare with
         * */
        fun compareString(index:Int, s:String):Boolean{
            tailrec fun compareIter(i:Int): Boolean {
                if (s.length < symbolLength
                        || s[index+i] != separateSymbol[i]
                        || index >= s.length){
                    return false
                }
                else if(i >= symbolLength - 1){
                    return true
                }
                else{
                    return compareIter(i+1)
                }
            }
            return compareIter(0)
        }

        if (index == string.length - 1){
            return collection
        }
        else{
            return indexIter(index+1, if (compareString(index, string)) collection.plus(index) else collection)
        }
    }

    val separatePosition = indexIter(0, listOf())

    /**
     * return string list separated by note
     * @param i the iter used to search the separatePosition
     * @param collection the collection of strings
     * */
    tailrec fun subStringIter(i:Int, collection: List<String>):List<String>{
        if (i == separatePosition.lastIndex){
            return collection
        }
        else{
            return subStringIter(i+1, collection.plus(string.substring(separatePosition[i] + symbolLength, separatePosition[i+1])))
        }
    }

    return subStringIter(0, listOf<String>())
}

/**
 * @param stringList the string list that be parse to a single string
 * which ready to store to a file.
 * */
fun parseToString(stringList: List<String>):String{
    return separateSymbol + stringList.reduce { p1, p2 -> p1 + separateSymbol + p2} + separateSymbol
}