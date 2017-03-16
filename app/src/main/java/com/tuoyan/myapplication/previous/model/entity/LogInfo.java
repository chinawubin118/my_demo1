package com.tuoyan.myapplication.previous.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */
public class LogInfo {

    private List<Ollist> ollist;

    public List<Ollist> getOllist() {
        return ollist;
    }

    public void setOllist(List<Ollist> ollist) {
        this.ollist = ollist;
    }

    public class Ollist {
        private String logName;
        private String logCode;
        private List<Data> data;

        public String getLogCode() {
            return logCode;
        }

        public void setLogCode(String logCode) {
            this.logCode = logCode;
        }

        public List<Data> getData() {
            return data;
        }

        public void setData(List<Data> data) {
            this.data = data;
        }

        public String getLogName() {
            return logName;
        }

        public void setLogName(String logName) {
            this.logName = logName;
        }

        public class Data {
            private String time;
            private String context;
            private String ftime;

            public String getFtime() {
                return ftime;
            }

            public void setFtime(String ftime) {
                this.ftime = ftime;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }
        }
    }
}