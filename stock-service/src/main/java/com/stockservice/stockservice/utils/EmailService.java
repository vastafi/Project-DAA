package com.stockservice.stockservice.utils;

import java.io.File;
import java.util.List;

public interface EmailService {
    void sendHtmlMessage(String to, String subject, String html, List<File> attachments);
}
