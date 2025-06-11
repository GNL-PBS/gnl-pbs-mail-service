package com.osm.gnl.pbs.mail.gnl_pbs_mail_service.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailRequest {
    private String copier;
    private String recipient;
    private String name;
    private String subject;
    private String message;
    private LocalDate sentDate;
    private String autoGenPassword;
    private int statusInd;
    private Timestamp expirationDateTime;
    private String errMsg;
    private String attachmentName;
    private byte[] attachmentData;
}
