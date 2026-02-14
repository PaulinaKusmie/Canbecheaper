//package com.kuciaapp.canbecheaperkmp.model.api
//
//import com.google.gson.GsonBuilder
//import com.kuciaapp.canbecheaperkmp.utility.BooleanTypeAdapter
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import java.util.concurrent.TimeUnit
//import javax.inject.Singleton
//import kotlin.apply
//import kotlin.jvm.java
//
//@Module
//@InstallIn(SingletonComponent::class)
//object ApiClient {
//
//    private const val BASE_URL = "http://srv18.mikr.us:20230/"
//
//    @Provides
//    @Singleton
//    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
//        HttpLoggingInterceptor().apply {
//            HttpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//        }
//
//
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(
//        logging: HttpLoggingInterceptor
//    ): OkHttpClient =
//        OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .writeTimeout(30, TimeUnit.SECONDS)
//            .build()
//
//
//    val gson = GsonBuilder()
//        .serializeNulls()
//        .registerTypeAdapter(Boolean::class.java, BooleanTypeAdapter())
//        .create()
//
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(client: OkHttpClient): Retrofit =
//        Retrofit.Builder()
//            .client(client)
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//
////    @Provides
////    @Singleton
////    fun provideUserAPI(retrofit: Retrofit): UserAPI =
////        retrofit.create(UserAPI::class.java)
//
//
//
////    @Provides
////    @Singleton
////    fun provideSpecjalizationAPI(retrofit: Retrofit): SpecjalizationAPI =
////        retrofit.create(SpecjalizationAPI::class.java)
////
////
////
////    @Provides
////    @Singleton
////    fun provideExaminationUserAPI(retrofit: Retrofit): ExaminationUserAPI =
////        retrofit.create(ExaminationUserAPI::class.java)
////
////
////
////    @Provides
////    @Singleton
////    fun provideExaminationAPI(retrofit: Retrofit): ExaminationAPI =
////        retrofit.create(ExaminationAPI::class.java)
////
////
////
////    @Provides
////    @Singleton
////    fun provideReminderAPI(retrofit: Retrofit): ReminderAPI =
////        retrofit.create(ReminderAPI::class.java)
////
////
////    @Provides
////    @Singleton
////    fun provideSpecjalizationUserAPI(retrofit: Retrofit): SpecjalizationUserAPI =
////        retrofit.create(SpecjalizationUserAPI::class.java)
////
////
////    @Provides
////    @Singleton
////    fun provideVisitDateAPI(retrofit: Retrofit): VisitDateAPI =
////        retrofit.create(VisitDateAPI::class.java)
//
//
//}