package com.example.pspagendaatr;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContactsApplication extends Application {

    private static final int NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors();

    public static final ExecutorService threadExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

}
