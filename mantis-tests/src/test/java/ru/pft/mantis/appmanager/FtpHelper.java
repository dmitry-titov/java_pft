package ru.pft.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;
import ru.pft.mantis.tests.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpHelper extends TestBase {

    private final ApplicationManager app;
    private FTPClient ftp;

    public FtpHelper(ApplicationManager app) {
        this.app = app;
        ftp = new FTPClient();
    }

    public void upload(File file, String target, String backup) throws IOException {
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.changeWorkingDirectory("mantisbt-1.3.4/config");
        ftp.deleteFile(backup);
        ftp.rename(target, backup);
        ftp.enterLocalPassiveMode();
        ftp.storeFile(target, new FileInputStream(file));
        ftp.disconnect();
    }

    public void restore(String backup, String target) throws IOException {
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(target);
        ftp.rename(backup, target);
        ftp.disconnect();
    }
}
