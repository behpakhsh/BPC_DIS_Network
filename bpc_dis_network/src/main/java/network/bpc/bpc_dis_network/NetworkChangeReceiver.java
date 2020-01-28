package network.bpc.bpc_dis_network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

public class NetworkChangeReceiver extends BroadcastReceiver {

    private NetworkChangeListener networkChangedListener;

    public NetworkChangeReceiver() {

    }

    public NetworkChangeReceiver(NetworkChangeListener networkChangedListener) {
        this.networkChangedListener = networkChangedListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        NetworkManager networkManager = new NetworkManager();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkState networkState = networkManager.getConnectivityStatus(connectivityManager);
        networkChangedListener.onNetworkStateChanged(networkState == NetworkState.CONNECTED);
    }

}
