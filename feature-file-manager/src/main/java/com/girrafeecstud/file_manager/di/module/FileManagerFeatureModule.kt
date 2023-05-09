/* Created by Girrafeec */

package com.girrafeecstud.file_manager.di.module

import com.girrafeecstud.file_manager.di.FileManagerDashboardComponent
import dagger.Module

@Module(
    subcomponents = [FileManagerDashboardComponent::class]
)
interface FileManagerFeatureModule {
}