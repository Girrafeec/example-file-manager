/* Created by Girrafeec */

class Dependencies {

    object Jetpack {

        object Compose {
            const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}";
            const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
            const val composeUi = "androidx.compose.ui:ui"
            const val composeMaterial = "androidx.compose.material:material"
            const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
            const val composeUiTooling = "androidx.compose.ui:ui-tooling"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.jetapackLifecycleVersion}"
            const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.androidxHilt}"
            const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.jetpackNavigationComponentVersion}"
        }

        object ViewModel {
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.jetapackLifecycleVersion}"
        }

        object LiveData {
            const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.jetapackLifecycleVersion}"
        }

        object LifecycleService {
            const val lifecycleService = "androidx.lifecycle:lifecycle-service:${Versions.jetapackLifecycleVersion}"
        }

        object Navigation {
            const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.jetpackNavigationComponentVersion}"
            const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.jetpackNavigationComponentVersion}"
        }

        object Room {
            const val room = "androidx.room:room-runtime:${Versions.roomVersion}"
            const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
            const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
        }

    }

    object Google {

        object Material {
            const val material = "com.google.android.material:material:${Versions.materialVersion}"
        }

        object GMS {
            const val gmsMaps = "com.google.android.gms:play-services-maps${Versions.gmsMaps}"
            const val gmsLocation = "com.google.android.gms:play-services-location:${Versions.gmsLocation}"
        }

    }

    object AndroidX {

        object Core {
            const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
        }

        object Espresso {
            const val espressoCore =
                "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
        }

        object Constraintlayout {
            const val constraintLayout =
                "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
        }

        object AppCompat {
            const val appCompat =
                "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
        }

    }

    object Dagger {
        const val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"

        object Hilt {
            const val hilt = "com.google.dagger:hilt-android:${Versions.daggerVersion}"
            const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.daggerVersion}"
            // deprecated
//            const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.androidxHiltViewModel}"
            const val androidxHiltCompiler = "androidx.hilt:hilt-compiler:${Versions.androidxHilt}"
        }

    }

    object Coroutines {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}"
    }

    object jUnit {
        const val jUnit = "junit:junit:${Versions.jUnitVersion}"
        const val jUnitAndroidTest = "androidx.test.ext:junit:${Versions.jUnitAndroidxExtVersion}"
    }

    object Kaspresso {

    }

    object Mockito {
        const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.kotlinMockitoVersion}"
        const val mockitoInline = "org.mockito:mockito-inline:${Versions.kotlinMockitoVersion}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
        const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
        const val retrofitConverterScalars = "com.squareup.retrofit2:converter-scalars:${Versions.retrofitVersion}"
    }

    object OkHttp3 {
        const val okHttp3 = "com.squareup.okhttp3:okhttp:${Versions.okHttp3Version}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp3Version}"
    }

    object EasyPermissions {
        const val easyPermissions = "pub.devrel:easypermissions:${Versions.easyPermissionsVersion}"
    }

    object OSMDroid {
        const val osmDroid = "org.osmdroid:osmdroid-android:${Versions.osmDroid}"
        const val osmDroidBonuspack = "com.github.MKergall:osmbonuspack:${Versions.osmDroidBonuspack}"
    }

    object Picasso {
        const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }

    object Firebase {
        const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"

        const val firebaseMessaging = "com.google.firebase:firebase-messaging"

        const val firebaseAnalytics = "com.google.firebase:firebase-analytics"
    }

    object SharedPreferences {
        const val shafranSharedPreferencesMock = "io.github.ivanshafran:shared-preferences-mock:${Versions.shafranSharedPreferencesMock}"
    }

}