package com.example.tcpexamination.utils;


import android.util.Log;

import com.example.tcpexamination.common.SocketRequestType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import entity.Account;
import entity.Examination;

public class SocketUtil {
    private static final String SERVER_ADDRESS = "192.168.43.6";
    private static final int SERVER_PORT = 8989;
    private Socket socket;
    private static SocketUtil instance;
    private ObjectInputStream din;
    private ObjectOutputStream dos;

    private SocketUtil() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            dos = new ObjectOutputStream(socket.getOutputStream());
            din = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SocketUtil getInstance() {
        if (instance == null){
            instance = new SocketUtil();
        }
        return instance;
    }

    public static SocketRequestType getSocketRequestType(String request) {
        for (SocketRequestType socketRequestType : SocketRequestType.values()) {
            if (socketRequestType.getName().equals(request)) {
                return socketRequestType;
            }
        }

        return null;
    }

    public void asyncAccount(Account account) {
        try {
            dos.writeUTF(SocketRequestType.ASYNC_ACCOUNT.getName());
//            dos.flush();
            dos.writeObject(account);
//            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Examination[] getAllExaminations() {
        Examination[] examinations = new Examination[0];
        try {
            Log.d("hehe", "writing...");
            dos.writeUTF(SocketRequestType.GET_EXAMINATIONS.getName());
            dos.flush();
            Log.d("hehe", "wrtited...");
            Object[] objs = (Object[]) din.readObject();
            examinations = new Examination[objs.length];
            for (int i = 0; i < objs.length; i++){
                examinations[i] = (Examination) objs[i];
                Log.d("hehe", examinations[i].toString());
            }
            Log.d("hehe", examinations.length + "");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return examinations;
    }

    public Account getAccount(String email) {
        Account account = null;
        try {
            dos.writeUTF(SocketRequestType.GET_ACCOUNT.getName());
            dos.flush();
//            dos.writeUTF(email);
//            dos.flush();
            account = (Account) din.readObject();
            Log.d("hehe", account.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return account;
    }
}
