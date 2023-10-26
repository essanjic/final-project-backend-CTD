package com.proyectctd.SpringBack.repository;

public interface EmailRepository {
    void sendMail(String to, String subject, String body) throws Exception;
}
