package com.example.ntbargainhunter;

public class User {
    private String email;
    private String name;
    private String recoveryQuestion1;
    private String recoveryQuestionAnswer1;
    private String recoveryQuestion2;
    private String recoveryQuestionAnswer2;
    private String recoveryQuestion3;
    private String recoveryQuestionAnswer3;

    User() {

    }

    User(String name, String email, String q1, String a1, String q2, String a2, String q3, String a3) {
        this.name = name;
        this.email = email;
        recoveryQuestion1 = q1;
        recoveryQuestion2 = q2;
        recoveryQuestion3 = q3;
        recoveryQuestionAnswer1 = a1;
        recoveryQuestionAnswer2 = a2;
        recoveryQuestionAnswer3 = a3;
    }

    public String getName() {
        return name;
    }
    public void updateName(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    public void updateEmail(String email) {
        this.email = email;
    }

    public String getRecoveryQuestion1() {
        return recoveryQuestion1;
    }
    public void updateRecoveryQuestion1(String q) {
        recoveryQuestion1 = q;
    }

    public String getRecoveryQuestion2() {
        return recoveryQuestion2;
    }
    public void updateRecoveryQuestion2(String q) {
        recoveryQuestion2 = q;
    }

    public String getRecoveryQuestion3() {
        return recoveryQuestion3;
    }
    public void updateRecoveryQuestion3(String q) {
        recoveryQuestion3 = q;
    }

    public String getRecoveryQuestionAnswer1() {
        return recoveryQuestionAnswer1;
    }
    public void updateRecoveryQuestionAnswer1(String a) {
        recoveryQuestionAnswer1 = a;
    }

    public String getRecoveryQuestionAnswer2() {
        return recoveryQuestionAnswer2;
    }
    public void updateRecoveryQuestionAnswer2(String a) {
        recoveryQuestionAnswer2 = a;
    }

    public String getRecoveryQuestionAnswer3() {
        return recoveryQuestionAnswer3;
    }
    public void updateRecoveryQuestionAnswer3(String a) {
        recoveryQuestionAnswer3 = a;
    }
}
