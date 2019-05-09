#include <stdlib.h>
#include <MainActivity.h>

static const char *const messages[] = {
        "Hello, world!",
        "Bonjour, monde!",
        "Hallo Welt!"
};

JNIEXPORT jstring JNICALL
Java_com_suicide_everandom_MainActivity_msg(JNIEnv *env, jobject obj)
{
        int i;

        i = rand() % (sizeof(messages) / sizeof(messages[0]));

        return (*env)->NewStringUTF(env, messages[i]);
}
