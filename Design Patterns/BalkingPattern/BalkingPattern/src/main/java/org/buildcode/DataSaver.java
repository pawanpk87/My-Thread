package org.buildcode;

/*
* Data is only saved if it hasn't already been saved
* */
public class DataSaver {
    private boolean changed;

    public DataSaver() {
        this.changed = false;
    }

    synchronized public void changeData() {
        this.changed = true;
        System.out.println("Data has been changed.");
    }

    synchronized public void save() {
        if(!changed) {
            System.out.println("No changes to save. Balking...");
            return;
        }
        System.out.println("Saving the data...");
        changed = false;
    }
}