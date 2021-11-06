package com.think.tool.model;

/**
 * 配置
 *
 * @author veione
 * @version 1.0
 * @date 2021/11/5
 */
public final class Config {
    private String input;
    private OutputConfig output;

    public static class OutputConfig {
        private String client;
        private String server;

        public String getClient() {
            return client;
        }

        public void setClient(String client) {
            this.client = client;
        }

        public String getServer() {
            return server;
        }

        public void setServer(String server) {
            this.server = server;
        }
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public OutputConfig getOutput() {
        return output;
    }

    public void setOutput(OutputConfig output) {
        this.output = output;
    }
}
