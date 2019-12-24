package in.hocg.payment.spring.boot.sample.basic;

import lombok.Data;

/**
 * Created by hocgin on 2019/12/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class Result<T> {
    private final Integer code;
    private final String message;
    private final T data;
    
    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    
    public static <T> Result<T> create(Integer code, String message, T data) {
        return new Result<>(code, message, data);
    }
    
    public static <T> Result<T> success(T data) {
        return create(200, "ok", data);
    }
    
    public static <T> Result<T> failure(Integer code, String message, T data) {
        return create(code, message, data);
    }
    
    public static <T> Result<T> failure(String message, T data) {
        return failure(500, message, data);
    }
}
