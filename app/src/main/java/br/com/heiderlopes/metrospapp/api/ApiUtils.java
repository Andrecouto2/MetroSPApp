package br.com.heiderlopes.metrospapp.api;

public class ApiUtils {

    public static final String BASE_URL = "http://192.168.2.5:3000";

    public static MetroAPI getMetroAPI() {
        return RetrofitClient.getClient(BASE_URL).create(MetroAPI.class);
    }
}