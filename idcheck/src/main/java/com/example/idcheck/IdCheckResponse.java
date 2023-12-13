package com.example.idcheck;

public class IdCheckResponse {
    private final int result;

    public IdCheckResponse(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }
}
