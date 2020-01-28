package network.bpc.bpc_dis_network;

import java.io.Serializable;

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