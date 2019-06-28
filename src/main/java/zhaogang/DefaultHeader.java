package zhaogang;

public class DefaultHeader extends Header {
    private static final int PROTOCOL_VERSION = 1;
    private static final int PROTOCOL_FLAG = 0;
    private static final int PROTOCOL_RESERVED = 0;


    public DefaultHeader(int serviceId, int commandId) {
        setVersion((short) PROTOCOL_VERSION);
        setFlag((short) PROTOCOL_FLAG);
        setServiceId((short) serviceId);
        setCommandId((short) commandId);
        setSeqnum(DefaultHeader.getInstance().getSeqnum());
        setReserved((short) PROTOCOL_RESERVED);
    }

    public DefaultHeader() {
    }

    private static DefaultHeader _instance;
    private static int seq = 1;

    public static DefaultHeader getInstance() {
        if (_instance == null) {
            _instance = new DefaultHeader();
        }
        return _instance;
    }

    public static int GetIncriSeq() {
        seq++;
        return seq;
    }
}
