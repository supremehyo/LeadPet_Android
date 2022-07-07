//package com.dev6.data.network
//
//import com.dev6.data.di.NetworkModule
//import dagger.Module
//import dagger.hilt.components.SingletonComponent
//import dagger.hilt.testing.TestInstallIn
//import okhttp3.HttpUrl
//import okhttp3.mockwebserver.MockWebServer
//
//@Module
//@TestInstallIn(
//    components = [SingletonComponent::class],
//    replaces = [NetworkModule::class]
//)
//class MockNetworkModule : NetworkModule() {
//
//    override fun baseUrl(): HttpUrl {
//        return Mockserver.server.url("http://localhost/")
//    }
//}