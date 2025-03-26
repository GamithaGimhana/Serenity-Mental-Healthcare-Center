package com.gdse.serenity.bo;


import com.gdse.serenity.bo.custom.impl.UserBOImpl;

public class BOFactory {
    public static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOType {
        USER
    }

    public SuperBO getBO(BOType type) {
        switch (type) {
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
