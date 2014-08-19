package com.mlefevre.app.archiving.service;

import com.mlefevre.app.archiving.exception.ThreadingException;
import com.mlefevre.app.archiving.threading.ArchivingThread;

import java.util.List;


public interface ThreadingService {


    List<ArchivingThread> dispatch(List<String> documentIds) throws ThreadingException;


    ArchivingThread create(List<String> documentIds, String name, int startIndex, int stopIndex) throws ThreadingException;


    void execute(List<ArchivingThread> threads) throws ThreadingException;

}
