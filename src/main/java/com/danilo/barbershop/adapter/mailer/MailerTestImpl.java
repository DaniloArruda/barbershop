package com.danilo.barbershop.adapter.mailer;

import com.danilo.barbershop.port.mailer.Mailer;

import org.springframework.stereotype.Component;

@Component
public class MailerTestImpl implements Mailer {

    @Override
    public void send(String message) {
        System.out.println(message);
    }

}
