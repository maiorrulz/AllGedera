package restaccessLayer;


import android.support.v4.app.Fragment;

/**
 * Created by Alex on 1/10/2016.
 */
public class RestCallback <T> {
    /** Callback interface for performing next steps */
    public interface OnResponseSuccess<T> {

        public void onSuccess(T result,Fragment f);
    }

    /** Callback interface for delivering error responses. */
    public interface OnResponseFailure<T> {
        /**
         * Callback method that an error has been occurred with the
         * provided error code and optional user-readable message.
         */
        public void onFailure(T result);
    }

}
