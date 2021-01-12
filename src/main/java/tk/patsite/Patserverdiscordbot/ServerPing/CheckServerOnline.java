package tk.patsite.Patserverdiscordbot.ServerPing;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public final class CheckServerOnline {

    private static final IOUtil IOUtil = new IOUtil();

    public static CompletableFuture<Boolean> IsServerOnline(String Address) {
        CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();
        try (final Socket sock = new Socket()) {
            sock.connect(new InetSocketAddress(Address, 25565));

            try (DataInputStream in = new DataInputStream(sock.getInputStream());
                 DataOutputStream out = new DataOutputStream(sock.getOutputStream());

                 // <OUT> Handshake
                 ByteArrayOutputStream handshake_bytes = new ByteArrayOutputStream();
                 DataOutputStream handshake_out = new DataOutputStream(handshake_bytes)) {

                handshake_out.writeByte(0x00); // 0x00 is handshake packet
                IOUtil.WriteVarInt(handshake_out, 4); // 4 is protocol number
                IOUtil.WriteVarInt(handshake_out, Address.length()); // address length
                handshake_out.writeBytes(Address); // write the address
                handshake_out.writeShort(25565); // 25565 is the port
                IOUtil.WriteVarInt(handshake_out, 1); // Handshake status

                IOUtil.WriteVarInt(out, handshake_bytes.size()); // Write handshake size
                out.write(handshake_bytes.toByteArray()); // Write handshake

                // <OUT> Status request
                out.writeByte(0x01); // Size
                out.writeByte(0x00); // Status request

                // <IN> Status response
                IOUtil.ReadVarInt(in); // Unnecessary size
                int id = IOUtil.ReadVarInt(in); // Packet type

                IOUtil.except(id == -1, "Server prematurely ended stream.");
                IOUtil.except(id != 0x00, "Server returned invalid packet type."); // not status request packet

                int length = IOUtil.ReadVarInt(in);
                IOUtil.except(length == -1, "Server prematurely ended stream.");
                IOUtil.except(length == 0, "Server returned unexpected value.");

                byte[] data = new byte[length];
                in.readFully(data);
                String json = new String(data, StandardCharsets.UTF_8);

                // <OUT> Ping (FINALLY)
                out.writeByte(0x09); // Packet size
                out.writeByte(0x01); // Ping packet
                out.writeLong(System.currentTimeMillis()); // Calculate ping time on server

                // <IN> Finally inbound ping packet
                IOUtil.ReadVarInt(in); // unnecessary size again
                id = IOUtil.ReadVarInt(in); // ID

                IOUtil.except(id == -1, "Server prematurely ended stream.");
                IOUtil.except(id != 0x01, "Server returned invalid packet type.");

                completableFuture.complete(true);
            }
        //} catch (IOException ignored) {
            /* ignored */
        } catch (PingException | IOException e) {
            e.printStackTrace();
        }
        completableFuture.complete(false);
        return completableFuture;
    }
}












