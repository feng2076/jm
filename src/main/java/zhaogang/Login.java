package zhaogang;

import com.mogujie.tt.protobuf.IMBaseDefine;


public class Login {
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

        byte[] data = MidMedth.getData(header, req.toByteArray(), req.getSerializedSize());
        // 打印的字符贴到jmeter中即可
        return MidMedth.bytes2hex(data);
    }
}
