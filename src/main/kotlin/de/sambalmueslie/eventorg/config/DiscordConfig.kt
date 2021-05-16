package de.sambalmueslie.eventorg.config

import io.micronaut.context.annotation.ConfigurationProperties
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.validation.constraints.NotBlank

@ConfigurationProperties("discord")
class DiscordConfig {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(DiscordConfig::class.java)
    }

    @NotBlank
    var token: String = ""
        set(value) {
            logger.debug("Set token from '$token' to '$value'")
            field = value
        }

    @NotBlank
    var adminChannelName: String = "event-administration"
        set(value) {
            logger.debug("Set adminChannelName from '$adminChannelName' to '$value'")
            field = value
        }

    @NotBlank
    var eventCategoryName: String = "Event Calendar"
        set(value) {
            logger.debug("Set eventCategoryName from '$eventCategoryName' to '$value'")
            field = value
        }

    @NotBlank
    var eventChannelPrefix: String = "EVT"
        set(value) {
            logger.debug("Set eventChannelPrefix from '$eventChannelPrefix' to '$value'")
            field = value
        }
}
