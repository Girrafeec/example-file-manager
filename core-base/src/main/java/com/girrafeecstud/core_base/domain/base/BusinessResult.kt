/* Created by Girrafeec */

package com.girrafeecstud.core_base.domain.base

import com.girrafeecstud.core_base.base.ExceptionType

sealed class BusinessResult<out T> (
)
{
    data class Success<out R> (val data: R?): BusinessResult<R> ()
    data class Error (val businessErrorType: BusinessErrorType): BusinessResult<Nothing> ()
    data class Exception (val exceptionType: ExceptionType) : BusinessResult<Nothing> ()
}