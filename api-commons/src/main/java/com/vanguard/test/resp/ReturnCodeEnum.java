package com.vanguard.test.resp;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ReturnCodeEnum
{
    /**Operation failed**/
    RC999("999","Operation XXX failed"),
    /**Operation successful**/
    RC200("200","success"),
    /**Service downgrade**/
    RC201("201","Service downgrade protection is enabled, please try again later!"),
    /**Hotspot parameter current limiting**/
    RC202("202","Hotspot parameter current limiting, please try again later!"),
    /**System rules are not met**/
    RC203("203","System rules do not meet the requirements, please try again later!"),
    /**Authorization rules are not passed**/
    RC204("204","Authorization rules are not passed, please try again later!"),
    /**access_denied**/
    RC403("403","No access rights, please contact the administrator to grant permissions"),
    /**access_denied**/
    RC401("401","Anonymous user accesses unauthorized resources when exception"),
    RC404("404","404 page not found exception"),
    /**Service exception**/
    RC500("500","System exception, please try again later"),
    RC375("375","Mathematical operation exception, please try again later"),

    INVALID_TOKEN("2001","Invalid access token"),
    ACCESS_DENIED("2003","No permission to access the resource"),
    CLIENT_AUTHENTICATION_FAILED("1001","Client authentication failed"),
    USERNAME_OR_PASSWORD_ERROR("1002","Incorrect username or password"),
    BUSINESS_ERROR("1004","Business logic exception"),
    UNSUPPORTED_GRANT_TYPE("1003","Unsupported authentication mode");

    /**Custom status code**/
    private final String code;
    /**Custom description**/
    private final String message;

    ReturnCodeEnum(String code, String message){
        this.code = code;
        this.message = message;
    }

    public static ReturnCodeEnum getReturnCodeEnum(String code)
    {
        for (ReturnCodeEnum element : ReturnCodeEnum.values()) {
            if(element.getCode().equalsIgnoreCase(code))
            {
                return element;
            }
        }
        return null;
    }

    public static ReturnCodeEnum getReturnCodeEnumV2(String code)
    {
        return Arrays.stream(ReturnCodeEnum.values()).filter(x -> x.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }

}
