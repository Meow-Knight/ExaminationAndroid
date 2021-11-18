package com.example.tcpexamination.utils;


import android.util.Log;

import com.example.tcpexamination.common.SocketRequestType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import entity.Account;
import entity.Examination;

public class DateUtil {

    private DateUtil() {
    }

    public static String formatDate(Timestamp timestamp) {
        return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timestamp);
    }
}
