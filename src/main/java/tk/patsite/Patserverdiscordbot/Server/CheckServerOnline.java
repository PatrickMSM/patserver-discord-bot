package tk.patsite.Patserverdiscordbot.Server;
/*
MIT License

Copyright (c) 2021 PatrickMSM-Chertes

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
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
                in.readByte(); // If this fails to read, the server is offline.
                               // If this can be read, that means the server is online.

                completableFuture.complete(true);
            }
        } catch (IOException ignored) {
            /* ignored */
        } catch (PingException e) {
            e.printStackTrace();
        }
        completableFuture.complete(false);
        return completableFuture;
    }
}












