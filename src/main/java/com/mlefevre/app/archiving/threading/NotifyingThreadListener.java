package com.mlefevre.app.archiving.threading;

public interface NotifyingThreadListener {

    void onStart(final NotifyingThread thread);

    void onComplete(final NotifyingThread thread);

    void onFailure(final NotifyingThread thread);

}
