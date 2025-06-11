package com.osm.gnl.pbs.mail.gnl_pbs_mail_service.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailResponse {
    private UUID mailId;
    private boolean success;
    private String errorMessage;
}
