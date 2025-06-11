package com.osm.gnl.pbs.mail.gnl_pbs_mail_service.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pbs_sent_mails")
public class Mailer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "mail_id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "cc_email", length = 100)
    private String copier;

    @Column(name = "recipient_email", length = 100, nullable = false)
    private String recipient;

    @Column(name = "recipient_name", length = 100, nullable = false)
    private String name;

    @Column(name = "msg_subject", length = 100, nullable = false)
    private String subject;

    @Column(name = "mail_message", nullable = false)
    private String message;

    @Column(name = "sent_date", nullable = false)
    private LocalDate sentDate;

    @Column(name = "auto_gen_password", length = 100)
    private String autoGenPassword;

    @Column(name = "status", length = 1)
    private int statusInd;

    @Column(name = "expiration")
    private Timestamp expirationDateTime;

    @Column(name = "error_msg")
    private String errMsg;
}
