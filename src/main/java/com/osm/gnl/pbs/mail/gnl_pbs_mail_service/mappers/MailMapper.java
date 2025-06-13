package com.osm.gnl.pbs.mail.gnl_pbs_mail_service.mappers;

import com.osm.gnl.pbs.mail.gnl_pbs_mail_service.domain.Mailer;
import com.osm.gnl.pbs.mail.gnl_pbs_mail_service.web.model.MailRequest;
import org.mapstruct.*;
import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface MailMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sentDate", expression = "java(mailRequest.getSentDate() != null ? mailRequest.getSentDate() : LocalDate.now())")
    Mailer toMailer(MailRequest mailRequest);
}

