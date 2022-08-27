package mail;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import javax.mail.*;

public class MailService {

    private Session session;
    private Store store;
    private Folder folder;

    // hardcoding protocol and the folder
    // it can be parameterized and enhanced as required
    private String protocol = "imaps";
    private String file = "INBOX";

    public MailService() {

    }

    public boolean isLoggedIn() {
        return store.isConnected();
    }

    /**
     * to login to the mail host server
     */

    public void login(String host, String username, String password)
            throws Exception {
        username = "your_email@gmail.com";
        Path fileName = Path.of("Your_Password_Directory");
        String pass = Files.readString(fileName);
        password = pass;
        // Google imap
        host = "imap.gmail.com";
        URLName url = new URLName(protocol, host, 993, file, username, password);

        if (session == null) {
            Properties props = null;
            try {
                props = System.getProperties();
            } catch (SecurityException sx) {
                props = new Properties();
            }
            session = Session.getInstance(props, null);
        }
        store = session.getStore(url);
        store.connect();
        folder = store.getFolder(url);

        folder.open(Folder.READ_WRITE);
    }

    /**
     * to logout from the mail host server
     */
    public void logout() throws MessagingException {
        folder.close(false);
        store.close();
        store = null;
        session = null;
    }

    public int getMessageCount() {
        int messageCount = 0;
        try {
            messageCount = folder.getMessageCount();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
        return messageCount;
    }

    public Message[] getMessages() throws MessagingException {
        return folder.getMessages();
    }

}
