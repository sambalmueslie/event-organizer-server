package de.sambalmueslie.eventorg.common


import de.sambalmueslie.eventorg.auth.AuthUtils
import io.micronaut.core.type.Argument
import io.micronaut.data.model.Page
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import org.junit.jupiter.api.BeforeEach
import javax.inject.Inject

abstract class BaseIntegrationTest<T> {
    private var tokenGenerated: Boolean = false

    @Inject
    @field:Client("/")
    lateinit var client: RxHttpClient

    protected lateinit var token: String

    @BeforeEach
    fun generateToken() {
        if (tokenGenerated) return
        token = AuthUtils.getAuthToken(client, "test")
        tokenGenerated = true
    }


    fun <T> post(uri: String, body: T, token: String = this.token) = HttpRequest.POST(uri, body).bearerAuth(token)
    fun <T> put(uri: String, body: T, token: String = this.token) = HttpRequest.PUT(uri, body).bearerAuth(token)
    fun get(uri: String, token: String = this.token) = HttpRequest.GET<String>(uri).bearerAuth(token)
    fun delete(uri: String, token: String = this.token) = HttpRequest.DELETE<Any>(uri).bearerAuth(token)

    fun <I> callPost(uri: String, body: I, token: String = this.token) = retrieve(post(uri, body, token), getDefaultType())
    fun <I> callPut(uri: String, body: I, token: String = this.token) = retrieve(put(uri, body, token), getDefaultType())
    fun callGet(uri: String, token: String = this.token) = retrieve(get(uri, token), getDefaultType())
    fun callDelete(uri: String, token: String = this.token) = retrieve(delete(uri, token), getDefaultType())

    fun <I, O> retrieve(request: HttpRequest<I>, bodyType: Argument<O>) = client.toBlocking().retrieve(request, bodyType)
    fun <I, O> retrieve(request: HttpRequest<I>, bodyType: Class<O>) = client.toBlocking().retrieve(request, bodyType)

    @Suppress("UNCHECKED_CAST")
    fun callGetPage(uri: String, token: String = this.token) = retrieve(get(uri, token), Argument.of(Page::class.java, getDefaultType())) as Page<T>

    fun <I> doPost(uri: String, body: I, token: String = this.token) = exchange(post(uri, body, token), Any::class.java)
    fun <I> doPut(uri: String, body: I, token: String = this.token) = exchange(put(uri, body, token), Any::class.java)
    fun doGet(uri: String, token: String = this.token) = exchange(get(uri, token), Any::class.java)
    fun doDelete(uri: String, token: String = this.token) = exchange(delete(uri, token), Any::class.java)

    fun <I, O> exchange(request: HttpRequest<I>, bodyType: Argument<O>) = client.toBlocking().exchange(request, bodyType)
    fun <I, O> exchange(request: HttpRequest<I>, bodyType: Class<O>) = client.toBlocking().exchange(request, bodyType)


    abstract fun getDefaultType(): Class<T>
}
