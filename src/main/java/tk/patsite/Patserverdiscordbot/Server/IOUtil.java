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
