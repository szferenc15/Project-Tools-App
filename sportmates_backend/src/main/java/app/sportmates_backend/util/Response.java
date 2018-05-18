package app.sportmates_backend.util;

import lombok.Data;

/**
 * Ezzel az osztállyal kommunikálunk az adatbázissal.
 * @author szendrei
 * @author polozgai
 *
 * @param <T> Objektum
 */
@Data
public class Response<T>{
    public T data;

    /**
     * Kontruktor
     * @param data Adat.
     */
    public Response(T data){
        this.data = data;
    }
    
    /**
     * Lekérdezés esetén adatfolyam létrehozása.
     * @param data Adat.
     * @return Új kapcsolat.
     */
    public static <T> Response<T> ok(T data){
        return new Response<T>(data);
    }

    /**
     * Hiba esetén hiba üzenet fellépése.
     * @param message Üzenet.
     * @throws IllegalArgumentException Hiba esetén.
     */
    public static void error(String message){
        throw new IllegalArgumentException(message);
    }
}