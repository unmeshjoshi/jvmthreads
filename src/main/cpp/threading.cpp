#include <iostream>
#include <pthread.h>
#include "com_threading_Thread.h"

using namespace std;

class JavaThreadWrapper
{
private:
    JavaVM *jvm;
    jobject threadObjectRef;
    JNIEnv* attachToJvm();
public:
    JavaThreadWrapper(JNIEnv *env, jobject javaThreadObjectRef);
    void callRunMethod();
    ~JavaThreadWrapper();
};

JNIEnv* JavaThreadWrapper::attachToJvm() {
    JNIEnv *env;
    if (jvm->AttachCurrentThread((void **)&env, NULL) != 0)
    {
        std::cout << "Failed to attach" << std::endl;
    }
    return env;
}

JavaThreadWrapper::JavaThreadWrapper(JNIEnv *env, jobject javaThreadObjectRef) {
    env->GetJavaVM(&(this->jvm));
    this->threadObjectRef = env->NewGlobalRef(javaThreadObjectRef);
}

JavaThreadWrapper::~JavaThreadWrapper() {
    jvm->DetachCurrentThread(); //detach from current thread
}

void JavaThreadWrapper::callRunMethod() {
    JNIEnv *env = attachToJvm();
    jclass cls = env->GetObjectClass(threadObjectRef);
    jmethodID runId = env->GetMethodID(cls, "run", "()V");
    if (runId != nullptr) {
        env->CallVoidMethod(threadObjectRef, runId);
        
    } else {         
        cout << "No run method found in the Thread object!!" << endl;
    }

    env->DeleteGlobalRef(threadObjectRef); //delete global ref before detaching the thread.
}

void *thread_entry_point(void *args)
{
    std::cout << "Starting  thread_entry_point";
    
    JavaThreadWrapper *javaThreadWrapper = (JavaThreadWrapper*)args;
    javaThreadWrapper->callRunMethod();

    delete javaThreadWrapper;

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