package com.pb.marenychenko.hw7;

//XXS, XS, S, M, L
//32, 34, 36, 38, 40

public enum Size {
    XXS("XXS", 32),
    XS("XS", 34),
    S("S", 36),
    M("M", 38),
    L("L", 40);

    private int size;
    private String description;

    Size(String description, int size) {
        this.size = size;
        this.description = description;

    }
    public int getEuroSize(){
        return this.size;
    }
    public String getDescription() {
        String dsc;
        switch (this.size) {
            case 32:
                dsc = "Детский размер";
                break;
            default:
                dsc = "Взрослый размер";
        }
        return dsc;
    }
    @Override
    public String toString() {
        return this.description;
    }
}