package vn.t3h.androidwebapi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hoang on 7/21/16.
 */

public class LoginModel {
    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private int status;

    @SerializedName("accessToken")
    private String token;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
