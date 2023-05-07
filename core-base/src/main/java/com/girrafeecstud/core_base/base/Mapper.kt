/* Created by Girrafeec */

package com.girrafeecstud.core_base.base

interface Mapper<in I, out O> {
    fun map(input: I): O
}