package de.sambalmueslie.eventorg.auth


import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.security.authentication.UsernamePasswordCredentials
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken
import org.junit.jupiter.api.Assertions
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class AuthUtils {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(AuthUtils::class.java)

        fun getAuthToken(client: HttpClient, username: String): String {
            val credentials = UsernamePasswordCredentials(username, "password")
            val request: HttpRequest<Any> = HttpRequest.POST("/login", credentials)
            val rsp: HttpResponse<BearerAccessRefreshToken> = client.toBlocking().exchange(request, BearerAccessRefreshToken::class.java)
            Assertions.assertEquals(HttpStatus.OK, rsp.status)

            val bearerAccessRefreshToken: BearerAccessRefreshToken = rsp.body()!!
            return bearerAccessRefreshToken.accessToken
        }
    }


}
