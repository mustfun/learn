
package com.dzy.util.crypto;

/**
 * The crypto exception extension of the runtime exception.
 *
 * @author
 * @version $Id: CryptoException.java 290 2014-10-27 08:48:18Z fuchun $
 * @since 2.0
 */
public class CryptoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CryptoException() {
    }

    public CryptoException(String message) {
        super(message);
    }

    public CryptoException(String message, Throwable cause) {
        super(message, cause);
    }

    public CryptoException(Throwable cause) {
        super(cause);
    }

    public CryptoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
