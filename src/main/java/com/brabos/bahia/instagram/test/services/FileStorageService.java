package com.brabos.bahia.instagram.test.services;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@Service
public class FileStorageService {

    public String storageFile(MultipartFile file, String path) {

        //if(!path.equals("profile_image") && !path.equals("post_image")){ }

        try {
            String FTP_ADDRESS = "ftp.gabriel.govmail.com.br";
            String LOGIN = "gabrigov.instagram_clone@gabriel.govmail.com.br";
            String PSW = "gQ)$]dTTQ##&";

            FTPClient con = null;
            con = new FTPClient();
            con.connect(FTP_ADDRESS);

            if (con.login(LOGIN, PSW)) {
                con.enterLocalPassiveMode(); // important!
                con.setFileType(FTP.BINARY_FILE_TYPE);
                con.changeWorkingDirectory(path);

                boolean result = con.storeFile(file.getOriginalFilename(), file.getInputStream());
                con.logout();
                con.disconnect();
                return "https://gabriel.govmail.com.br:2078/" + path + "/" + file.getOriginalFilename();
            }else{
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
