package com.example.testexample1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.net.SocketException;

public class MainActivity extends AppCompatActivity {

    private lateinit var photoManager: PhotoManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Logger = Java의 Log.e()
        // Initialize logger first because Photos SDK uses it
        Logger.init(applicationContext)
        Logger.setDebugMode(BuildConfig.DEBUG)


        //
        // 헤라크로스 인파/
        //
        //
        //
        //

        // PhotoManager.initInstance 기능
        // fun initInstance(
        //      context: Context,
        //      competitionMode: CompetitionMode,
        //      competitorPackageName: String)

        // initRxErrorHandler()
        PhotoManager.initInstance(
                applicationContext,
                PhotoManager.CompetitionMode.STANDALONE,
                "<competitor app package name>")

        photoManager = PhotoManager

    }

    private fun initRxErrorHandler() {
        RxJavaPlugins.setErrorHandler(Consumer { exception ->
                var e: Throwable? = exception
            if (e is UndeliverableException) {
                e = e.cause
            }
            if (e is OnErrorNotImplementedException) {
                // error received by observer that does not provide onError handler
                if (e.cause != null) {
                    e = e.cause
                }
            }
            if (e == IOException || e == SocketException) {
                // fine, irrelevant network problem or API that throws on cancellation
//                Logger.main().i("RxErrorHandler", "Exception caught", e)
                return@Consumer
            }
            if (e is InterruptedException) {
                // fine, some blocking code was interrupted by a dispose call
//                Logger.main().i("RxErrorHandler", "Exception caught", e)
                return@Consumer
            }
            if (e is NullPointerException
                || e is IllegalArgumentException
                    || e is UnsupportedOperationException) {
                // that's likely a bug in the application
//                Logger.main().e("RxErrorHandler", "Exception caught", e)
                Thread.currentThread().uncaughtExceptionHandler
                        .uncaughtException(Thread.currentThread(), e)
                return@Consumer
            }
            if (e is IllegalStateException) {
//                Logger.main().e("RxErrorHandler", "Exception caught", e)
                // that's a bug in RxJava or in a custom operator
                Thread.currentThread().uncaughtExceptionHandler
                        .uncaughtException(Thread.currentThread(), e)
                return@Consumer
            }
//            Logger.main().w("RxErrorHandler", "Undeliverable exception received, not sure what to do", e)
        })
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
}