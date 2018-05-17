package net.corda

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import net.corda.client.rpc.CordaRPCClient
import net.corda.core.utilities.NetworkHostAndPort
import java.io.FileInputStream
import java.time.Duration
import java.util.*

val properties = Properties()


val proxy = run {
    val host = (properties["host"] ?: "localhost").toString()
    val username = (properties["username"] ?: "user1").toString()
    val password = (properties["password"] ?: "letmein").toString()
    val rpcPort = (properties["rpcPort"] ?: "10053").toString().toInt()

    val rpcAddress = NetworkHostAndPort(host, rpcPort)
    val rpcClient = CordaRPCClient(rpcAddress)
    val rpcConnection = rpcClient.start(username, password)
    rpcConnection.proxy
}

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(CORS) {
        anyHost()
        allowCredentials = true
        maxAge = Duration.ofDays(1)
    }

    install(Routing) {

        val mapper = ObjectMapper()

        get("/nodes/partyName"){
            call.respond(mapper.writeValueAsString(proxy.nodeInfo().legalIdentities[0].name.x500Principal.name))
        }
    }
}

fun main(args: Array<String>) {
    val propertiesFile = System.getProperty("user.dir") + "/config.properties";
    val inputStream = FileInputStream(propertiesFile)
    properties.load(inputStream)
    properties.forEach{(k, v) -> println("key = $k, value = $v")}

    val port = (properties["port"] ?: "9090").toString()

    embeddedServer(Netty, port = port.toInt(), watchPaths = listOf("MainKt"), module = Application::module).start()

}