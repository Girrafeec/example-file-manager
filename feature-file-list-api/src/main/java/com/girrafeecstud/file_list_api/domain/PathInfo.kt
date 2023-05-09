/* Created by Girrafeec */

package com.girrafeecstud.file_list_api.domain

data class PathInfo(
    var isPathActive: Boolean,
    val path: String,
    val name: String
)