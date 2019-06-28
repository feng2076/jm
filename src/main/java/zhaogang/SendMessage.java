package zhaogang;

import com.google.protobuf.ByteString;
import com.mogujie.tt.protobuf.IMBaseDefine;


public class SendMessage {
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

        byte[] data = MidMedth.getData(header, req.toByteArray(), req.getSerializedSize());
        // 打印的字符贴到jmeter中即可
        return MidMedth.bytes2hex(data);
    }
}
