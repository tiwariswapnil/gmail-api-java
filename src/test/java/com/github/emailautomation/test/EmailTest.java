package com.github.emailautomation.test;

import com.github.emailautomation.gmail.GmailAPI;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.fail;

@Slf4j
public class EmailTest {

    @Test
    void verifyEmail() throws IOException, GeneralSecurityException {
        String expectedSubject = "";
        String expectedContent = "";

        boolean exist = GmailAPI.isMailExist("");

        if (!exist) {
            System.out.println("Email with the subject " + expectedSubject + " not found");
        } else {
            HashMap<String, String> hm = GmailAPI.getGmailData("subject:" + expectedSubject + " AND is:unread");

            if (hm.get("subject").equalsIgnoreCase(expectedSubject) && hm.get("body").toUpperCase().contains(expectedContent.toUpperCase())) {
                System.out.println("Email content is as expected !");
            } else {
                System.out.println("Failed verification - Email content / subject mismatching !");
                fail();
            }

            GmailAPI.deleteMessage("subject:" + expectedSubject + " AND is:unread");

        }


    }
}
