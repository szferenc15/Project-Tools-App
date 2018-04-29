package app.sportmates_backend.util;

import lombok.Data;

@Data
public class Response<T>{
    public T data;

    public Response(T data){
        this.data = data;
    }
    
    public static <T> Response<T> ok(T data){
        return new Response<T>(data);
    }

    public static void error(String message){
        throw new IllegalArgumentException(message);
    }
}