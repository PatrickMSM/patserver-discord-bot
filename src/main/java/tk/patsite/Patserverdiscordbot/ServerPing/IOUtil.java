package tk.patsite.Patserverdiscordbot.ServerPing;

import org.xbill.DNS.*;
import org.xbill.DNS.Record;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;

final class IOUtil {
    String getARecord(String Site) {
        String host = "";
        try {
            final Resolver resolver = new ExtendedResolver(new String[]{"1.1.1.1", "1.0.0.1", "8.8.8.8", "8.8.4.4"});
            final Lookup lookup = new Lookup(Site, Type.A);
            lookup.setResolver(resolver);

            org.xbill.DNS.Record[] records = lookup.run();

            if (records != null) {
                for (Record record : records) {
                    host = ((ARecord) record).getAddress().getHostAddress();
                }
            }

        } catch (TextParseException | UnknownHostException e) {
            e.printStackTrace();
        }
        return host;
    }

    void WriteVarInt(DataOutputStream out, int intVal) throws IOException {
        while (true) {
            if ((intVal & 0xFFFFFF80) == 0) {
                out.writeByte(intVal);
                return;
            }

            out.writeByte(intVal & 0x7F | 0x80);
            intVal >>>= 7;
        }
    }

    int ReadVarInt(DataInputStream in) throws IOException {
        int i = 0;
        int j = 0;
        while (true) {
            int k = in.readByte();

            i |= (k & 0x7F) << j++ * 7;

            if (j > 5) {
                throw new RuntimeException("VarInt too big");
            }

            if ((k & 0x80) != 128) {
                break;
            }
        }

        return i;
    }

    void except(final boolean b, final String str) throws PingException {
        if (b) {
            throw new PingException(str);
        }
    }
}
