package androidx.leanback.leanbackshowcase.app.network;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("/api/2/accounts/login/")
    Call<Map<String, String>> login(@Body LoginRequestDTO body);
}
