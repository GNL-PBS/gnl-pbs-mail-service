package com.osm.gnl.pbs.mail.gnl_pbs_mail_service.repository;

import com.osm.gnl.pbs.mail.gnl_pbs_mail_service.domain.Mailer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MailerRepository extends JpaRepository<Mailer, UUID> {
}
