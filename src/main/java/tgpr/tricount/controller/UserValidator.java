package tgpr.tricount.controller;

import tgpr.framework.Error;
import tgpr.framework.ErrorList;
import tgpr.tricount.model.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.regex.Pattern;

public abstract class UserValidator {


    public static Error isValidPseudo(String pseudo) {
        if (pseudo == null || pseudo.isBlank())
            return new Error("name required", User.Fields.FullName);
        if (!Pattern.matches("[a-zA-Z][a-zA-Z0-9]{2,7}", pseudo))
            return new Error("invalid name", User.Fields.FullName);
        return Error.NOERROR;
    }

    public static Error isValidAvailablePseudo(String fullname) {
        var error = isValidPseudo(fullname);
        if (error != Error.NOERROR)
            return error;
        if (User.getByFullName(fullname) != null)
            return new Error("not available", User.Fields.FullName);
        return Error.NOERROR;
    }

    public static Error isValidPassword(String password) {
        if (password == null || password.isBlank())
            return new Error("password required", User.Fields.Password);
        if (!Pattern.matches("[a-zA-Z0-9]{3,}", password))
            return new Error("invalid password", User.Fields.Password);
        return Error.NOERROR;
    }



}

