package tk.patsite.Patserverdiscordbot.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

final class IOUtil {
    void WriteVarInt(DataOutputStream out, int intVal) throws IOException {
        while (true) {
            if ((intVal & 4294967168L) == 0) {  // 0xFFFFFF80
                out.writeByte(intVal);
                return;
            }

            out.writeByte(intVal & 127 | 128);  // 0x7F |  0x80
            intVal >>>= 7;
        }
    }

    int ReadVarInt(DataInputStream in) throws IOException {
        int i = 0;
        int j = 0;
        while (true) {
            int k = in.readByte();

            i |= (k & 127) << j++ * 7; // 0x7F

            if (j > 5) {
                throw new RuntimeException("VarInt too big");
            }

            if ((k & 128) != 128) { //0x80
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
