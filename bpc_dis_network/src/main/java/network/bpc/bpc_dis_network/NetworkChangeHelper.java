package network.bpc.bpc_dis_network;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

public class NetworkChangeHelper {

    private boolean isFirstRun = true;

    public NetworkChangeReceiver registerNetworkChangeReceiver(Context context, final NetworkChangeListener networkChangeListener) {
        NetworkChangeReceiver networkChangeReceiver = new NetworkChangeReceiver(new NetworkChangeListener() {
            @Override
            public void onNetworkStateChanged(boolean isOnline) {
                if (isFirstRun) {
                    isFirstRun = false;
                    return;
                }
                if (networkChangeListener != null) {
                    networkChangeListener.onNetworkStateChanged(isOnline);
                }
            }
        });
        if (context != null) {
            context.registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        return networkChangeReceiver;
    }

    public void unregisterNetworkChangeReceiver(Context context, NetworkChangeReceiver networkChangeReceiver) {
        if (context != null) {
            context.unregisterReceiver(networkChangeReceiver);
        }
    }

}