package network.bpc.bpc_dis_network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NetworkChangeReceiver extends BroadcastReceiver {

    private final INetworkChangedListener networkChangedListener;
    public NetworkChangeReceiver(){
        networkChangedListener = new INetworkChangedListener() {
            @Override
            public void onNetworkStateChanged(boolean isOnline) {

            }
        };
    }
    public NetworkChangeReceiver(INetworkChangedListener networkChangedListener){
        this.networkChangedListener = networkChangedListener;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        NetworkManager.NetworkState state= NetworkManager.getConnectivityStatus(context);
        networkChangedListener.onNetworkStateChanged(state == NetworkManager.NetworkState.CONNECTED);
    }
}
