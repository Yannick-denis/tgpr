package tgpr.tricount.controller;

import tgpr.framework.Error;
import tgpr.framework.ErrorList;
import tgpr.tricount.model.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.regex.Pattern;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserValidator {


    public static Error isValidPseudo(String mail) {
        if (mail == null || mail.isBlank())
            return new Error("mail required", User.Fields.FullName);
        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", mail))
            return new Error("invalid mail", User.Fields.FullName);
        return Error.NOERROR;
    }

    public static Error isValidAvailablePseudo(String mail) {
        var error = isValidPseudo(mail);
        if (error != Error.NOERROR)
            return error;
        if (User.getByMail(mail) != null)
            return new Error("not available", User.Fields.Mail);
        return Error.NOERROR;
    }

    public static Error isValidPassword(String password) {
        if (password == null || password.isBlank())
            return new Error("password required", User.Fields.Password);
        if (password.length() < 5)
            return new Error("invalid password", User.Fields.Password);
        return Error.NOERROR;
    }
    public static Error isHerPassword(String mail ,String password) {
        var user = User.getByMail(mail);
        if (!user.getHashedPassword().equals(password)){
            return new Error("invalid password",User.Fields.Password);
        }
        return Error.NOERROR;
    }



}

