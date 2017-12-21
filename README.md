# AndroBoum
# Si le graddle sont pas bon
   
   apply plugin: 'com.android.application'

   android {
   compileSdkVersion 26
    defaultConfig {
        applicationId "com.example.leman.androboum"
        minSdkVersion 23
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation 'com.google.firebase:firebase-auth:11.0.4'
    compile fileTree(dir: 'libs', include: ['*.jar'])
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    compile 'com.android.support:appcompat-v7:26.1.0'
    compile 'com.android.support.constraint:constraint-layout:1.0.2'
    testCompile 'junit:junit:4.12'
    compile 'com.google.firebase:firebase-auth:11.0.4'
    compile 'com.firebaseui:firebase-ui-auth:2.0.1'
    compile 'com.facebook.android:facebook-android-sdk:4.22.1'

    compile "com.google.android.gms:play-services-auth:11.0.4"

    compile 'com.google.firebase:firebase-storage:11.0.4'
    compile 'com.firebaseui:firebase-ui-storage:2.0.1'
    compile 'com.google.firebase:firebase-database:11.0.4'
    compile 'com.google.android.gms:play-services-location:11.0.4'
    implementation 'com.google.android.gms:play-services-maps:11.0.4'
}
