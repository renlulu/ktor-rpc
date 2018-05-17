This is a simple demo using corda rpc client to connect corda node with ktor.

Now we have 5 properties in the config.properties:

1. port: the port ktor server listen on

2. host: corda node host

3. username: username which needed to call node rpc server / in fact flow

4. password: password which needed to call node rpc server / in fact flow

5. rpcPort: node server's rpc port

you can use command 'gradle shadowJar' to generate your fat jar, you should put you conf.properties under the same directory as the fat jar

I also gave a restful-style interface to get the node party name :)