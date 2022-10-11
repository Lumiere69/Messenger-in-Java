package com.samasung.chatapp;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class Message {
    public String textMessage2;
    public String textMessage3;
    public String userName;
    public String messPassword;
    public String textMessage;
    public String textMessage1;
    private long messageTime;
    private static int SIGN_IN_REQUEST_CODE
    public Message() {}
    public Message(String userName, String textMessage) {
        this.userName = userName;
        this.textMessage = textMessage;
        this.textMessage1 = textMessage;
        this.textMessage2 = textMessage;
        this.textMessage3 = textMessage;
        this.messPassword = messPassword;

        this.messageTime = new Date().getTime();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getTextMessage() {
        return textMessage;
    }

    public String getTextMessage1() {
        return textMessage1;
    }
    public String getTextMessage2() {
        return textMessage2;
    }
    public String getTextMessage3() {
        return textMessage3;
    }



    public void setTextMessage(String textMessage) throws UnsupportedEncodingException{
        String textMessage1 = new String(textMessage.getBytes("UTF-8"),"CP1141");
        this.textMessage = textMessage1;
    }

    public void setTextMessage1(String textMessage)  throws UnsupportedEncodingException{
        this.textMessage1 = textMessage;
    }

    public void setTextMessage2(String textMessage) throws UnsupportedEncodingException{ ;
        byte[] input = textMessage.getBytes(StandardCharsets.UTF_8);
        String textMessage2 = convertByteArraysToBinary(input);
        this.textMessage2 = textMessage2;
    }

    public void setTextMessage3(String textMessage) throws UnsupportedEncodingException{ ;
        byte random = (byte) (Math.random()*4+1);
        String test = textMessage;
        char[] chars = test.toCharArray();
        StringBuilder test2 = new StringBuilder();
        test2.append((char) random);
        for (byte b = 1; chars.length>b;b++) {
            chars[b] = (char) (chars[b] - random);
            test2.append(chars[b]);
        }

        this.textMessage3 = test2.toString();
    }









    public static String convertByteArraysToBinary(byte[] input) {

        StringBuilder result = new StringBuilder();
        for (byte b : input) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                result.append((val & 128) == 0 ? 0 : 1);      // 128 = 1000 0000
                val <<= 1;
            }
        }
        return result.toString();

    }

    public String getMessPassword() {
        return messPassword;
    }

    public void setMessPassword(String messPassword) {
        this.messPassword = messPassword;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}
