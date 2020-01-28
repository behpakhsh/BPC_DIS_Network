package network.bpc.bpc_dis_network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkManager {

    /*
     * @deprecated
     * do not use this static method with context params
     */
    @Deprecated
    public static NetworkState getConnectivityStatus(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (connectivityManager != null) {
            activeNetwork = connectivityManager.getActiveNetworkInfo();
        }
        if (activeNetwork != null && activeNetwork.isConnected()) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                return NetworkState.CONNECTED;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return NetworkState.CONNECTED;
            }
        }
        return NetworkState.NOT_CONNECTED;
    }

    public NetworkState getConnectivityStatus(ConnectivityManager connectivityManager) {
        NetworkInfo activeNetwork = null;
        if (connectivityManager != null) {
            activeNetwork = connectivityManager.getActiveNetworkInfo();
        }
        if (activeNetwork != null && activeNetwork.isConnected()) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                return NetworkState.CONNECTED;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return NetworkState.CONNECTED;
            }
        }
        return NetworkState.NOT_CONNECTED;
    }

}
