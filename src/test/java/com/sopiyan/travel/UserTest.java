package com.sopiyan.travel;

import com.sopiyan.travel.model.entity.Terminal;
import com.sopiyan.travel.model.entity.User;
import com.sopiyan.travel.service.repository.LoginRepository;
import com.sopiyan.travel.service.services.TerminalService;
import com.sopiyan.travel.service.services.UserService;
import com.sopiyan.travel.util.enumuration.Roles;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

/**
 * Created by Sopiyan on 02/06/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AplikasiTravelApplication.class)
public class UserTest {
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private TerminalService terminalService;

    @Test
    public void simpan(){
/*      User user = new User();
        user.setListPercakapan(new ArrayList<>());
        user.setListTiket(new ArrayList<>());
        user.setNamaLengkap("AYU Nugraha");
        user.setRole(Roles.ADMIN);
        user.setSaldo(new Double("1"));
        user.setEnabled(true);
        user.setNoTelpon("0855522125");
        user.setEmail("aiiuzhuanx@gmail.com");
        user.setListPesan(new ArrayList<>());
        user.setPhoto("/assets/img/default.jpg");
        user.setPassword(new BCryptPasswordEncoder().encode("960150"));
        loginRepository.save(user);*/
/*        Terminal t = terminalService.cariBerdasarkanNamaTerminal("sad");
        System.out.println(t.getKota().getNamaKota());
        Assert.assertNotNull("t",t);*/
    }
}
