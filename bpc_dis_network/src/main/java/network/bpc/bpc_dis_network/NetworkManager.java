package network.bpc.bpc_dis_network;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.Serializable;

public class NetworkManager {

    public enum NetworkState implements Serializable {

        NONE(0),
        CONNECTED(1),
        NOT_CONNECTED(2);

        private final int value;

        NetworkState(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }
    public static NetworkState getConnectivityStatus(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork;
        if (connectivityManager != null)
            activeNetwork = connectivityManager.getActiveNetworkInfo();
        else
            activeNetwork = null;
        if (activeNetwork != null && activeNetwork.isConnected()) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return NetworkState.CONNECTED;
            else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return NetworkState.CONNECTED;
        }
        return NetworkState.NOT_CONNECTED;
    }

}
