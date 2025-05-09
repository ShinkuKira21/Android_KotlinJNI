#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_cfuncs_Test_stringFromJNI(
        JNIEnv* env,
        jobject /* this */, int fragmentMode) {
    const char* str = "";

    if(fragmentMode == 0) str = "Home Fragment\nC++ Generated";
    else if(fragmentMode == 1) str = "Profile Fragment\nC++ Generated";
    else str = "Settings Fragment\nC++ Generated";

    return env->NewStringUTF(str);
}