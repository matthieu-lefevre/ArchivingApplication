package com.mlefevre.app.archiving.threading;

public interface NotifyingThreadListener<T extends NotifyingThread> {

    void onStart(final T thread);

    void onComplete(final T thread);

    <U extends Exception> void onFailure(T thread, U exception);

}
