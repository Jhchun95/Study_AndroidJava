package androidx.leanback.leanbackshowcase.app.network;

public class LoginRequestDTO {
    final String email;
    final String password;
    final boolean permanent_auth = true;
    final String device_description = "tv-client 1.0";

    public LoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
