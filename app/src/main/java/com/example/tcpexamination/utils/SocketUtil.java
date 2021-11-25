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
import entity.History;
import entity.Question;

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

    public List<Examination> getAllExaminations() {
        List<Examination> examinations = new ArrayList<>();
        try {
            Log.d("hehe", "writing...");
            dos.writeUTF(SocketRequestType.GET_EXAMINATIONS.getName());
            dos.flush();
            Log.d("hehe", "wrtited...");
            examinations = (ArrayList<Examination>) din.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return examinations;
    }

    public void saveNewHistoryRecord(History history) {
        try {
            dos.writeUTF(SocketRequestType.SAVE_HISTORY_RECORD.getName());
            dos.flush();
            dos.writeObject(history);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<History> getHistoryByAccountEmail(String accountEmail) {
        List<History> histories = new ArrayList<>();
        try {
            dos.writeUTF(SocketRequestType.GET_HISTORY_OF_ACCOUNT.getName());
            dos.flush();
            dos.writeUTF(accountEmail);
            dos.flush();
            histories = (List<History>) din.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return histories;
    }

    public List<Question> getQuestionByExamId(Long examinationId) {
        List<Question> questions;

        try {
            dos.writeUTF(SocketRequestType.GET_LIST_QUESTIONS_BY_EXAM_ID.getName());
            dos.flush();
            dos.writeLong(examinationId);
            dos.flush();
            questions = (List<Question>) din.readObject();
        } catch (IOException | ClassNotFoundException e) {
            questions = new ArrayList<>();
            e.printStackTrace();
        }

        return questions;
    }
}
