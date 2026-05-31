/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author karina
 */

import Model.User.DAOUser;
import Model.User.ModelUser;

public class ControllerLogin {

    private DAOUser daoUser;

    public ControllerLogin(){

        daoUser = new DAOUser();
    }

    public ModelUser login(
            String username,
            String password
    ) {

        if(username.isEmpty() || password.isEmpty()){

            throw new IllegalArgumentException(
                    "Semua field harus diisi!"
            );
        }

        return daoUser.login(username,password);
    }
}