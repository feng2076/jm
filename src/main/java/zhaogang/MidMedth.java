package zhaogang;

import org.jboss.netty.buffer.ChannelBuffer;

public class MidMedth {
    public static byte[] getData(Header header, byte[] body, int bodySize) {
        header.setLength(Header.PROTOCOL_HEADER_LENGTH + bodySize);

        // 头部序列化
        DataBuffer headerBuffer = header.encode();

        // 数据部 对象 序列号成二进制数组
        DataBuffer bodyBuffer = new DataBuffer();
        bodyBuffer.writeBytes(body);

        // 整个二进制数据包，
        DataBuffer buffer = new DataBuffer(Header.PROTOCOL_HEADER_LENGTH + bodySize);
        buffer.writeDataBuffer(headerBuffer);
        buffer.writeDataBuffer(bodyBuffer);

        // 从这里面取数据
        ChannelBuffer channel = buffer.getOrignalBuffer();
        // 打印的字符贴到jmeter中即可
        return channel.array();
    }

    public static String bytes2hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        String tmp;
        for (byte b : bytes) {
            // 将每个字节与0xFF进行与运算转化为10进制，然后借助于Integer再转化为16进制
            tmp = Integer.toHexString(0xFF & b);
            if (tmp.length() < 2) {
                sb.append("0");
            }
            sb.append(tmp);
        }
        return sb.toString();
    }
}
