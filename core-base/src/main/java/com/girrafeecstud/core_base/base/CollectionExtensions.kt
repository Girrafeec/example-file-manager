/* Created by Girrafeec */

package com.girrafeecstud.core_base.base

// method for sorting list of objects that have name property
fun <T> List<T>.sortByName(getName: (T) -> String): List<T> {
    if (size <= 1)
        return this

    val middle = size / 2
    val left = subList(0, middle).sortByName(getName)
    val right = subList(middle, size).sortByName(getName)

    return merge(left, right, getName)
}

private fun <T> merge(left: List<T>, right: List<T>, getName: (T) -> String): List<T> {
    var leftIndex = 0
    var rightIndex = 0
    val result = mutableListOf<T>()

    while (leftIndex < left.size && rightIndex < right.size) {
        val leftName = getName(left[leftIndex])
        val rightName = getName(right[rightIndex])

        if (leftName < rightName) {
            result.add(left[leftIndex])
            leftIndex++
        } else {
            result.add(right[rightIndex])
            rightIndex++
        }
    }

    result.addAll(left.subList(leftIndex, left.size))
    result.addAll(right.subList(rightIndex, right.size))

    return  result
}

// method for sorting list of objects that have long number or size property
fun <T> List<T>.sortByNumberUp(getNumber: (T) -> Long): List<T> {
    if (size <= 1)
        return this

    val middle = size / 2
    val left = subList(0, middle).sortByNumberUp(getNumber)
    val right = subList(middle, size).sortByNumberUp(getNumber)

    return mergeUp(left, right, getNumber)
}

private fun <T> mergeUp(left: List<T>, right: List<T>, getNumber: (T) -> Long): List<T> {
    var leftIndex = 0
    var rightIndex = 0
    val result = mutableListOf<T>()

    while (leftIndex < left.size && rightIndex < right.size) {
        val leftNumber = getNumber(left[leftIndex])
        val rightNumber = getNumber(right[rightIndex])

        if (leftNumber < rightNumber) {
            result.add(left[leftIndex])
            leftIndex++
        } else {
            result.add(right[rightIndex])
            rightIndex++
        }
    }

    result.addAll(left.subList(leftIndex, left.size))
    result.addAll(right.subList(rightIndex, right.size))

    return  result
}

// method for sorting list of objects that have long number or size property
fun <T> List<T>.sortByNumberDown(getNumber: (T) -> Long): List<T> {
    if (size <= 1)
        return this

    val middle = size / 2
    val left = subList(0, middle).sortByNumberDown(getNumber)
    val right = subList(middle, size).sortByNumberDown(getNumber)

    return mergeDown(left, right, getNumber)
}

private fun <T> mergeDown(left: List<T>, right: List<T>, getNumber: (T) -> Long): List<T> {
    var leftIndex = 0
    var rightIndex = 0
    val result = mutableListOf<T>()

    while (leftIndex < left.size && rightIndex < right.size) {
        val leftNumber = getNumber(left[leftIndex])
        val rightNumber = getNumber(right[rightIndex])

        if (leftNumber > rightNumber) {
            result.add(left[leftIndex])
            leftIndex++
        } else {
            result.add(right[rightIndex])
            rightIndex++
        }
    }

    result.addAll(left.subList(leftIndex, left.size))
    result.addAll(right.subList(rightIndex, right.size))

    return  result
}