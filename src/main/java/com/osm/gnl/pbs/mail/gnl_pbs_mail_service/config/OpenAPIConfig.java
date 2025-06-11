package com.osm.gnl.pbs.mail.gnl_pbs_mail_service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "GNL Systems Ltd",
                        url = "https://gnlsystems.com"
                ),
                description = "OpenApi documentation for GNL-PBS Mail service",
                title = "API Documentation for GNL-PBS Mail Service"
        ),
        servers = {
                @Server(
                        description = "Test ENV",
                        url = "http://localhost:8300"
                ),
                @Server(
                        description = "Cloud Gateway Port",
                        url = "http://localhost:8765"
                )
        }
)

public class OpenAPIConfig { }
