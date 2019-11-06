import com.jcraft.jsch.*;

public class Sftper {
    public static void download
            (String username, String host, int port, String password, String src, String dst)
            throws JSchException, SftpException {

        JSch jsch = new JSch();
        Session session;
        session = jsch.getSession(username, host, port);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(password);
        session.connect();

        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        sftpChannel.get(src, dst);
        sftpChannel.exit();
        session.disconnect();
    }
    public static void upload
            (String username, String host, int port, String password, String src, String dst)
            throws JSchException, SftpException {

        JSch jsch = new JSch();
        Session session;
        session = jsch.getSession(username, host, port);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(password);
        session.connect();

        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        sftpChannel.put(src, dst);
        sftpChannel.exit();
        session.disconnect();
    }
}
