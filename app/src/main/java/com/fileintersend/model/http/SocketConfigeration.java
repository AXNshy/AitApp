package com.fileintersend.model.http;

/**
 * Created by axnshy on 2017/6/25.
 */

public class SocketConfigeration {

    private SocketConfigeration(){}

    private int port;

    private String address;

    private int maxParallels;

    public static class Builder {
        SocketConfigeration conf;

        public Builder() {
            this.conf = new SocketConfigeration();
        }

        public SocketConfigeration build() {
            return conf;
        }

        public Builder setPort(int port){
            conf.setPort(port);
            return this;
        }
        public Builder setAddress(String address){
            conf.setAddress(address);
            return this;
        }
        public Builder setMaxParallels(int maxParallels){
            conf.setMaxParallels(maxParallels);
            return this;
        }
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMaxParallels(int parellels) {
        this.maxParallels = parellels;
    }

    public int getPort() {
        return port;
    }

    public String getAddress() {
        return address;
    }

    public int getMaxParallels() {
        return maxParallels;
    }
}
