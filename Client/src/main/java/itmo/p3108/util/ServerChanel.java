package itmo.p3108.util;

import itmo.p3108.exception.FileException;
import itmo.p3108.model.Person;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Optional;

/**
 * ServerChanel keep classes for sending and receive data
 */
@Slf4j
@Data
public class ServerChanel {
    public static List<Person> list;
    public static String replyFromServer;
    private UDPSender udpSender;
    private UDPReceiver udpReceiver;
    private int serverPort;
    private int clientPort;


    public ServerChanel(int serverPort) {
        this.serverPort = serverPort;
        while (true) {
            try {
                clientPort = (int) (8000 + Math.random() * 2000);
                this.udpReceiver = new UDPReceiver(clientPort);
                break;
            } catch (IllegalArgumentException ignored) {
            }
        }
        SerializeObject.setClientPort(clientPort);
        this.udpSender = new UDPSender(serverPort);
    }


    public String sendAndReceive() {
        StringBuilder builder = new StringBuilder();
        while (true) {
            Optional<byte[]> message = SerializeObject.poll();
            if (message.isEmpty()) {
                if (builder.toString().length() == 0) {
                    replyFromServer = null;
                    list = null;
                    throw new FileException("Message is null.");
                }
                replyFromServer = builder.toString();
                return builder.toString();
            }
            udpSender.send(message.get());
            log.info("Message sent to server,waiting for reply");
            Optional<InetSocketAddress> inetSocketAddress = udpReceiver.receive();
            if (inetSocketAddress.isEmpty()) {
                replyFromServer = null;
                throw new FileException("connection with server lost");
            }
            log.info("Reply got from server");

            builder.append(createMessage(udpReceiver.getBuffer()));

        }
    }

    public void close() {
        udpSender.close();
        udpReceiver.close();
    }

    private String createMessage(ByteBuffer buffer) {
        String serverReply = "";

        buffer.flip();
        int limit = buffer.limit();
        byte[] bytes = new byte[limit];
        buffer.get(bytes, 0, limit);
        Optional<?> reply = DeserializeObject.deserializeObject(bytes);
        if (reply.isEmpty()) {
            throw new FileException("reply is null,lost connection with server");
        }
        Object o = reply.get();
        if (o instanceof MessageServer messageServer) {
            list = messageServer.getList();
            serverReply = messageServer.getMessage();
            replyFromServer = serverReply;
            buffer.clear();
            return serverReply;
        }
        throw new FileException("reply is not messageServer");

    }

}
