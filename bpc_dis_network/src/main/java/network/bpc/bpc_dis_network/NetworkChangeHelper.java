package network.bpc.bpc_dis_network;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

public class NetworkChangeHelper {

    private boolean isFirstRun = true;
    private NetworkChangeReceiver networkChangeReceiver;

    public void registerNetworkChangeReceiver(Context context, final NetworkChangeListener networkChangeListener) {
        networkChangeReceiver = new NetworkChangeReceiver(new NetworkChangeListener() {
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
    }

    public void unregisterNetworkChangeReceiver(Context context) {
        if (context != null) {
            context.unregisterReceiver(networkChangeReceiver);
        }
    }

}