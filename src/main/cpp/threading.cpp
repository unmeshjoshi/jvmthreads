//============================================================================
// Name        : threading.cpp
// Author      :
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <pthread.h>
#include "com_threading_Thread.h"
using namespace std;

struct JavaCallerContext
{
    JavaVM *jvm;
    jobject o;
};

void *thread_entry_point(void *context)
{
    std::cout << "Starting  thread_entry_point";
    
    JavaCallerContext *caller = (JavaCallerContext *)context;

    JNIEnv *g_env;
    JavaVM *g_vm = caller->jvm;
    if (g_vm->AttachCurrentThread((void **)&g_env, NULL) != 0)
    {
        std::cout << "Failed to attach" << std::endl;
    }

    std::cout << "Getting class for " << caller->o;
    jclass cls = g_env->GetObjectClass(caller->o);


    std::cout << "Calling run method on Thread object" << caller->o;
    jmethodID runId = g_env->GetMethodID(cls, "run", "()V");
    if (runId == nullptr)
        cout << "No run method !!" << endl;
    else
        g_env->CallVoidMethod(caller->o, runId);

    g_vm->DetachCurrentThread();
    
    delete caller;

    return NULL;
}

JNIEXPORT void JNICALL Java_com_threading_Thread_start0(JNIEnv *env, jobject o)
{


    JavaVM *jvm;
    env->GetJavaVM(&jvm);

    JavaCallerContext *caller = new JavaCallerContext();
    caller->jvm = jvm;
    caller->o = env->NewGlobalRef(o);


    pthread_attr_t attr;
    pthread_attr_init(&attr);
    pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_DETACHED);
    
    pthread_t javaThread;
    if (pthread_create(&javaThread, &attr, thread_entry_point, caller))
    {
        fprintf(stderr, "Error creating thread\n");
        return;
    }

    printf("Started a linux thread successfully!\n");
    return;
}