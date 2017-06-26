package com.fileintersend.view.service;

import java.io.File;

/**
 * Created by axnshy on 2017/6/25.
 */

interface ISendService{


    void send(String path);

    File accept();

    void writeToDisk(File file,String dictionary);

    void closeClientSocket();

    void closeServerSocket();
}
