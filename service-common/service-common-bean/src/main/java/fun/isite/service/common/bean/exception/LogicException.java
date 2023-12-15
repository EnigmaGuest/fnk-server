package fun.isite.service.common.bean.exception;

import fun.isite.service.common.bean.http.IResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Enigma
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LogicException extends RuntimeException{
    private String message;

    private IResponseCode code;

    public LogicException(String message) {
        this.message = message;
    }

    public LogicException(IResponseCode code) {
        this.code = code;
    }

    public LogicException(String message, IResponseCode code) {
        this.message = message;
        this.code = code;
    }
}
