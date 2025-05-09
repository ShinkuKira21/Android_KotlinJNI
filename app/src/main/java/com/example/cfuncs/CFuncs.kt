package com.example.cfuncs

class Test {
    /**
     * A native method that is implemented by the 'testapplicationc' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(fragmentMode: Int): String

    companion object {
        // Used to load the 'cfuncs' library on application startup.
        init {
            System.loadLibrary("cfuncs")
        }
    }
}