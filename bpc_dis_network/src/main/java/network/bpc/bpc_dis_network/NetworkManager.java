package network.bpc.bpc_dis_network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.StrictMode;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkManager {

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

    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    NetworkCapabilities networkCapabilities =
                            connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                    if (networkCapabilities != null) {
                        return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                                || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                                || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
                    }
                } else {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
                }
            }
        }
        return false;
    }

    @Deprecated
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

    public static boolean isInternetReachable() {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            HttpURLConnection url = (HttpURLConnection) (new URL("http://clients3.google.com/generate_204").openConnection());
            url.setRequestProperty("User-Agent", "Android");
            url.setRequestProperty("Connection", "close");
            url.setConnectTimeout(1500);
            url.setUseCaches(false);
            url.setInstanceFollowRedirects(true);
            url.connect();
            int result = url.getResponseCode();
            return (result == 204);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}