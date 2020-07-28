package network.bpc.bpc_dis_network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NetworkChangeReceiver extends BroadcastReceiver {

    private NetworkChangeListener networkChangedListener;

    public NetworkChangeReceiver() {

    }

    public NetworkChangeReceiver(NetworkChangeListener networkChangedListener) {
        this.networkChangedListener = networkChangedListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (networkChangedListener != null) {
            networkChangedListener.onNetworkStateChanged(NetworkManager.isNetworkAvailable(context));
        }
    }

}