This is a simple demo using corda rpc client to connect corda node with [ktor](https://github.com/ktorio/ktor).

Now we have 5 properties in the `config.properties` file:

1. `port`: port the ktor server listens on

2. `host`: corda node host

3. `username`: username needed to connect to node rpc server

4. `password`: password needed to connect to node rpc server

5. `rpcPort`: node server's RPC port

You can use command `gradle shadowJar` to generate your fat jar. You should put you conf.properties under the same directory as the fat jar. You can also override properties with environment variables, the key names of the environment variables are the same as properties. For example:

    port=8099 java -jar app-1.0-SNAPSHOT-shadow.jar 

then the configuration item named `port` in `config.properties` will be overridden.

I also gave a restful-style interface to get the node party name :) Go to:

    localhost:<PORT>/nodes/partyName
