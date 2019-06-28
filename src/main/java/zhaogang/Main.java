package zhaogang;

import com.google.protobuf.ByteString;
import com.mogujie.tt.protobuf.IMBaseDefine;
import org.jboss.netty.buffer.ChannelBuffer;

public class Main {

    public static void main(String[] args) {
        System.out.println(Login(1));
        System.out.println(SendMessage(1, 2, "你好好"));
    }


    public static String Login(int userId) {
        // 假设是登录
        // 发送到什么服务器，具体见：IM.BaseDefine.proto ==> ServiceID
        int sid = IMBaseDefine.ServiceID.SID_LOGIN_VALUE;
        // 消息类型：可以理解为业务，比如登录、发文本等都不一样
        // 具体见：IM.BaseDefine.proto ==> LoginCmdID、BuddyListCmdID、MessageCmdID
        int cid = IMBaseDefine.LoginCmdID.CID_LOGIN_REQ_USERLOGIN_VALUE;

        // 协议头部
        Header header = new DefaultHeader(sid, cid);
        // 数据部
        // userToken编码前：4b8fad1ac93be799a220b2314c81f8ab
        com.mogujie.tt.protobuf.IMLogin.IMLoginReq req =
                com.mogujie.tt.protobuf.IMLogin.IMLoginReq.newBuilder()
                        .setAppId(2)        // 1：android，2：ios
                        .setUserId(userId)       // 用户id
                        .setDomainId(1000)  // 写死即可
                        .setUserToken("ZGFlNzMwNDViMmM0NzkxNTg5MGI3MjM1ZGI5ODcyMzQ=")  // 作base64编码，这里直接写死的base64编码后的数据
                        .setOnlineStatus(IMBaseDefine.UserStatType.USER_STATUS_ONLINE) // 在线
                        .setClientVersion("0.1")                                       // 客户端版本，无所谓随便写
                        .build();

        byte[] data = getData(header, req.toByteArray(), req.getSerializedSize());
        // 打印的字符贴到jmeter中即可
        return bytes2hex(data);
    }

    public static String SendMessage(int fromUserId, int toUserId, String text) {
        int sid = IMBaseDefine.ServiceID.SID_MSG_VALUE;
        int cid = IMBaseDefine.MessageCmdID.CID_MSG_DATA_VALUE;

        // 协议头部
        Header header = new DefaultHeader(sid, cid);
        // 数据部
        com.mogujie.tt.protobuf.IMMessage.IMMsgData req =
                com.mogujie.tt.protobuf.IMMessage.IMMsgData.newBuilder()
                        .setAppId(2)
                        .setDomainId(1000)
                        .setFromUserId(fromUserId)          // 谁发的
                        .setToSessionId(toUserId)           // 谁接收
                        .setMsgId(DefaultHeader.GetIncriSeq())
                        .setCreateTime((int) (System.currentTimeMillis() / 1000))
                        .setMsgType(IMBaseDefine.MsgType.MSG_TYPE_SINGLE_TEXT)      // 单聊一对一文本消息
                        .setMsgData(ByteString.copyFrom(text.getBytes()))           // 文本内容
                        .build();

        byte[] data = getData(header, req.toByteArray(), req.getSerializedSize());
        // 打印的字符贴到jmeter中即可
        return bytes2hex(data);
    }

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
