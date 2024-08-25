package org.buildcode;

public class Main {
    public static void main(String[] args) {
        DataSaver dataSaver = new DataSaver();

        dataSaver.save();

        dataSaver.changeData();

        dataSaver.save();

        dataSaver.save();
    }
}