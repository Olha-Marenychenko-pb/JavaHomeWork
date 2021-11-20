package com.pb.marenychenko.hw8;

public class Auth {
    private boolean signedUp;
    private boolean signedIn;
    private boolean strongPassRequired;

    private String login;
    private String password;
    public Auth(){
        this.signedUp = false;
        this.signedIn = false;
        this.strongPassRequired = false;
    }
    public Auth(boolean strongPassRequired){
        this.signedUp = false;
        this.signedIn = false;
        this.strongPassRequired = strongPassRequired;
    }
    public String getLogin() {
        return login;
    }

    public boolean getSignedUp(){
        return this.signedUp;
    }
    public boolean getSignedIn(){
        return this.signedIn;
    }

    private boolean check_login(String login) throws WrongLoginException {
        if (login.matches("^[a-zA-Z0-9]{5,20}$")) {
            return true;
        }
        else
        {
            throw new WrongLoginException("'" + login +"'  не соответвует требованиям");
        }
        //return false;
    };
    private boolean check_password(String password, String confirmPassword) throws WrongPasswordException{
        String strongCheck = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[_])[a-zA-Z0-9_]{6,}$";
        String lightCheck = "^[a-zA-Z0-9_]{6,}$";
        String checkString;
        if (this.strongPassRequired)
        {
            checkString = strongCheck;
        }
        else
        {
            checkString = lightCheck;
        }
        boolean passDemand = password.matches(checkString);
        boolean passEquals = password.equals(confirmPassword);
        if ( (passDemand) && (passEquals)) {
            return true;
        }
        else
        {
            if (!passDemand) {
            throw new WrongPasswordException("'" + password + "' не соответствует требованиям!");
            }
            else
            {
                throw new WrongPasswordException("'" + password + "' не соответствует паролю подтверждения '" + confirmPassword +"'");

            }

        }
        //return false;
    };
    public boolean signUp(String login, String password, String confirmPassword) throws WrongLoginException,WrongPasswordException{
        if (check_login(login) && check_password(password, confirmPassword))
        {
           this.login = login;
           this.password = password;
           this.signedUp = true;
           return true;
        }
        return false;

    }

    public boolean signIn(String login, String password) throws WrongLoginException{
        if (login.equals(this.login) && password.equals(this.password)){
            this.signedIn = true;
            return true;
        }
        else {
            throw new WrongLoginException("Не существует юзера '" + login + "' с паролем '" + password + "' !");
        }
          //return false;
    }

}
