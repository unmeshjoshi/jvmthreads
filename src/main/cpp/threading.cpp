#include <iostream>
#include <pthread.h>
#include "com_threading_Thread.h"

using namespace std;

class JavaThreadWrapper
{
private:
    JavaVM *jvm;
    jobject threadObjectRef;
    JNIEnv *env;
public:
    JavaThreadWrapper(JNIEnv *env, jobject javaThreadObjectRef);
    void attachToJvm();
    void callRunMethod();
    ~JavaThreadWrapper();
};

void JavaThreadWrapper::attachToJvm() {
    if (jvm->AttachCurrentThread((void **)&this->env, NULL) != 0)
    {
        std::cout << "Failed to attach" << std::endl;
    }
}

JavaThreadWrapper::JavaThreadWrapper(JNIEnv *env, jobject javaThreadObjectRef) {
    env->GetJavaVM(&(this->jvm));
    this->threadObjectRef = env->NewGlobalRef(javaThreadObjectRef);
}

JavaThreadWrapper::~JavaThreadWrapper() {
    env->DeleteGlobalRef(threadObjectRef); //delete global ref before detaching the thread.
    jvm->DetachCurrentThread(); //detach from current thread
}

void JavaThreadWrapper::callRunMethod() {
    jclass cls = env->GetObjectClass(threadObjectRef);
    jmethodID runId = env->GetMethodID(cls, "run", "()V");

    if (runId == nullptr)
        cout << "No run method !!" << endl;
    else
        env->CallVoidMethod(threadObjectRef, runId);
}

void *thread_entry_point(void *args)
{
    std::cout << "Starting  thread_entry_point";
    
    JavaThreadWrapper *javaThread = (JavaThreadWrapper*)args;
    javaThread->attachToJvm();
    javaThread->callRunMethod();

    delete javaThread;

    return NULL;
}

JNIEXPORT void JNICALL Java_com_threading_Thread_start0(JNIEnv *env, jobject javaThreadObjectRef)
{
    //Get jvm instance and global reference to Thread java object to be passed to
    //pthread entry point function.
    JavaThreadWrapper* args = new JavaThreadWrapper(env, javaThreadObjectRef);

    //init thread attributes
    pthread_attr_t attr;
    pthread_attr_init(&attr);
    pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_DETACHED);
    
    //native thread id
    pthread_t tid;
    if (pthread_create(&tid, &attr, thread_entry_point, args))
    {
        fprintf(stderr, "Error creating thread\n");
        return;
    }

    std::cout << "Started a linux thread " << tid << "!" << endl;
    return;
}